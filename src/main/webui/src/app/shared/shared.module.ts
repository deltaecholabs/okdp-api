import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { PageErrorsComponent } from './page-errors/page-errors.component';


@NgModule({
  declarations: [
    NavbarComponent,
    PageErrorsComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    ReactiveFormsModule,
    NgbModule
  ],
  exports: [
    RouterModule,
    ReactiveFormsModule,
    NgbModule,
    NavbarComponent,
    PageErrorsComponent
  ]
})
export class SharedModule { }
