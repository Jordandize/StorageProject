import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';
import { OrderComponent } from './order';
import { HomeComponent } from './home';
import { CabinetComponent } from './cabinet';
import { ProductsComponent } from './products/products.component';
import { DashTestComponent } from './dash-test/dash-test.component';
import { ProductCardComponent } from './products/product-card/product-card.component';
import { UserComponent } from './userPage';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'order', component: OrderComponent },
  { path: 'user', component: UserComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'dashtest', component: DashTestComponent },
  { path: 'pr-card', component: ProductCardComponent }
];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
