import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableDataSource} from '@angular/material';
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
export interface Order {
  orderId: number;
  status: string;
  comment: string;
}
const ELEMENT_DATA: Order[] = [
  {orderId: 1, status: 'Ready', comment: 'A'},
  {orderId: 2, status:  'Ready',  comment: 'B'},
  {orderId: 3, status:  'Ready',  comment: 'C'},
  {orderId: 4, status:  'Ready',  comment: 'D'},
  {orderId: 5, status:  'Ready',  comment: 'E'},
  {orderId: 6, status:  'Ready',  comment: 'G'},
  {orderId: 7, status:  'Ready', comment: '12'},
  {orderId: 8, status:  'Ready', comment: '123'},
  {orderId: 9, status:  'Ready',  comment: '23123'},
  {orderId: 10, status:  'Ready',  comment: 'abcds'},
];

@Component({templateUrl: 'home.component.html'})
export class HomeComponent implements OnInit {
  
    currentUser: User;
    displayedColumns: string[] = ['orderId', 'status', 'comment'];
    dataSource = new MatTableDataSource(ELEMENT_DATA);
  
    applyFilter(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();
    }
    myFunc():void{
      sessionStorage.removeItem('id');
      console.log("Works");
    }
  
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
