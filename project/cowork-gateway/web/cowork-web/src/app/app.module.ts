import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdwertisingTestComponent } from './adwertising-test/adwertising-test.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatDialogModule} from "@angular/material/dialog";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS, MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import { AdvertisingDetailsComponent } from './advertising-details/advertising-details.component';
import { AdvertisingInputErrorMessagesComponent } from './advertising-input-error-messages/advertising-input-error-messages.component';
import { AdvertisingCreateComponent } from './advertising-create/advertising-create.component';
import { AdvertisingRemoveComponent } from './advertising-remove/advertising-remove.component';
import { AdvertisingUpdateComponent } from './advertising-update/advertising-update.component';

@NgModule({
  declarations: [
    AppComponent,
    AdwertisingTestComponent,
    AdvertisingDetailsComponent,
    AdvertisingInputErrorMessagesComponent,
    AdvertisingCreateComponent,
    AdvertisingRemoveComponent,
    AdvertisingUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatTooltipModule,
    MatIconModule,
    MatCardModule,
    MatInputModule,
    MatSnackBarModule,
    FormsModule,
    MatButtonModule,
    MatTableModule,
    MatPaginatorModule,
    MatDialogModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  providers: [{provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {floatLabel: 'always'}}],
  bootstrap: [AppComponent]
})
export class AppModule { }
