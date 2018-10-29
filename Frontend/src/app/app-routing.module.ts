import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';

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
  }
];

export const routing = RouterModule.forRoot(routes);

export class AppRoutingModule { }
