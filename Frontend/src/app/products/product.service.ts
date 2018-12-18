import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { baseUrl } from '../../varUrl';
import { Observable } from 'rxjs';
import { HttpService } from '../http.service';
import { Product } from '../data/Product';
import { Category } from '../data/Categoty';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private productsUrl = baseUrl + '/api/products';
  private productsByCategoryParam = 'category=';
  private categoriesUrl = baseUrl + '/api/categories';

  constructor(
    private http: HttpClient,
    private httpService: HttpService) { }

  getProducts(): Observable<Product[]> {
    return this.httpService.get<Product[]>(this.productsUrl);
  }

  getProductsByCategory(categoryId: number): Observable<Product[]> {
    return this.httpService.get<Product[]>(this.productsUrl + '?'
      + this.productsByCategoryParam + categoryId);
  }

  getCategories(): Observable<Category[]> {
    return this.httpService.get<Category[]>(this.categoriesUrl);
  }

}
