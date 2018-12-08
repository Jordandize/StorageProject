import { Component, OnInit, Input} from '@angular/core';

import { OrderLine } from '../data/OrderLine';
import { SessionService } from '../session.service';

@Component({
  selector: 'app-session-viewer',
  templateUrl: './session-viewer.component.html',
  styleUrls: ['./session-viewer.component.css']
})
export class SessionViewerComponent implements OnInit {

  @Input() orderLines: OrderLine[];
  @Input() displayedColumns: string[];

  constructor(private sessionService: SessionService) {
  }

  ngOnInit() {
    if (this.orderLines == null) {
      this.orderLines = this.sessionService.getOrderLines();
    }
    if (this.displayedColumns == null) {
      this.displayedColumns = ['id', 'amount', 'product', 'category'];
    }
  }

  removeLine(id: number) {
    this.sessionService.removeOrderLine(id);
  }

}
