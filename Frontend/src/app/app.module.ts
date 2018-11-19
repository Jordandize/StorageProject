import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { routing } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';
import { OrderComponent } from './order/order.component';
import { HomeComponent } from './home';
import { HeaderComponent } from './header';
import { SidebarComponent } from './sidebar';
import { UserComponent } from './userPage';
import { CabinetComponent } from './cabinet/cabinet.component';
import { MatTabsModule } from '@angular/material';
import {CustomMaterialModule} from "./material.module";

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    OrderComponent,
    HomeComponent,
    CabinetComponent,
    HeaderComponent,
    SidebarComponent,
    UserComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTabsModule,
    CustomMaterialModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
