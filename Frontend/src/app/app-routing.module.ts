import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';
import { ProductCardComponent } from './products/product-card/product-card.component';
import { OneOrderComponent } from './oneOrder';
import { CabinetComponent } from './cabinet/cabinet.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cabinet', component: CabinetComponent },
  { path: 'pr-card', component: ProductCardComponent },
  { path: 'oneOrder/:id', component: OneOrderComponent }
];

export const routing = RouterModule.forRoot(routes);

@NgModule({
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
