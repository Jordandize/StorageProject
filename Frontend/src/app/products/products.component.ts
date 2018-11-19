import { Component, OnInit } from '@angular/core';

import { Product } from './product';
import { ProductService } from './product.service';
import { Category } from './category';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  products: Product[];
  categories: Category[];

  selectedCategoryId: number;

  count = 4;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(products => {
        this.products = products;
      });
    this.productService.getCategories()
      .subscribe(categories => {
        this.categories = categories;
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

}
