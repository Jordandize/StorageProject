import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/order.service';
import { OrderStatus } from 'src/app/data/OrderStatus';

@Component({
  selector: 'app-order-queue',
  templateUrl: './order-queue.component.html',
  styleUrls: ['./order-queue.component.css']
})
export class OrderQueueComponent implements OnInit {

  columns = {
    processing: { name: 'Processing', orders: [] },
    ready: { name: 'Ready', orders: [] },
    waiting: { name: 'Waiting', orders: [] }
  };

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.orderService.getOrdersForKeeperByStatus(
      OrderStatus.processing.asString, OrderStatus.processing.asNumber)
      .subscribe(data => {
        this.columns.processing.orders = data;
    });
    this.orderService.getOrdersForKeeperByStatus(
      OrderStatus.ready.asString, OrderStatus.ready.asNumber)
      .subscribe(data => {
        this.columns.ready.orders = data;
    });
    this.orderService.getOrdersForKeeperByStatus(
      OrderStatus.waiting.asString, OrderStatus.waiting.asNumber)
      .subscribe(data => {
        this.columns.waiting.orders = data;
    });
  }

}
