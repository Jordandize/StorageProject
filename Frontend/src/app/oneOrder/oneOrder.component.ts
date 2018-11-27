import { Component, OnInit,Input,ViewChild } from '@angular/core';
import { first } from 'rxjs/operators';
import {Observable} from 'rxjs';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableDataSource} from '@angular/material';
import "@angular/material/prebuilt-themes/indigo-pink.css";
import { baseUrl } from '../../varUrl';
import { ActivatedRoute } from '@angular/router';
import {MatPaginator} from '@angular/material';

export interface Order {
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
export interface Product {
  id : number;
  categoryId : number;
  name : string;
  amount : number;
  description : string;
  image : string;
  icon : string;
  active : boolean;
}
@Component({templateUrl: 'oneOrder.component.html'})
export class OneOrderComponent implements OnInit {
 @Input() public displayedColumns: string[];
  baseUrl = baseUrl;
  public orderId;
  
  @ViewChild(MatPaginator) paginator: MatPaginator;
  
  
  
public  dataSource ;
obs: Observable<any>;
public product: Product[];
public order: Order;
  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private router: Router) {}

    async ngOnInit() {
      await this.route.params.subscribe(params => { this.orderId = params['id'];
    });
    await  this.http.get(this.baseUrl+"/api/orders/oneOrder/"+this.orderId).subscribe(data => {
    this.order=<Order>data;
        },   error => {
          console.log(error);
      }
      );
      await  this.http.get(this.baseUrl+"/api/orders_products/products/"+this.orderId).subscribe(data => {
          this.product=<Product[]>data;
          this.dataSource = new MatTableDataSource<Product>(this.product);
          this.obs = this.dataSource.connect();
          this.dataSource.paginator = this.paginator;
            },   error => {
              console.log(error);
            }
          );
    }

   
}
