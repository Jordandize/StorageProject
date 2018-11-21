import { Component, OnInit, Input } from '@angular/core';

import { Product } from '../product';
import { ProductService } from '../product.service';
import { PRODUCTS } from '../PRODUCTS';
import { SessionService } from 'src/app/session.service';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {

  @Input() product: Product;
  @Input() amount = 0;

  isHovered = false;

  limits: any = {
    bot: 0,
    top: 9999
  };

  constructor(
    private productService: ProductService,
    private sessionService: SessionService) { }

  ngOnInit() {
    // If No Product Injected Take From Static Collection
    if (this.product == null) {
      this.productService.getProducts()
        .subscribe(products => {
          // this.product = products[0];
          this.product = PRODUCTS[1];
        });
    }
    if (this.product.category == null) {
      this.productService.getCategories()
        .subscribe(categories => {
          this.product.category = categories.find(c => c.id === this.product.categoryId).name;
        });
    }
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

  onMouseEnter() {
    this.isHovered = true;
  }

  onMouseLeave() {
    this.isHovered = false;
  }

  get() {
    if (this.amount !== 0) {
      this.sessionService.setOrderLine({id: this.product.id, amount: this.amount,
        product: this.product.name, category: this.product.category});
    } else if (this.amount === 0) {
      this.sessionService.removeOrderLine(this.product.id);
    }
  }

}
