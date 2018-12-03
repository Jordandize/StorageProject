import { Component, OnInit, Input } from '@angular/core';

import { Product } from '../../products/product';
import { ProductService } from '../../products/product.service';
import { PRODUCTS } from '../../products/PRODUCTS';
import { SessionService } from 'src/app/session.service';

@Component({
  selector: 'app-product-card2',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardOpComponent implements OnInit {

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
  onMouseEnter() {
    this.isHovered = true;
  }

  onMouseLeave() {
    this.isHovered = false;
  }

  get() {
    if (this.amount !== 0) {
      this.sessionService.setOrderLine({id: this.product.id, amount: this.amount,
        product: this.product.name, category: this.product.category,
        position: this.sessionService.getOrderLines().length + 1 });
    } else if (this.amount === 0) {
      this.sessionService.removeOrderLine(this.product.id);
    }
  }

}
