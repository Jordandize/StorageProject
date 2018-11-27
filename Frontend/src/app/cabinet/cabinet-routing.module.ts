import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SessionViewerComponent } from '../session-viewer/session-viewer.component';
import { CabinetComponent } from './cabinet.component';
import { ProductsComponent } from '../products/products.component';
import { OrderComponent } from '../order/order.component';
import { OrdersComponent } from '../orders/orders.component';
import { OneOrderComponent } from '../oneOrder';
import { CreateOrderComponent } from '../create-order/create-order.component';

const cabinetRoutes: Routes = [
  {
    path: 'cabinet',
    component: CabinetComponent,
    children: [
      {
        path: '',
        redirectTo: 'products',
        pathMatch: 'full'
      },
      {
        path: 'products',
        component: ProductsComponent
      },
      {
        path: 'create-order',
        component: CreateOrderComponent
      },
      {
        path: 'orders',
        component: OrdersComponent
      },
      {
        path: 'orders/:id',
        component: OneOrderComponent
      },
      {
        path: 'session',
        component: SessionViewerComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(cabinetRoutes)],
  exports: [RouterModule]
})
export class CabinetRoutingModule { }
