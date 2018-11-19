import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FlexLayoutModule} from '@angular/flex-layout';

import { routing } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';
import { OrderComponent } from './order';
import { HomeComponent } from './home';
import { HeaderComponent } from './header';
import { SidebarComponent } from './sidebar';
import { UserComponent } from './userPage';
import { OneOrderComponent } from './oneOrder';
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
    UserComponent,
    OneOrderComponent

  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatTabsModule,
    CustomMaterialModule,
    FlexLayoutModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
