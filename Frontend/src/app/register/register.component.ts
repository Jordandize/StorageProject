import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


@Component({templateUrl: 'register.component.html'})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;

    constructor(
        private http: HttpClient,
        private formBuilder: FormBuilder,
        private router: Router) { }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6)]],
            email: ['', Validators.required],
            phone: ['', Validators.required]

        });
    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.registerForm.invalid) {
            return;
        }

        this.loading = true;
        let head = new Headers({'Content-Type': ' application/x-www-form-urlencoded'});

        let user= {
            "firstName":this.f.firstName.value,
            "lastName":this.f.lastName.value,
            "password":this.f.password.value,
            "email":this.f.email.value,
            "phone":this.f.phone.value
        };
        return this.http.post('http://localhost:4200/register',user,{head}).pipe(
    catchError(this.handleError('addUser', user))
  );;
    }
}
