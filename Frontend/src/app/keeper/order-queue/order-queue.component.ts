import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-queue',
  templateUrl: './order-queue.component.html',
  styleUrls: ['./order-queue.component.css']
})
export class OrderQueueComponent implements OnInit {

  columns = ['Processing', 'Waiting', 'Ready'];

  constructor() { }

  ngOnInit() {
  }

}
