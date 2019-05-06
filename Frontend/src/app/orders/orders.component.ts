import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {MatTableDataSource, MatPaginator, MatSort} from '@angular/material';
import {HttpClient} from '@angular/common/http';

import { baseUrl } from '../../varUrl';
import { OrderService } from '../order.service';
import { SessionService } from '../session.service';
import { HttpService } from '../http.service';


export class Order {
  annotation: string;
  archived: boolean;
  assignedTo: number;
  createdBy: number;
  creationDateTime: string;
  id: number;
  modifiedDateTime: string;
  orderStatus: number;
  orderType: number;
  parentId: number;
}

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  displayedColumns = ['id', 'creationDateTime', 'annotation'];
  private orders: Order[] = null;
  public id = this.sessionService.getUser().id;
  baseUrl = baseUrl;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

     public  dataSource2: Order[] ;

      public  dataSource ;

    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }

  constructor(
    private orderService: OrderService,
    private http: HttpClient,
    private httpService: HttpService,
    private sessionService: SessionService) {}

    async ngOnInit() {
     await  this.httpService.get(this.baseUrl + '/api/orders/' + this.id).subscribe(data => {

        if (data != null) {
        this.dataSource2 = <Order[]>data;
        }


        this.dataSource = new MatTableDataSource(this.dataSource2);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;


          },   error => {
            console.log(error);
        }
        );
    }

}
