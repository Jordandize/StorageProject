import { Component, OnInit, HostBinding} from '@angular/core';

import { OrderLine } from '../data/orderLine';
import { SessionService } from '../session.service';

@Component({
  selector: 'app-session-viewer',
  templateUrl: './session-viewer.component.html',
  styleUrls: ['./session-viewer.component.css']
})
export class SessionViewerComponent implements OnInit {

  orderLines: OrderLine[];
  displayedColumns: string[];

  constructor(private sessionService: SessionService) {
  }

  ngOnInit() {
    this.sessionService.orderLinesChange$.subscribe(() => { this.orderLines = this.sessionService.getOrderLines(); console.log(25); });
    this.sessionService.orderLinesChange();
    this.displayedColumns = ['id', 'amount', 'product', 'category'];
  }

}
