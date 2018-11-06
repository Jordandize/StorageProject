import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {validate} from "codelyzer/walkerFactory/walkerFn";

@Component({templateUrl: 'order.component.html'})
export class OrderComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;




  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
    // private authenticationService: AuthenticationService,
    // private alertService: AlertService
  ) {}
  ngOnInit() {
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
    return this.http.get('http://localhost:4200/order', {headers: head}).subscribe(
      data => {},
      error => {
        this.loading = false;
      }
    );
    //
    //HERE receive JSON object asd fill "category" and "product" fields
    //
  }

  // convenience getter for easy access to form fields
  get f() { return this.orderForm.controls; }

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
    return this.http.post('http://localhost:4200/order', order, {headers: head}).subscribe(
      data => {
        this.router.navigate(['/home']);
      },
      error => {
        this.loading = false;
      }
    );
  }

}







