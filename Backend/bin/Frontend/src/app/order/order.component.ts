import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

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
  }
}


