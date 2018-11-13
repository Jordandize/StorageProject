import { Component, OnInit } from '@angular/core';

import { Product } from '../product';
import { ProductService } from '../product.service';
import { PRODUCTS } from '../PRODUCTS';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  product: Product;

  limits: any = {
    bot: 0,
    top: 9999
  };

  amount = 0;

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.productService.getProducts()
      .subscribe(data => {
        this.product = data[0] as Product;
        console.log(this.product);
        this.product = PRODUCTS[0];
      });
  }

  decrease() {
    this.amount = this.amount > this.limits.bot ? this.amount - 1 : this.limits.bot;
  }

  increase() {
    this.amount = this.amount < this.limits.top ? this.amount + 1 : this.limits.top;
  }

  amountInput() {
    if (Number.isNaN(+this.amount)) {
      this.amount = this.limits.bot;
    } else {
      this.amount = this.amount < this.limits.bot ?  this.limits.bot :
        this.amount < this.limits.top ? +this.amount : this.limits.top;
    }
  }

}
