import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";

class User {
  id: number;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
}

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
    currentUser: User;
    users: User[] = [];

    // constructor(private userService: UserService) {
  //     //     this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  //     // }

  constructor(

    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router) {}

    ngOnInit() {

      return this.http.get('http://localhost:8080/users', user, {headers: head}).subscribe(
        data => {
          this.currentUser = data;
        },
        error => {
          this.loading = false;
        }
      );

    }

    deleteUser(id: number) {
        this.userService.delete(id).pipe(first()).subscribe(() => { 
            this.loadAllUsers() 
        });
    }

    private loadAllUsers() {
        this.userService.getAll().pipe(first()).subscribe(users => { 
            this.users = users; 
        });
    }
}
