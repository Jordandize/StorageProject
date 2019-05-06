import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { OrderObject as Order } from 'src/app/data/OrderObject';

@Component({
  selector: 'app-queue-column',
  templateUrl: './queue-column.component.html',
  styleUrls: ['./queue-column.component.css']
})
export class QueueColumnComponent implements OnInit {

  @Output() ready = new EventEmitter<Order>();
  @Output() supply = new EventEmitter<Order>();

  @Input() name = 'Column';
  @Input() orders: Order[] = [];

  constructor() { }

  ngOnInit() {
  }

  orderReady(order: Order) {
    this.ready.emit(order);
  }

  orderSupply(order: Order) {
    this.supply.emit(order);
  }

}
