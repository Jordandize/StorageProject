import { Component, OnInit } from '@angular/core';
import { SessionService } from '../session.service';
import { OrderLine } from '../data/OrderLine';
import { OrderService } from '../order.service';
import { MessageService } from 'primeng/api';
import { OrderType } from '../data/OrderType';

class Order {
  comment: string;
}

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  public OrderType = OrderType;
  orderTypes = [OrderType.User, OrderType.Base];
  activeType: OrderType;
  showOrderTypeMenu: boolean;
  userColumns = ['position', 'product', 'amount', 'remove-but'];
  keeperColumns = ['position', 'order', 'product', 'amount', 'remove-but'];
  orderCreatedMessageLifeTime = 6000;
  messageLifeTime = 4000;
  emptyListMessageActive = false;
  loading = false;

  orderLines: OrderLine[];
  order: Order = new Order();

  constructor(
    private sessionService: SessionService,
    private orderService: OrderService,
    private messageService: MessageService) { }

  ngOnInit() {
    this.showOrderTypeMenu = this.sessionService.isUser() && this.sessionService.isKeeper();
    this.activeType = this.sessionService.getCreationOrderType();
    this.orderLines = this.sessionService.getOrderLines(this.activeType);
    this.sessionService.orderLinesChange$.subscribe(() => {
      this.orderLines = this.sessionService.getOrderLines(this.activeType);
    });
  }

  create() {
    if (this.orderLines == null || this.orderLines.length === 0) {
      if (!this.emptyListMessageActive) {
        this.emptyListMessageActive = true;
        this.messageService.add({severity: 'info', summary: 'Bad order',
          detail: 'Cannot create order, empty product list is not allowed.', life: this.messageLifeTime});
        setTimeout(() => this.emptyListMessageActive = false, this.messageLifeTime - 1500);
      }
      return;
    }
    this.loading = true;
    const order = {
      annotation: this.order.comment,
      orderType: 1,
      createdBy: this.sessionService.getUser().id,
      products: new Object()
    };
    this.orderLines.forEach(line => {
      order.products[line.id] = line.amount;
    });
    this.orderService.createOrderAny(order).subscribe(
    resp => {
      this.order.comment = '';
      this.clean();
      this.messageService.add({severity: 'success', summary: `Order #${resp} created!`,
        detail: 'Visit \'Orders\' page for additional information.', life: this.orderCreatedMessageLifeTime});
      this.loading = false;
    },
    error => {
      this.messageService.add({severity: 'error', summary: `Sorry, operation failed`,
        detail: 'Connect with our support group or repeat operation later.', life: this.messageLifeTime});
      this.loading = false;
    });
  }

  clean() {
    this.sessionService.deleteOrderLines(this.activeType);
  }

  setOrderType(type: OrderType) {
    this.activeType = type;
    this.sessionService.setCreationOrderType(type);
    this.orderLines = this.sessionService.getOrderLines(this.activeType);
  }

}
