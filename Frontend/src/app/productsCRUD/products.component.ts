import { Component, OnInit,Inject } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Product } from '../products/product';
import { ProductService } from '../products/product.service';
// import { UserService } from './product.service';
import { Category } from '../products/category';
import { SessionService } from '../session.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { baseUrl } from '../../varUrl';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';
import { HttpService } from '../http.service';


export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-products2',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsOpComponent implements OnInit {
  animal: string;
  name: string;

  products: Product[];
  categories: Category[];

  selectedCategoryId: number;

  count = 0;

  constructor(
    private productService: ProductService,
    private sessionService: SessionService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog) { }
    openDialog(): void {
      const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
        width: '80%'
      });
  
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        
      });
    }
  
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
@Component({
  selector: 'productCrud',
  templateUrl: 'productCrud.html',
  styleUrls: ['./products.component.css']
})
export class DialogOverviewExampleDialog implements OnInit {

  constructor(private formBuilder: FormBuilder, private productService: ProductService,private http: HttpClient,private router: Router,private httpService: HttpService,
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}
    crudForm: FormGroup;
    categories: Category[];
    loading = false;
    submitted = false;
    baseUrl = baseUrl;
    ngOnInit() {
    this.crudForm = this.formBuilder.group({
      name: ['', Validators.required],
      amount: ['', Validators.required],
      description: ['', Validators.required],
      image: ['', Validators.required],
      icon: ['', Validators.required],
      categoryId: ['', Validators.required],
      isActive: ['', Validators.required]
  });
  this.productService.getCategories()
  .subscribe(categories => {
    this.categories = categories;
  });
  
}
get f() { return this.crudForm.controls; }
onSubmit() {
  this.submitted = true;

  // stop here if form is invalid
  if (this.crudForm.invalid) {
      return;
  }

  this.loading = true;
 var active;
 if(this.f.isActive.value=="true"){
   active=true;
 }
  else{
active=false;
  }

  const crudForm = {
      'categoryId': this.f.categoryId.value,
      'isActive': active,
      'name': this.f.name.value,
      'description': this.f.description.value,
      'amount': this.f.amount.value,
      'image': this.f.image.value,
      'icon': this.f.icon.value
  };
  console.log(crudForm);

return this.httpService.post(this.baseUrl + '/api/products', crudForm)
  .subscribe(data => {
      swal({
          type: 'success',
          title: 'You successfully create product!',
          showConfirmButton: false,
          timer: 1500
      });
      this.router.navigate(['/cabinet']);
  },
  error => {
    
          swal({
              type: 'error',
              title: 'Error!',
              text: 'Can not create product'
          });
    
      console.log(error);
      
      this.loading = false;
  });
  
}
  onNoClick(): void {
    this.dialogRef.close();
  }

}