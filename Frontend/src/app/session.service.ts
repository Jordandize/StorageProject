import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

import { OrderLine } from './data/OrderLine';
import { User } from './User';
import { OrderType } from './data/OrderType';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private orderLinesChangeSource = new Subject<void>();
  // subsribe this to listen for order lines change
  orderLinesChange$ = this.orderLinesChangeSource.asObservable();

  orderTypeKey = 'OrderType';
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
    this.roles = this.getRoles();
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

  getCreationOrderType(): OrderType {
    let type: OrderType;
    if (this.isKeeper()) {
      type = this.getFromCache(this.orderTypeKey);
      type = type == null ? OrderType.Base : type;
    } else {
      type = OrderType.User;
    }
    return type;
  }

  setCreationOrderType(type: OrderType) {
    this.saveInCache(this.orderTypeKey, type);
  }

  getOrderLines(type: OrderType = this.getCreationOrderType()): OrderLine[] {
    const linesKey = this.orderLinesKey + type;
    let orderLines = JSON.parse(localStorage.getItem(linesKey));
    if (orderLines == null) {
      localStorage.setItem(linesKey, JSON.stringify([]));
      orderLines = JSON.parse(localStorage.getItem(linesKey));
    }
    return orderLines;
  }

  setOrderLine(orderLine: OrderLine, type: OrderType = this.getCreationOrderType()) {
    const orderLines = this.getOrderLines(type);
    const cachedOrderLine = orderLines.find(elem => elem.id === orderLine.id);
    if (cachedOrderLine == null) {
      orderLines.push(orderLine);
    } else {
      if (cachedOrderLine.order == null || (cachedOrderLine.order != null && orderLine.order != null)) {
        orderLines.forEach(elem => {
          if (elem.id === cachedOrderLine.id) {
            elem.amount = orderLine.amount;
          }
        });
      } else {
        orderLines.push(orderLine);
      }
    }
    this.saveOrderLines(orderLines, type);
  }

  getOrderLine(id: number, type: OrderType = this.getCreationOrderType()): OrderLine {
      return this.getOrderLines(type).find(elem => elem.id === id);
  }

  removeOrderLine(id: number, type: OrderType = this.getCreationOrderType(), order = null) {
    let orderLines: OrderLine[];
    orderLines = order == null
      ? this.getOrderLines(type).filter(elem => (elem.id === id && elem.order != null) || elem.id !== id)
      : this.getOrderLines(type).filter(elem => elem.order !== order);
    this.saveOrderLines(orderLines, type);
  }

  saveOrderLines(orderLines: OrderLine[], type: OrderType = this.getCreationOrderType()) {
    localStorage.setItem(this.orderLinesKey + type, JSON.stringify(orderLines));
    this.orderLinesChange();
  }

  deleteOrderLines(type: OrderType = this.getCreationOrderType()) {
    localStorage.setItem(this.orderLinesKey + type, JSON.stringify([]));
    this.orderLinesChange();
  }

  getOrderIdIfExistsInOrder(id: number, type: OrderType = this.getCreationOrderType()) {
    return this.getOrderLines(type).filter(elem => elem.id === id)[0].order;
  }

  // emit event
  orderLinesChange() {
    this.orderLinesChangeSource.next();
  }

}
