import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import "@angular/material/prebuilt-themes/indigo-pink.css";


export class User {
  id: number;
  username: string;
  url:string;
  constructor(public id2:number, public username2:string, public url2:string) {
    this.id = id2;
    this.username=username2;
    this.url = url2;
 }
}
export class Current {
  email: string;
  constructor(public email2:string) {
    this.email = email2;
 }
}

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
    currentUser: User;
     session = sessionStorage.getItem('email');
    public  users: [User,User,User] = [ 
      new User(1,'CreateOrder','/order'), 
      new User(2,'List of Orders','/listOrders'), 
      new User(3,'List of Products','/listProducts'),
];
    // constructor(private userService: UserService) {
  //     //     this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  //     // }

  constructor(

    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router) {}

    ngOnInit() {
      console.log(sessionStorage.getItem('email'));
     // destroy(){
  //      sessionStorage.removeItem('email');
  //    }
      // return this.http.get('http://localhost:8080/users', user, {headers: head}).subscribe(
      //   data => {
      //     this.currentUser = data;
      //   },
      //   error => {
      //     this.loading = false;
      //   }
      // );

    }

    // deleteUser(id: number) {
    //     this.userService.delete(id).pipe(first()).subscribe(() => {
    //         this.loadAllUsers()
    //     });
    // }

    // private loadAllUsers() {
    //     this.userService.getAll().pipe(first()).subscribe(users => {
    //         this.users = users;
    //     });
    // }
}
