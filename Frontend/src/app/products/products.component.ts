import { Component, OnInit } from '@angular/core';

import { Product } from './product';
import { ProductService } from './product.service';
import { PRODUCTS } from './PRODUCTS';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[];

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(products => {
        this.products = products;
        console.log(products);
        // this.products = PRODUCTS;
      });
    this.productService.getProductsByCategory(1)
      .subscribe(products => {
        console.log(products);
      });
    this.productService.getCategories()
      .subscribe(categories => {
        console.log(categories);
      });
  }
}
