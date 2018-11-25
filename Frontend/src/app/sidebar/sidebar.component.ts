import { Component, OnInit,Input } from '@angular/core';
import { first } from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {MatTabsModule} from '@angular/material/tabs';
import {MatTableDataSource} from '@angular/material';
import "@angular/material/prebuilt-themes/indigo-pink.css";
import { PreviousRouteService } from '../service-previousUrl/previous-route.service';
import { baseUrl } from '../../varUrl';


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
@Component({ selector: 'app-sidebar',templateUrl: 'sidebar.component.html',styleUrls: ['./sidebar.component.css']})
export class SidebarComponent implements OnInit {
  baseUrl = baseUrl;
  public  tabs : Tab[] ;
  public role = sessionStorage.getItem('role');
myFunc():void{
  console.log(this.previousRouteService.getPreviousUrl());
  this.router.navigate(['/'+this.previousRouteService.getPreviousUrl()]); 
    }

  constructor(
    private previousRouteService: PreviousRouteService,
    private http: HttpClient, private router: Router) {}

    async ngOnInit() {
      await  this.http.get(this.baseUrl+"/api/tabs").subscribe(data => {

        this.tabs=<Tab[]> data;
      
            },   error => {
              console.log(error);
          }
          );
    }
}
