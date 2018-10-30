import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { routing } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register';
import { LoginComponent } from './login';
import { OrderComponent } from './order';

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    OrderComponent

  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
