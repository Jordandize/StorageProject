
import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatTableDataSource, MatPaginator} from '@angular/material';
import '@angular/material/prebuilt-themes/indigo-pink.css';
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


@Component({ selector: 'app-userpage', templateUrl: 'home.component.html'})

export class HomeComponent implements OnInit {

  private orders: Order[] = null;
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
     await  this.http.get(this.baseUrl + "/api/orders?user="+this.id).subscribe(data => {

        if(data!=null){
        this.dataSource2=<Order[]>data;
        }
      
        
        this.dataSource = new MatTableDataSource(this.dataSource2);
        this.dataSource.paginator = this.paginator;

         
          },   error => {
            console.log(error);
        }
        );
    }

   
}
