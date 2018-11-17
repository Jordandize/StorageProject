import { Component, OnInit,Input } from '@angular/core';
import { first } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableDataSource} from '@angular/material';
import "@angular/material/prebuilt-themes/indigo-pink.css";
import { baseUrl } from '../../varUrl';


export class User {
  id: number;
  username: string;
  url:string;
  constructor(public id2:number, public username2:string, public url2:string) {
    this.id = id2;
    this.username=username2;
    this.url = url2;
 }
}
export class Current {
  email: string;
  constructor(public email2:string) {
    this.email = email2;
 }
}
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
var orders: Order[] = null;
@Component({ selector: 'app-home',templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
 @Input() public displayedColumns: string[];
 // @Input() public  dataSource2;
  public id = sessionStorage.getItem('userId');
  baseUrl = baseUrl;
    
     
     public  dataSource2 : Order[] =orders;
      public  dataSource ;
  
    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }

  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router) {}

    ngOnInit() {
       this.http.get(this.baseUrl+"/api/orders?userId="+this.id).subscribe(data => {
        console.log("Element data 1");
         orders=<Order[]>data;
         console.log(orders);
         
          },   error => {
            console.log(error);
        }
        );
         
         
        this.dataSource2=orders;
        this.dataSource = new MatTableDataSource(this.dataSource2);
        console.log( "Check");
      console.log( this.dataSource2);
    
 

    }

   
}
