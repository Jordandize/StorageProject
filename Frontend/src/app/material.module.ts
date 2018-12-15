import {NgModule} from "@angular/core";
import { CommonModule } from '@angular/common';
import {
  MatButtonModule, MatNativeDateModule, MatIconModule, MatSidenavModule, MatListModule, MatToolbarModule,MatCardModule, MatMenuModule,MatFormFieldModule,MatTableModule,MatInputModule,MatPaginatorModule,MatGridListModule,MatDialogModule, MatSlideToggleModule,MatSortModule} from '@angular/material';

@NgModule({
  imports: [CommonModule, MatButtonModule,MatToolbarModule, MatNativeDateModule, MatIconModule,MatCardModule, MatMenuModule, MatSidenavModule, MatListModule,MatFormFieldModule,MatTableModule,MatInputModule,MatPaginatorModule,MatGridListModule,MatDialogModule, MatSlideToggleModule,MatSortModule],
  exports: [CommonModule, MatButtonModule, MatToolbarModule, MatNativeDateModule, MatIconModule,MatCardModule, MatMenuModule, MatSidenavModule, MatListModule,MatFormFieldModule,MatTableModule,MatInputModule,MatPaginatorModule,MatGridListModule,MatDialogModule, MatSlideToggleModule,MatSortModule],
})
export class CustomMaterialModule { }