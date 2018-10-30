import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';
import { OrderComponent } from './order';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'register',
    pathMatch: 'full'
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'order',
    component: OrderComponent
  }

];

export const routing = RouterModule.forRoot(routes);

export class AppRoutingModule { }
