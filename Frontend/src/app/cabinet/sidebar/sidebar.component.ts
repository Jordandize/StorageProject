import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {

  tabs = [
    { name: 'Products', url: 'products', icon: 'ballot' },
    { name: 'Create Order', url: 'create-order', icon: 'queue' },
    { name: 'Orders', url: 'orders', icon: 'sort' },
    { name: 'Session', url: 'session', icon: 'data_usage' },
    { name: 'Queue', url: 'queue', icon: 'compare_arrows'} // view_week
  ];

  constructor() { }

  ngOnInit() {
  }

}
