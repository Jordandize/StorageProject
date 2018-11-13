import { Component, OnInit, Input } from '@angular/core';
import { first } from 'rxjs/operators';
import { baseUrl } from '../../varUrl';

import { HttpClient, HttpParams } from '@angular/common/http';
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableDataSource} from '@angular/material';
import "@angular/material/prebuilt-themes/indigo-pink.css";
import { HttpHeaders } from '@angular/common/http';


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

const head = new HttpHeaders({'Content-Type': 'application/json'});
@Component({ templateUrl: 'user.component.html'})
export class UserComponent implements OnInit {
  
    currentUser: User;
    
    public session = sessionStorage.getItem('email');
 
     
     public displayedColumns: string[] = ['id', 'creationDateTime', 'annotation'];
     
  
  
  
      public  users: [User,User,User] = [ 
      new User(1,'CreateOrder','/order'), 
      new User(2,'List of Orders','/listOrders'), 
      new User(3,'List of Products','/listProducts'),
];

  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router) {}

    ngOnInit() {
      console.log(sessionStorage.getItem('email'));
    }
}
