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


export class Tab {
  id: number;
  name: string;
  url:string;
  constructor(public id2:number, public name2:string, public url2:string) {
    this.id = id2;
    this.name=name2;
    this.url = url2;
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
  
    
    public session = sessionStorage.getItem('email');
 
     
     public displayedColumns: string[] = ['id', 'creationDateTime', 'annotation'];
     
  
  
  
      public  tabs: [Tab,Tab,Tab] = [ 
      new Tab(1,'CreateOrder','/order'), 
      new Tab(2,'List of Orders','/list'), 
      new Tab(3,'List of Products','/listProducts'),
];

  constructor(
    private http: HttpClient) {}

    ngOnInit() {
      
      console.log(sessionStorage.getItem('email'));
    }
}
