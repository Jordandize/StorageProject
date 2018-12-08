import { Component, OnInit } from '@angular/core';
import { SessionService } from '../session.service';
import { OrderLine } from '../data/OrderLine';
import { OrderService } from '../order.service';
import { MessageService } from 'primeng/api';

class Order {
  comment: string;
}

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent implements OnInit {

  order: Order = new Order();
  orderLines: OrderLine[];
  loading = false;
  emptyListMessageActive = false;

  constructor(
    private sessionService: SessionService,
    private orderService: OrderService,
    private messageService: MessageService) { }

  ngOnInit() {
    this.orderLines = this.sessionService.getOrderLines();
    this.sessionService.orderLinesChange$.subscribe(() => {
      this.orderLines = this.sessionService.getOrderLines();
    });
  }

  create() {
    if (this.orderLines == null || this.orderLines.length === 0) {
      const life = 4000;
      if (!this.emptyListMessageActive) {
        this.emptyListMessageActive = true;
        this.messageService.add({severity: 'info', summary: 'Bad order.',
          detail: 'Cannot create order, empty product list is not allowed.', life: life});
        setTimeout(() => this.emptyListMessageActive = false, life - 1500);
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
        detail: 'Please, visit \'Orders\' page for more details.', life: 6000});
      this.loading = false;
    },
    error => {
      this.messageService.add({severity: 'error', summary: `Sorry, operation failed.`,
        detail: 'Connect with our support group or repeat operation later.', life: 4000});
      this.loading = false;
    });
  }

  clean() {
    this.sessionService.deleteOrderLines();
  }

}
