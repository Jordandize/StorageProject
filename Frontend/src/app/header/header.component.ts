import { Component, OnInit, Input } from '@angular/core';
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


@Component({ selector: 'app-header',templateUrl: 'header.component.html'})
export class HeaderComponent implements OnInit {
  @Input() public session = sessionStorage.getItem('email');

    myFunc():void{
      sessionStorage.removeItem('id');
      console.log("Works");
    }
  
   

  constructor(
    private http: HttpClient,
    private formBuilder: FormBuilder,
    private router: Router) {}

    ngOnInit() {
      
     
  

    }

}
