import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { HttpClient, HttpParams, HttpBackend } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';
import  axios from 'axios';

// import { AlertService, AuthenticationService } from '../_services';


@Component({templateUrl: 'login.component.html'})
export class LoginComponent implements OnInit {
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
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // reset login status
        // this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.loginForm.invalid) {
            return;
        }

        this.loading = true;

        const headers = new HttpHeaders({'Content-Type': 'application/json'});

        const data = {
            'email': this.f.username.value,
            'password': this.f.password.value
        };
        //return this.http.post('http://localhost:4200/login', data, {headers: headers});

        
      return axios.post('http://localhost:8080/login', data, {headers: headers}).then((response) =>{
            
            swal({
                position: 'top-end',
                type: 'success',
                title: 'You successfully sign in!',
                showConfirmButton: false,
                timer: 1500
              })
              console.log(response.headers['x-auth-token']);
              sessionStorage.setItem('id',response.headers['x-auth-token']);
              console.log(this.f.username.value);
          this.router.navigate(['/home']);
        })
        .catch((error) => {
            if(error.status==401){
            swal({
                type: 'error',
                title: 'Error!',
                text:"Такого користувача не існує" 
              })
            }
          this.loading = false;
        }
      );


    }
}
