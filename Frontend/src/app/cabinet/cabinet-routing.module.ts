import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SessionViewerComponent } from '../session-viewer/session-viewer.component';
import { CabinetComponent } from './cabinet.component';
import { ProductsComponent } from '../products/products.component';
import { ProductsOpComponent } from '../productsCRUD/products.component';
import { OrderComponent } from '../order/order.component';
import { OrdersComponent } from '../orders/orders.component';
import { OneOrderComponent } from '../oneOrder';
import { CreateOrderComponent } from '../create-order/create-order.component';
import { UserManagementComponent } from '../user-management/user-management.component';
import { OrderQueueComponent } from '../keeper/order-queue/order-queue.component';
import { AdminOrdersComponent } from '../adminOrders/adminOrders.component';
import { CategoriesComponent } from '../categories/categories.component';

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
        path: 'categories',
        component: CategoriesComponent
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
        path: 'adminOrders',
        component: AdminOrdersComponent
      },
      {
        path: 'order',
        component: OrderComponent
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
