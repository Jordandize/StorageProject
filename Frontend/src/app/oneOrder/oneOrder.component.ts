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
  
  public order2: Order =  {
    "id" : 1,
    "parentId" : 1,
    "orderType" : 1,
    "orderStatus" : 1,
    "creationDateTime" : "2018-02-11T06:35:00.000+0000",
    "modifiedDateTime" : "2018-02-11T06:35:00.000+0000",
    "annotation" : "nothing",
    "createdBy" : 2,
    "assignedTo" : 3,
    "archived" : false
  }
  public product2: Product[] =[{
    "id" : 2,
    "categoryId" : 1,
    "name" : "Saw circular Daewoo DAS 1500-190",
    "amount" : 2,
    "description" : null,
    "image" : "https://source.unsplash.com/random/800x600",
    "icon" : null,
    "active" : true
  },{
  "id" : 2,
  "categoryId" : 1,
  "name" : "Saw circular Daewoo DAS 1500-190",
  "amount" : 2,
  "description" : null,
  "image" : "https://source.unsplash.com/random/800x600",
  "icon" : null,
  "active" : true
},{
  "id" : 2,
  "categoryId" : 1,
  "name" : "Saw circular Daewoo DAS 1500-190",
  "amount" : 2,
  "description" : null,
  "image" : "https://source.unsplash.com/random/800x600",
  "icon" : null,
  "active" : true
},{
"id" : 2,
"categoryId" : 1,
"name" : "Saw circular Daewoo DAS 1500-190",
"amount" : 2,
"description" : null,
"image" : "https://source.unsplash.com/random/800x600",
"icon" : null,
"active" : true
}];
  
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
