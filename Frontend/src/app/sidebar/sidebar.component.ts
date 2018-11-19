import { Component, OnInit,Input } from '@angular/core';
import { first } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableDataSource} from '@angular/material';
import "@angular/material/prebuilt-themes/indigo-pink.css";


export class Tab {
  id: number;
  name: string;
  url:string;
  constructor(public id2:number, public name2:string, public url2:string) {
    this.id = id2;
    this.name=name2;
    this.url = url2;
 }
}
@Component({ selector: 'app-sidebar',templateUrl: 'sidebar.component.html'})
export class SidebarComponent implements OnInit {

  @Input() public  tabs:Tab[];
    

  constructor(
    private http: HttpClient) {}

    ngOnInit() {
    }
}
