import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  tabs = [
    { name: 'Products', url: 'products', icon: 'ballot' },
    { name: 'Create Order', url: 'order', icon: 'queue' },
    { name: 'Orders', url: 'orders', icon: 'sort' },
    { name: 'Session', url: 'session', icon: 'data_usage' }
  ];

  constructor() { }

  ngOnInit() {
  }

}
