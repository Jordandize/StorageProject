import { Component, OnInit } from '@angular/core';

import { Product } from './product';
import { ProductService } from './product.service';
// import { UserService } from './product.service';
import { Category } from './category';
import { SessionService } from '../session.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[];
  categories: Category[];

  selectedCategoryId: number;

  count = 0;

  constructor(
    private productService: ProductService,
    private sessionService: SessionService) { }

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(products => {
        this.products = products;
      });
    this.productService.getCategories()
      .subscribe(categories => {
        this.categories = categories;
      });
    this.count = this.sessionService.getOrderLines().length;
    this.sessionService.orderLinesChange$.subscribe(() => {
      this.count = this.sessionService.getOrderLines().length;
    });
  }

  categoryChanged() {
    if (this.selectedCategoryId == null) {
      this.productService.getProducts()
        .subscribe(products => {
          this.products = products;
        });
    } else {
      this.productService.getProductsByCategory(this.selectedCategoryId)
        .subscribe(products => {
          this.products = products;
        });
    }
  }

  inOrderAmount(product: Product): number {
    const orderLine = this.sessionService.getOrderLine(product.id);
    return orderLine != null ? orderLine.amount : 0;
  }

}
