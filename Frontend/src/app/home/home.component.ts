
﻿import { Component, OnInit,Input, ViewChild } from '@angular/core';
import { first } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableDataSource,MatPaginator} from '@angular/material';
import "@angular/material/prebuilt-themes/indigo-pink.css";
import { baseUrl } from '../../varUrl';


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

var angular: any;
@Component({ selector: 'app-userpage',templateUrl: 'home.component.html'})

export class HomeComponent implements OnInit {

  private orders: Order[] = null;
 @Input() public displayedColumns: string[];
  public id = sessionStorage.getItem('userId');
  baseUrl = baseUrl;

  public order: Order[] = [ {
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
  },{
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
  },
  {
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
  }]
  @ViewChild(MatPaginator) paginator: MatPaginator;
     public  dataSource2 : Order[] ;

      public  dataSource ;
  
    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }

  constructor(
    private http: HttpClient) {}

    async ngOnInit() {
    //  console.log(this.id);
    console.log( "It s me"+ sessionStorage.getItem('userId'));
     await  this.http.get(this.baseUrl+"/api/orders?userId="+this.id).subscribe(data => {
        console.log("Element data 1");



        if(data!=null){
        this.dataSource2=<Order[]>data;
        }
        else{
        this.dataSource2=this.order;
        }
        
        this.dataSource = new MatTableDataSource(this.dataSource2);
        this.dataSource.paginator = this.paginator;

         
          },   error => {
            console.log(error);
        }
        );
         
         

        

        console.log( "Check");
      console.log( this.dataSource2);
    
 

    }

   
}
