import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SessionViewerComponent } from '../session-viewer/session-viewer.component';
import { CabinetComponent } from './cabinet.component';
import { ProductsComponent } from '../products/products.component';
import { ProductsOpComponent } from '../productsCRUD/products.component';
import { OrdersComponent } from '../orders/orders.component';
import { OneOrderComponent } from '../oneOrder';
import { CreateOrderComponent } from '../create-order/create-order.component';
import { UserManagementComponent } from '../user-management/user-management.component';
import { OrderQueueComponent } from '../keeper/order-queue/order-queue.component';

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
        path: 'productsOperations',
        component: ProductsOpComponent
      },
      {
        path: 'create-order',
        component: CreateOrderComponent
      },
      {
        path: 'user-management',
        component: UserManagementComponent
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
        path: 'queue',
        component: OrderQueueComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(cabinetRoutes)],
  exports: [RouterModule]
})
export class CabinetRoutingModule { }
