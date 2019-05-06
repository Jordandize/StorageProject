import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Order } from 'src/app/data/Order';
import { UserService } from 'src/app/user.service';
import { OrderService } from 'src/app/order.service';
import { OrderStatus } from 'src/app/data/OrderStatus';
import { OrderObject } from 'src/app/data/OrderObject';
import { finalize } from 'rxjs/operators';
import { MessageService } from 'primeng/api';
import { OrderShortage } from 'src/app/data/OrderShortage';
import { SessionService } from 'src/app/session.service';
import { OrderType } from 'src/app/data/OrderType';
import { User } from 'src/app/data/User';

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

  loading = false;
  readyDisabled = false;
  supplyDisabled = false;
  baseDisabled: boolean;

  shortage: OrderShortage[];

  constructor(
    private orderService: OrderService,
    private userService: UserService,
    private messageService: MessageService,
    private sessionService: SessionService) { }

  ngOnInit() {
    if (this.user == null) {
      this.userService.getUserById(this.order.createdBy).subscribe(data => {
        this.user = data;
      });
    }
    if (this.order.orderStatus === OrderStatus.Processing) {
      this.loading = true;
      this.orderService.getShortageForOrder(this.order.id).subscribe(data => {
        this.shortage = data;
        this.baseDisabled = this.shortage.length ? false : true;
        this.loading = false;
      });
    }
  }

  toReady(id: number) {
    this.loading = true;
    this.readyDisabled = true;
    this.orderService.setOrderReady(id)
      .pipe(finalize(() => {
        this.readyDisabled = false;
        this.loading = false;
      }))
      .subscribe(order => this.ready.emit({ order: order, user: this.user }));
  }

  toBase(id: number) {
    this.shortage.forEach(s => {
      this.sessionService.setOrderLine({id: s.productId, order: this.order.id,
        amount: s.shortage, product: s.productName, position: this.sessionService
        .getOrderLines(OrderType.Base).length + 1}, OrderType.Base);
    });
    this.messageService.add({severity: 'info', summary: 'Added',
          detail: `Shortage has been added to base order.`, life: 6000});
  }

  toSupply(id: number) {
    this.loading = true;
    this.supplyDisabled = true;
    this.orderService.setOrderClosed(id)
      .pipe(finalize(() => {
        this.supplyDisabled = false;
        this.loading = false;
      }))
      .subscribe(order => {
        this.supply.emit({ order: order, user: this.user });
        this.messageService.add({severity: 'success', summary: 'Success!',
          detail: `Order #${order.id} is successfully closed!`, life: 6000});
      });
  }

}
