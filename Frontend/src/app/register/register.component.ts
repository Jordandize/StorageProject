import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import swal from 'sweetalert2';
import { baseUrl } from '../../varUrl';

import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';

interface Alert2 {
    message: string;
  }


@Component({templateUrl: 'register.component.html'})
export class RegisterComponent implements OnInit {
    registerForm: FormGroup;
    loading = false;
    submitted = false;

    baseUrl = baseUrl;
    
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
        return this.http.post(this.baseUrl+"/users", user, {headers: head}).subscribe(
         data => {
              swal({
                position: 'top-end',
                type: 'success',
                title: 'You successfully registered!',
                showConfirmButton: false,
                timer: 1500
              });
                    this.router.navigate(['/login']);
                },
                error => {
                    console.log(error);

                    swal({
                        type: 'error',
                        title: 'Error!',

                        text:error.error.errors ? JSON.stringify(error.error.errors.email) +". "+ (error.error.global ?  JSON.stringify(error.error.global.signupFormDto): "" ) :""  
                      })
                   
                    this.loading = false;
                }
                );
    }
}
