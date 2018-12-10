import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/order.service';
import { Order } from 'src/app/oneOrder';

@Component({
  selector: 'app-order-queue',
  templateUrl: './order-queue.component.html',
  styleUrls: ['./order-queue.component.css']
})
export class OrderQueueComponent implements OnInit {

  columns = ['Processing', 'Ready', 'Waiting'];

  processingAsString = 'PROCESSING';
  readyAsString      = 'READY';
  waitingAsString    = 'WAITING';
  processingAsNumber = '2';
  readyAsNumber      = '4';
  waitingAsNumber    = '3';

  orders = [];

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.orderService.getOrdersForKeeperByStatus(this.processingAsString, this.processingAsNumber)
      .subscribe(data => {
        this.orders[this.columns[0]] = data;
    });
    this.orderService.getOrdersForKeeperByStatus(this.readyAsString, this.readyAsNumber)
      .subscribe(data => {
      this.orders[this.columns[1]] = data;
    });
    this.orderService.getOrdersForKeeperByStatus(this.waitingAsString, this.waitingAsNumber)
      .subscribe(data => {
      this.orders[this.columns[2]] = data;
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
