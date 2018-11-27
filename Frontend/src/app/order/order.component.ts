import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
// import {validate} from "codelyzer/walkerFactory/walkerFn";
import { baseUrl } from '../../varUrl';

import { Order } from '../order';
import { ORDERS } from './ORDERS';
import { OrderService } from '../order.service';



@Component({templateUrl: 'order.component.html'})
export class OrderComponent implements OnInit {
  orderForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  baseUrl: string;



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

      //Array here in the future
          category:['', Validators.required],
          product:['', Validators.required],
          qnt:['', Validators.required],

      //
    });
    const head = new HttpHeaders({'Content-Type': 'application/json'});
    let categories$ =  this.http.get(this.baseUrl+'/order', {headers: head}).subscribe(
      data => {},
      error => {
        this.loading = false;
      }
    );
    let products$ =  this.http.get(this.baseUrl+'/order', {headers: head}).subscribe(
      data => {},
      error => {
        this.loading = false;
      }
    );
    //
    // HERE receive JSON object asd fill "category" and "product" fields
    //
    console.log("here");
    this.orderService.createOrder(ORDERS[0]).subscribe(data=> console.log(data));

    console.log("get user orders");
    this.orderService.getUserOrders(2);

    console.log("assign");
    this.orderService.assignKeeperToOrder(2, 3).subscribe(data=> console.log(data));
  }

  // convenience getter for easy access to form fields
  get f() { return this.orderForm.controls; }

  fileChange(event) {
    // let fileList: FileList = event.target.files;
    // if(fileList.length > 0) {
    //   let file: File = fileList[0];
    //   let formData:FormData = new FormData();
    //   formData.append('uploadFile', file, file.name);
    //   let headers = new Headers();
    //   /** In Angular 5, including the header Content-Type can invalidate your request */
    //   headers.append('Content-Type', 'multipart/form-data');
    //   headers.append('Accept', 'application/json');
    //   let options = new RequestOptions({ headers: headers });
    //   this.http.post(`${this.baseUrl}`, formData, options)
    //     .map(res => res.json())
    //     .catch(error => Observable.throw(error))
    //     .subscribe(
    //       data => console.log('success'),
    //       error => console.log(error)
    //     )
    // }
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

}







