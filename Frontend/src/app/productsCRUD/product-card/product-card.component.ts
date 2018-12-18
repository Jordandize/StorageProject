import { Component, OnInit, Input } from '@angular/core';

import { ProductService } from '../../products/product.service';
import { SessionService } from 'src/app/session.service';
import { baseUrl } from '../../../varUrl';
import swal from 'sweetalert2';
import { HttpService } from '../../http.service';
import { Router } from '@angular/router';
import { DialogOverviewExampleDialog } from '../products.component';
import {MatDialog} from '@angular/material';
import { Product } from 'src/app/data/Product';

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

}
