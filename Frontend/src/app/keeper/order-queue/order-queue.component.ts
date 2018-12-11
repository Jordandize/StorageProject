import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/order.service';
import { Order } from 'src/app/oneOrder';

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

  processingAsString = 'PROCESSING';
  readyAsString      = 'READY';
  waitingAsString    = 'WAITING';
  processingAsNumber = '2';
  readyAsNumber      = '4';
  waitingAsNumber    = '3';

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.orderService.getOrdersForKeeperByStatus(this.processingAsString, this.processingAsNumber)
      .subscribe(data => {
        this.columns.processing.orders = data;
    });
    this.orderService.getOrdersForKeeperByStatus(this.readyAsString, this.readyAsNumber)
      .subscribe(data => {
        this.columns.ready.orders = data;
    });
    this.orderService.getOrdersForKeeperByStatus(this.waitingAsString, this.waitingAsNumber)
      .subscribe(data => {
        this.columns.waiting.orders = data;
    });
  }

  toReady(id: number) {
    this.orderService.setOrderReady(id).subscribe(data => {
      console.log(data);
    });
  }

  toSupply(id: number) {
    this.orderService.setOrderClosed(id).subscribe(data => {
      console.log(data);
    });
  }

}
