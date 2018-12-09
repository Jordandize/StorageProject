import {NgModule} from "@angular/core";
import { CommonModule } from '@angular/common';
import {
  MatButtonModule, MatNativeDateModule, MatIconModule, MatSidenavModule, MatListModule, MatToolbarModule,MatCardModule, MatMenuModule,MatFormFieldModule,MatTableModule,MatInputModule,MatPaginatorModule,MatGridListModule,MatDialogModule, MatSlideToggleModule} from '@angular/material';

@NgModule({
  imports: [CommonModule, MatButtonModule,MatToolbarModule, MatNativeDateModule, MatIconModule,MatCardModule, MatMenuModule, MatSidenavModule, MatListModule,MatFormFieldModule,MatTableModule,MatInputModule,MatPaginatorModule,MatGridListModule,MatDialogModule, MatSlideToggleModule],
  exports: [CommonModule, MatButtonModule, MatToolbarModule, MatNativeDateModule, MatIconModule,MatCardModule, MatMenuModule, MatSidenavModule, MatListModule,MatFormFieldModule,MatTableModule,MatInputModule,MatPaginatorModule,MatGridListModule,MatDialogModule, MatSlideToggleModule],
})
export class CustomMaterialModule { }