import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/order.service';
import { OrderStatus } from 'src/app/data/OrderStatus';
import { OrderObject as Order } from 'src/app/data/OrderObject';

class Columns {
  [key: string]: {name: string, orders: Order[]};
}

@Component({
  selector: 'app-order-queue',
  templateUrl: './order-queue.component.html',
  styleUrls: ['./order-queue.component.css']
})
export class OrderQueueComponent implements OnInit {

  columns: Columns = {
    processing: { name: 'Processing', orders: [] },
    ready: { name: 'Ready', orders: [] },
    waiting: { name: 'Waiting', orders: [] }
  };

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.loadOrders(OrderStatus.Processing);
    this.loadOrders(OrderStatus.Ready);
    this.loadOrders(OrderStatus.Waiting);
  }

  loadOrders(status: OrderStatus) {
    this.orderService.getOrdersForKeeperByStatus(status)
      .subscribe(orders => this.columns[status.toLowerCase()].orders
        = orders.map(o => ({ order: o, user: null }))
    );
  }

  orderReady(order: Order) {
    this.columns.processing.orders = this.columns.processing
      .orders.filter(o => o.order.id !== order.order.id);
    this.loadOrders(OrderStatus.Processing);
    this.columns.ready.orders.push(order);
  }

  orderSupply(order: Order) {
    this.columns.ready.orders = this.columns.ready
      .orders.filter(o => o.order.id !== order.order.id);
  }

}
