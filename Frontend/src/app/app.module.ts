import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatTabsModule, MatGridListModule, MatCardModule, MatMenuModule, MatIconModule, MatButtonModule } from '@angular/material';
import { MatSelectModule } from '@angular/material/select';

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
import { CustomMaterialModule } from './material.module';
import { ProductsComponent } from './products/products.component';
import { DashTestComponent } from './dash-test/dash-test.component';
import { LayoutModule } from '@angular/cdk/layout';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ProductCardComponent } from './products/product-card/product-card.component';
import { StrLimitPipe } from './products/str-limit.pipe';
import { DefaultImagePipe } from './products/default-image.pipe';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    OrderComponent,
    HomeComponent,
    CabinetComponent,
    ProductsComponent,
    DashTestComponent,
    ProductCardComponent,
    StrLimitPipe,
    DefaultImagePipe,
    HeaderComponent,
    SidebarComponent,
    UserComponent,
    OneOrderComponent

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
    routing,
    MatGridListModule,
    MatCardModule,
    MatMenuModule,
    MatIconModule,
    MatButtonModule,
    LayoutModule,
    FlexLayoutModule,
    MatSelectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
