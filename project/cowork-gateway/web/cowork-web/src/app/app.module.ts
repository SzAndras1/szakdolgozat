import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AdwertisingTestComponent} from './adwertising-test/adwertising-test.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import {HttpClientModule} from '@angular/common/http';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatDialogModule} from "@angular/material/dialog";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import { AdvertisingDetailsComponent } from './advertising-details/advertising-details.component';
import { AdvertisingInputErrorMessagesComponent } from './advertising-input-error-messages/advertising-input-error-messages.component';
import { AdvertisingCreateComponent } from './advertising-create/advertising-create.component';
import { AdvertisingListByUseridComponent } from './advertising-list-by-userid/advertising-list-by-userid.component';
import { AdvertisingFavoritesComponent } from './advertising-favorites/advertising-favorites.component';
import {CdkAccordionModule} from "@angular/cdk/accordion";
import {MatExpansionModule} from "@angular/material/expansion";
import { PopUpDialogComponent } from './pop-up-dialog/pop-up-dialog.component';
import { CommentDisplayComponent } from './comment-display/comment-display.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatChipsModule} from "@angular/material/chips";

@NgModule({
  declarations: [
    AppComponent,
    AdwertisingTestComponent,
    AdvertisingDetailsComponent,
    AdvertisingInputErrorMessagesComponent,
    AdvertisingCreateComponent,
    AdvertisingListByUseridComponent,
    AdvertisingFavoritesComponent,
    PopUpDialogComponent,
    CommentDisplayComponent,
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
    ReactiveFormsModule,
    CdkAccordionModule,
    MatExpansionModule,
    MatProgressSpinnerModule,
    MatSlideToggleModule,
    MatChipsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
