import { Component, OnInit,Input, ViewChild } from '@angular/core';
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
 @Input() public displayedColumns: string[];
  public id = sessionStorage.getItem('userId');
  baseUrl = baseUrl;
    
  @ViewChild(MatPaginator) paginator: MatPaginator;
     public  dataSource2 : Order[] ;
      public  dataSource ;
  
    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }

  constructor(
    private http: HttpClient) {}

    async ngOnInit() {
      
     await  this.http.get(this.baseUrl+"/orders?userId="+this.id).subscribe(data => {
        console.log("Element data 1");
        this.dataSource2=<Order[]>data;
        
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
