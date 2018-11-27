import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderLine} from "./orderLine";
import {ORDER_LINES} from "./ORDER_LINES";

import { Product } from '../products/product';
import { ProductService } from '../products/product.service';
import { Category } from '../products/category';
import { OrderService } from '../order.service';
import swal from "sweetalert2";



@Component({templateUrl: 'order.component.html',
  styleUrls: ['./order.component.css']})
export class OrderComponent implements OnInit {
  orderForm: FormGroup;
  loading = false;
  submitted = false;
  baseUrl: string;
  newLine: OrderLine;
  orderLines: OrderLine[];
  selectedCategoryId: number;
  categories: Category[];
  products: Product[];
  amount = 1;
  limits: any = {
    bot: 1,
    top: 9999
  };



  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private orderService: OrderService,
    private productService: ProductService
  ) {}
  ngOnInit() {
    this.orderLines = ORDER_LINES;
console.log(this.orderLines);

    this.productService.getCategories()
      .subscribe(categories => {
        this.categories = categories;
      });


  }
  get f() { return this.orderForm.controls; }

  fileChange(event) {
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

  categoryChanged(){

    this.productService.getProductsByCategory(this.selectedCategoryId)
      .subscribe(products => {
        this.products = products;
      });
  }


  clickToAdd(orderLine: OrderLine){
    var line = this.orderLines.length;
    this.orderLines.push({prodId: 1, prodName: 'name', category: 'cat', amount: 1});
  }

  clickToRemove(orderLine: OrderLine){
    // console.log(orderLine);
    let index = this.orderLines.indexOf(orderLine);
    if(index > -1){
      this.orderLines.splice(index,1);
    }
  }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.orderForm.invalid) {
      return;
    }
    this.loading = true;
    const head = new HttpHeaders({'Content-Type': 'application/json'});

    const order = {
      'comment': this.f.comment.value,
      'attach': this.f.attach.value,
      'catecory': this.f.category.value,
      'product': this.f.product.value,
      'amount': this.f.amount.value
    };
    return this.http.post(this.baseUrl + '/api/orders', order, {headers: head}).subscribe(
      data => {
        swal({
          position: 'top-end',
          type: 'success',
          title: 'Your order has been saved!',
          showConfirmButton: false,
          timer: 1500
        });
        this.router.navigate(['/register']);
      },
      error => {
        console.log(error);

        swal({
          type: 'error',
          title: 'Error!',

         });

        this.loading = false;
      }
    );
  }
}







