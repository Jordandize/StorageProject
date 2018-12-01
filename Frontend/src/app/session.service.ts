import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

import { OrderLine } from './data/OrderLine';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private orderLinesChangeSource = new Subject<void>();
  // subsribe this to listen for order lines change
  orderLinesChange$ = this.orderLinesChangeSource.asObservable();

  orderLinesKey = 'OrderLines';
  userKey = 'User';
  rolesKey = 'Roles';
  authTokenKey = 'AuthToken';

  private user: User;
  private roles: string[];
  private authToken: string;

  constructor() {
    this.initialize();
  }

  private initialize() {
    this.user = this.getUser();
  }

  getUser(): User {
    if (this.user == null) {
      this.user = this.getFromCache(this.userKey);
    }
    return  this.user;
  }

  setUser(user: User) {
    this.saveInCache(this.userKey, user);
    this.user = user;
  }

  getRoles(): string[] {
    if (this.roles == null) {
      this.roles = this.getFromCache(this.rolesKey);
    }
    return this.roles;
  }

  setRoles(roles: string[]) {
    this.saveInCache(this.rolesKey, roles);
    this.roles = roles;
  }

  getAuth(): string {
    if (this.authToken == null) {
      this.authToken = this.getFromCache(this.authTokenKey);
    }
    return  this.authToken;
  }

  setAuth(authToken: string) {
    this.saveInCache(this.authTokenKey, authToken);
    this.authToken = authToken;
  }

  isLogined(): boolean {
    return  this.getUser() != null;
  }

  login(user: User, roles: string[], authToken: string): void {
    this.setUser(user);
    this.setRoles(roles);
    this.setAuth(authToken);
  }

  logout(): void {
    this.setUser(null);
    this.setRoles(null);
    this.setAuth(null);
  }

  isUser(): boolean {
    return  this.roles.some(s => s.includes('USER'));
  }

  isKeeper(): boolean {
    return  this.roles.some(s => s.includes('KEEPER'));
  }

  isAdmin(): boolean {
    return  this.roles.some(s => s.includes('ADMIN'));
  }

  saveInCache(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  getFromCache(key: string): any {
    return JSON.parse(localStorage.getItem(key));
  }

  getOrderLines(): OrderLine[] {
    let orderLines = JSON.parse(localStorage.getItem(this.orderLinesKey));
    if (orderLines == null) {
      localStorage.setItem(this.orderLinesKey, JSON.stringify([]));
      orderLines = JSON.parse(localStorage.getItem(this.orderLinesKey));
    }
    return orderLines;
  }

  setOrderLine(orderLine: OrderLine) {
    const cachedOrderLine = this.getOrderLine(orderLine.id);
    const orderLines = this.getOrderLines();
    if (cachedOrderLine == null) {
      orderLines.push(orderLine);
    } else {
      orderLines.forEach(elem => {
        if (elem.id === cachedOrderLine.id) {
          elem.amount = orderLine.amount;
        }
      });
    }
    this.saveOrderLines(orderLines);
  }

  getOrderLine(id: number): OrderLine {
    return this.getOrderLines().find(elem => elem.id === id);
  }

  removeOrderLine(id: number) {
    const orderLines = this.getOrderLines().filter(elem => elem.id !== id);
    this.saveOrderLines(orderLines);
  }

  saveOrderLines(orderLines: OrderLine[]) {
    localStorage.setItem(this.orderLinesKey, JSON.stringify(orderLines));
    this.orderLinesChange();
  }

  deleteOrderLines() {
    localStorage.setItem(this.orderLinesKey, JSON.stringify([]));
    this.orderLinesChange();
  }

  // emit event
  orderLinesChange() {
    this.orderLinesChangeSource.next();
  }

}
