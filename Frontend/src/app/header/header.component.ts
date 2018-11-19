import { Component, OnInit, Input } from '@angular/core';
import { first } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableDataSource} from '@angular/material';
import "@angular/material/prebuilt-themes/indigo-pink.css";





@Component({ selector: 'app-header',templateUrl: 'header.component.html'})
export class HeaderComponent implements OnInit {
  @Input() public session = sessionStorage.getItem('email');

    myFunc():void{
  //    sessionStorage.removeItem('id');
      console.log("Works");
    }
  
   

  constructor(
    private http: HttpClient) {}

    ngOnInit() {
      
     
  

    }

}
