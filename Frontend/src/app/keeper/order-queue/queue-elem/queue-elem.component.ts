import { Component, OnInit, Input } from '@angular/core';
import { Order } from 'src/app/oneOrder';
import { User } from 'src/app/User';
import { UserService } from 'src/app/user.service';
import { OrderService } from 'src/app/order.service';
import { OrderStatus } from 'src/app/data/OrderStatus';

@Component({
  selector: 'app-queue-elem',
  templateUrl: './queue-elem.component.html',
  styleUrls: ['./queue-elem.component.css']
})
export class QueueElemComponent implements OnInit {

  OrderStatus;

  @Input() order: Order;
  user: User;

  constructor(
    private orderService: OrderService,
    private userService: UserService) { }

  ngOnInit() {
    this.OrderStatus = OrderStatus;
    this.userService.getUserById(this.order.createdBy).subscribe(data => {
      this.user = data;
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
