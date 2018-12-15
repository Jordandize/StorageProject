import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule, MatGridListModule, MatCardModule, MatMenuModule, MatIconModule, MatButtonModule, MatRadioModule,
  MatBadgeModule, MatExpansionModule } from '@angular/material';
import { MatSelectModule } from '@angular/material/select';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

import { CabinetRoutingModule } from './cabinet/cabinet-routing.module';
import { routing } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';
import { OrderComponent } from './order/order.component';
import { HomeComponent } from './home';
import { HeaderComponent } from './cabinet/header/header.component';
import { SidebarComponent } from './cabinet/sidebar/sidebar.component';
import { OneOrderComponent } from './oneOrder';
import { CabinetComponent } from './cabinet/cabinet.component';
import { CustomMaterialModule } from './material.module';
import { ProductsComponent } from './products/products.component';
import { ProductsOpComponent, DialogOverviewExampleDialog } from './productsCRUD/products.component';
import { LayoutModule } from '@angular/cdk/layout';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ProductCardComponent } from './products/product-card/product-card.component';
import { ProductCardOpComponent } from './productsCRUD/product-card/product-card.component';
import { StrLimitPipe } from './products/str-limit.pipe';
import { DefaultImagePipe } from './products/default-image.pipe';
import { SessionViewerComponent } from './session-viewer/session-viewer.component';
import { PreviousRouteService } from './service-previousUrl/previous-route.service';
import { OrdersComponent } from './orders/orders.component';
import { CreateOrderComponent } from './create-order/create-order.component';
import { UserManagementComponent } from './user-management/user-management.component';
import { AdminOrdersComponent, AssignKeeper } from './adminOrders/adminOrders.component';
import { OrderQueueComponent } from './keeper/order-queue/order-queue.component';
import { QueueElemComponent } from './keeper/order-queue/queue-elem/queue-elem.component';
import { QueueColumnComponent } from './keeper/order-queue/queue-column/queue-column.component';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    OrderComponent,
    HomeComponent,
    CabinetComponent,
    ProductsComponent,
    ProductsOpComponent,
    ProductCardOpComponent,
    ProductCardComponent,
    StrLimitPipe,
    DefaultImagePipe,
    HeaderComponent,
    SidebarComponent,
 //   UserComponent,
    OneOrderComponent,
    AssignKeeper,
    AdminOrdersComponent,
    UserManagementComponent,
    OneOrderComponent,
    SessionViewerComponent,
    OrdersComponent,
    CreateOrderComponent,
    DialogOverviewExampleDialog,
    OrderQueueComponent,
    QueueElemComponent,
    QueueColumnComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatTabsModule,
    CustomMaterialModule,
    FlexLayoutModule,
    CabinetRoutingModule,
    routing,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    MatBadgeModule,
    LayoutModule,
    FlexLayoutModule,
    MatSelectModule,
    MatRadioModule,
    MatExpansionModule,
    ToastModule
  ],
  entryComponents: [AssignKeeper,DialogOverviewExampleDialog ],
  providers: [PreviousRouteService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
