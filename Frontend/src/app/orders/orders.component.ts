import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {MatTableDataSource, MatPaginator} from '@angular/material';
import {HttpClient} from '@angular/common/http';

import { baseUrl } from '../../varUrl';

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
  public id = sessionStorage.getItem('userId');
  baseUrl = baseUrl;

  @ViewChild(MatPaginator) paginator: MatPaginator;
     public  dataSource2: Order[] ;

      public  dataSource ;

    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }

  constructor(
    private http: HttpClient) {}

    async ngOnInit() {
     await  this.http.get(this.baseUrl + '/api/orders/' + this.id).subscribe(data => {

        if (data != null) {
        this.dataSource2 = <Order[]>data;
        }


        this.dataSource = new MatTableDataSource(this.dataSource2);
        this.dataSource.paginator = this.paginator;


          },   error => {
            console.log(error);
        }
        );
    }



}
