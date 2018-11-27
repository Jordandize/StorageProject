import { Component, OnInit } from '@angular/core';
import { SessionService } from '../session.service';
import { OrderLine } from '../data/OrderLine';
import { OrderService } from '../order.service';
import { baseUrl } from '../../varUrl';
import { HttpClient } from '@angular/common/http';

class Order {
  comment: string;
}

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  baseUrl: string;

  order: Order = new Order();
  orderLines: OrderLine[];

  constructor(
    private sessionService: SessionService,
    private orderService: OrderService,
    private http: HttpClient) { }

  ngOnInit() {
    this.baseUrl = baseUrl;
    this.orderLines = this.sessionService.getOrderLines();
    this.sessionService.orderLinesChange$.subscribe(() => {
      this.orderLines = this.sessionService.getOrderLines();
    });
  }

  create() {
    if (this.orderLines == null || this.orderLines.length === 0) {
      return;
    }
    const order = {
      annotation: this.order.comment,
      orderType: 1,
      createdBy: this.sessionService.getUser().id,
      products: new Object()
    };
    this.orderLines.forEach(line => {
      order.products[line.id] = line.id;
    });
    this.orderService.createOrderAny(order).subscribe(resp => {
      this.order.comment = '';
      this.clean();
    },
    error => {
      console.log('Error ' + error);
    });
  }

  clean() {
    this.sessionService.deleteOrderLines();
  }

}
