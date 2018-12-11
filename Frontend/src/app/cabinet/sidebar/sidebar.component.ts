import { Component, OnInit } from '@angular/core';
import { baseUrl } from '../../../varUrl';
import {HttpClient} from '@angular/common/http';
import { HttpService } from '../../http.service';
export class Tab {
  name: string;
  url: string;
  icon: string;
}
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
  baseUrl = baseUrl;
  
  constructor( private httpService: HttpService,private http: HttpClient) {  }

  async ngOnInit() {
    await  this.httpService.get(this.baseUrl+"/api/tabs").subscribe(data => {
      this.tabs=<Tab[]>data;
          },   error => {
            console.log(error);
        }
        );
  }

}
