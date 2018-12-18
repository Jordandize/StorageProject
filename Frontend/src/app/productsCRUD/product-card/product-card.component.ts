import { Component, OnInit, Input } from '@angular/core';

import { Product } from '../../products/product';
import { ProductService } from '../../products/product.service';
import { PRODUCTS } from '../../products/PRODUCTS';
import { SessionService } from 'src/app/session.service';
import { baseUrl } from '../../../varUrl';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';
import { HttpService } from '../../http.service';
import { Router, ActivatedRoute } from '@angular/router';
import { DialogOverviewExampleDialog } from '../products.component';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-product-card2',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardOpComponent implements OnInit {

  @Input() product: Product;
  @Input() amount = 0;
  baseUrl = baseUrl;
  isHovered = false;

  limits: any = {
    bot: 0,
    top: 9999
  };

  constructor(
    private productService: ProductService,
    private sessionService: SessionService, 
      private httpService: HttpService,
    private router: Router,
    public dialog: MatDialog) { }
    openDialog2(): void {
      console.log(this.product);
      const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
        width: '80%',
        data: {product:this.product,id:this.product.id,name:this.product.name,categoryId:this.product.categoryId,amount:this.product.amount,description:this.product.description,image:this.product.image,icon:this.product.icon,active:this.product.active}
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        
      });
    }
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
  delete( productId:number): void {
    this.httpService.delete(this.baseUrl + '/api/products/'+productId)
.subscribe(data => {
   swal({
       type: 'success',
       title: 'You successfully delete product!',
       showConfirmButton: false,
       timer: 1500
   });
   this.router.navigate(['/cabinet']);
},
error => {
 
       swal({
           type: 'error',
           title: 'Error!',
           text: 'Can not delete product'
       });
 
   console.log(error);
});
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
        product: this.product.name,
        position: this.sessionService.getOrderLines().length + 1 });
    } else if (this.amount === 0) {
      this.sessionService.removeOrderLine(this.product.id);
    }
  }

}
