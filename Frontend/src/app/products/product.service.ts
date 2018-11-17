import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Product } from './product';
import { Category } from './category';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService implements OnInit {

  private productsUrl = 'https://storage-pro.herokuapp.com/products';
  private productsByCategoryParam = 'category=';
  private categoriesUrl = 'https://storage-pro.herokuapp.com/categories';

  constructor(private http: HttpClient) { }

  ngOnInit() { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsUrl);
  }

  getProductsByCategory(categoryId: number): Observable<Product[]> {
    return this.http.get<Product[]>(this.productsUrl + '?'
      + this.productsByCategoryParam + categoryId);
  }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.categoriesUrl);
  }

}
