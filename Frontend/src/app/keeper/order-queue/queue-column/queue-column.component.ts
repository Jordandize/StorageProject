import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-queue-column',
  templateUrl: './queue-column.component.html',
  styleUrls: ['./queue-column.component.css']
})
export class QueueColumnComponent implements OnInit {

  @Input() name = 'no_name_column';
  @Input() orders = [];

  constructor() { }

  ngOnInit() {
  }

}
