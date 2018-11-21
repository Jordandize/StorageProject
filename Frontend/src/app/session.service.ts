import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

import { OrderLine } from './data/OrderLine';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private orderLinesChangeSource = new Subject<void>();
  // subsribe this to listen for order lines change
  orderLinesChange$ = this.orderLinesChangeSource.asObservable();

  orderLinesKey = 'OrderLines';

  constructor() { }

  // emit event
  orderLinesChange() {
    this.orderLinesChangeSource.next();
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
