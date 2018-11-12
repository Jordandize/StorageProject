import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';

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

        const loginForm = {
            'email': this.f.username.value,
            'password': this.f.password.value
        };

        console.log(33);
      return this.http.post('https://storage-pro.herokuapp.com/login', loginForm, {headers: headers, observe: 'response'})
        .subscribe(
            (data) => {
            console.log(44);
            swal({
                position: 'top-end',
                type: 'success',
                title: 'You successfully sign in!',
                showConfirmButton: false,
                timer: 1500
            });
            console.log(55);
            console.log(data);
            sessionStorage.setItem('id', data.headers['x-auth-token']);
            sessionStorage.setItem('email', this.f.username.value);
            console.log(this.f.username.value);
            this.router.navigate(['/home']);
        },
        error => {
            if (error.status === 401) {
                swal({
                    type: 'error',
                    title: 'Error!',
                    text: 'Такого користувача не існує'
                });
            } else {
            console.log(error);
            }
            this.loading = false;
        });
    }
}
