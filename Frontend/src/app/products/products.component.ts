import { Component, OnInit } from '@angular/core';

import { Product } from './product';
import { ProductService } from './product.service';
import { Category } from './category';
import { SessionService } from '../session.service';
import { OrderType } from '../data/OrderType';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[];
  categories: Category[];

  activeType: OrderType;
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
    this.activeType = this.sessionService.getCreationOrderType();
    this.count = this.getCount();
    this.sessionService.orderLinesChange$.subscribe(() => {
      this.count = this.getCount();
    });
  }

  getCount(): number {
    return this.sessionService.getOrderLines(this.activeType)
      .filter(elem => elem.order == null).length;
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
    const orderLine = this.sessionService.getOrderLine(product.id, this.activeType);
    return orderLine != null
      ? orderLine.order == null ? orderLine.amount : 0
      : 0;
  }

}
