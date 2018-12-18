/*import { Component, OnInit } from '@angular/core';
import { Category } from '../category';
import { CategoriesService } from '../categories.service';
import { ProductService } from '../products/product.service';
import { baseUrl } from '../../varUrl';
import { HttpService } from '../http.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  baseUrl = baseUrl;

  categories: Category[];

  

  constructor(private productService: ProductService,
  			  private httpService: HttpService) { }

  ngOnInit() {
  	
  }


  // getCategories(): void {
  // 	this.productService.getCategories()
  // 		.subscribe(categories => this.categories = categories);
  // }
}
*/

import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {MatTableDataSource, MatPaginator} from '@angular/material';
import {HttpClient, HttpHeaders} from '@angular/common/http';

import { baseUrl } from '../../varUrl';
import { OrderService } from '../order.service';
import { SessionService } from '../session.service';
import { HttpService } from '../http.service';
import { Observable, of } from 'rxjs';

/*
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
*/

export class Category{
	id: number;
	name: string;
}


@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  displayedColumns = ['id', 'name', 'action'];
  private categories: Category[] = null;
  public id = this.sessionService.getUser().id;
  baseUrl = baseUrl;

  @ViewChild(MatPaginator) paginator: MatPaginator;
     public  dataSource2: Category[] ;

      public  dataSource ;

    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }

  constructor(
    private orderService: OrderService,
    private http: HttpClient,
    private httpService: HttpService,
    private sessionService: SessionService) {}

  updateCategory(){

  }

  deleteCategory(category: Category | number): void{
  	// const categoryId = typeof category === 'number' ? category : category.id;
   //  return this.httpService.delete<Category>(baseUrl+'/api/categories/{id}');
  }

    async ngOnInit() {
     await  this.httpService.get(this.baseUrl + '/api/categories').subscribe(data => {

        if (data != null) {
        this.dataSource2 = <Category[]>data;
        }

        this.dataSource = new MatTableDataSource(this.dataSource2);
        this.dataSource.paginator = this.paginator;

          },   error => {
            console.log(error);
        }
        );
    }

}
