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
 private user: User;
 private roles: string[];
 private authToken: string;
 private isLogined: boolean;
  constructor() { }

  // emit event
  orderLinesChange() {
    this.orderLinesChangeSource.next();
  }
  getUser(): User {
    return  this.user;
  }

  setUser(user: User) {
    this.user=user;
  }
  getRoles():string[] {
    return  this.roles;
  }

  setRoles(roles: string[]) {
    this.roles=roles;
  }
  getAuth():string {
    return  this.authToken;
  }

  setAuth(authToken: string) {
    this.authToken=authToken;
  }
  getLogin():boolean {
    return  this.isLogined;
  }

  setLogin(isLogined: boolean) {
    this.isLogined=isLogined;
  }

  login(user: User, roles: string[], authToken: string): void{
    this.setUser(user);
    this.setRoles(roles);
    this.setAuth(authToken);
  }
  logout(): void{
    this.setUser(null);
    this.setRoles(null);
    this.setAuth(null);
  }
  isUser(): boolean{
  return  this.roles.some(s => s.includes("USER"));
  }
  isKeeper(): boolean{
    return  this.roles.some(s => s.includes("KEEPER"));
  }
  isAdmin(): boolean{
    return  this.roles.some(s => s.includes("ADMIN"));
  }
  saveInCache(key: string, value: string): void{
    sessionStorage.setItem(key, value);
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
      console.log(orderLine);
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

}
