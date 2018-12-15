import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Order } from 'src/app/data/Order';
import { User } from 'src/app/User';
import { UserService } from 'src/app/user.service';
import { OrderService } from 'src/app/order.service';
import { OrderStatus } from 'src/app/data/OrderStatus';
import { OrderObject } from 'src/app/data/OrderObject';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-queue-elem',
  templateUrl: './queue-elem.component.html',
  styleUrls: ['./queue-elem.component.css']
})
export class QueueElemComponent implements OnInit {

  public OrderStatus = OrderStatus;

  @Output() ready = new EventEmitter<OrderObject>();
  @Output() supply = new EventEmitter<OrderObject>();
  @Input() order: Order;
  @Input() user: User;

  readyDisabled = false;
  supplyDisabled = false;

  constructor(
    private orderService: OrderService,
    private userService: UserService) { }

  ngOnInit() {
    if (this.user == null) {
      this.userService.getUserById(this.order.createdBy).subscribe(data => {
        this.user = data;
      });
    }
  }

  toReady(id: number) {
    this.readyDisabled = true;
    this.orderService.setOrderReady(id)
      .pipe(finalize(() => this.readyDisabled = false))
      .subscribe(order => this.ready.emit({ order: order, user: this.user }));
  }

  toSupply(id: number) {
    this.supplyDisabled = true;
    this.orderService.setOrderClosed(id)
      .pipe(finalize(() => this.supplyDisabled = false))
      .subscribe(order => this.supply.emit({ order: order, user: this.user }));
  }

}
