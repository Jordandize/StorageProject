import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
// import {validate} from "codelyzer/walkerFactory/walkerFn";
import { baseUrl } from '../../varUrl';
import { RequestOptions } from '@angular/http';
import {OrderLine} from "./orderLine";
import {ORDER_LINES} from './ORDER_LINES';

import { Order } from '../order';
import { ORDERS } from './ORDERS';
import { OrderService } from '../order.service';



@Component({templateUrl: 'order.component.html'})
export class OrderComponent implements OnInit {
  orderForm: FormGroup;
  loading = false;
  submitted = false;
  baseUrl: string;
  newLine: OrderLine;
  orderLines: OrderLine[];



  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router, 
    private orderService: OrderService
    // private authenticationService: AuthenticationService,
    // private alertService: AlertService
  ) {}
  ngOnInit() {
    this.orderLines = ORDER_LINES;
console.log(this.orderLines);
    this.baseUrl = baseUrl;
    this.orderForm = this.formBuilder.group({
      ordStatus: ['', Validators.required],
      ordType: ['', Validators.required],
      parentId: ['', Validators.required],
      createdDate: ['', Validators.required],
      modifiedDate: ['', Validators.required],
      crBy: ['', Validators.required],
      assTo: ['', Validators.required],
      comment: [''],
      isArchived: ['', Validators.required],

      // Array here in the future
          category:['', Validators.required],
          product:['', Validators.required],
          qnt:['', Validators.required],

      //
    });
    const head = new HttpHeaders({'Content-Type': 'application/json'});

    this.http.get(this.baseUrl+'/', {headers: head}).subscribe(
      data => {},
      error => {
        this.loading = false;
      }
    );

//     //
//     // HERE receive JSON object asd fill "category" and "product" fields
//     //
//     console.log("here");
//     this.orderService.createOrder(ORDERS[0]).subscribe(data=> console.log(data));

  }

  // convenience getter for easy access to form fields
  get f() { return this.orderForm.controls; }

  fileChange(event) {
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
      'ordStatus': this.f.ordStatus.value,
      'ordType': this.f.ordType.value,
      'parentId': this.f.parentId.value,
      'createdDate': this.f.createdDate.value,
      'modifiedDate': this.f.modifiedDate.value,
      'crBy': this.f.crBy.value,
      'assTo': this.f.assTo.value,
      'comment': this.f.comment.value,
      'isArchived': this.f.isArchived.value,
      'category': this.f.category.value,
      'product': this.f.product.value,
      'qnt': this.f.qnt.value,
      'phone': this.f.phone.value
    };


    return this.http.post(this.baseUrl+'/order', order, {headers: head}).subscribe(
      data => {
        this.router.navigate(['/home']);
      },
      error => {
        this.loading = false;
      }
    );
  }


  clickToAdd(/*orderLine: OrderLine*/) {
    var line = this.orderLines.length;
    //тут треба заповнити line даними
    this.orderLines.push(ORDER_LINES[0]);
  }

  clickToRemove(orderLine: OrderLine){
    // console.log(orderLine);
    let index = this.orderLines.indexOf(orderLine);
    if(index > -1){
      this.orderLines.splice(index,1);
    }
  }

}







