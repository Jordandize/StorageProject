import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';
import { OrderComponent } from './order';
import { HomeComponent } from './home';
import { UserComponent } from './userPage';
import { OneOrderComponent } from './oneOrder';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
 {
    path: 'register',
   component: RegisterComponent
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'order',
    component: OrderComponent
  },
  {
    path: 'user',
    component: UserComponent
  }
  ,
  {
    path: 'oneOrder/:id',
    component: OneOrderComponent
  }
  //{
  //  path: 'user',
  //  component: CabinetComponent
 // }

];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
