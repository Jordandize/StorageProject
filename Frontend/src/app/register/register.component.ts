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
            passwordC: ['', [Validators.required, Validators.minLength(6)]],
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
        const head = new HttpHeaders({'Content-Type': 'application/json'});

        const user = {
            'name': this.f.firstName.value,
            'surname': this.f.lastName.value,
            'password': this.f.password.value,
            'passwordRepeat': this.f.passwordC.value,
            'email': this.f.email.value,
            'phone': this.f.phone.value
        };
        const api = "https://storage-site.herokuapp.com"
        return this.http.post('https://storage-site.herokuapp.com/users', user, {headers: head}).subscribe(
         data => {
                    this.router.navigate(['/login']);
                },
                error => {
                    this.loading = false;
                }
                );
    }
}
