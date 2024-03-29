import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {AdvertisingMainComponent} from './advertising-main/advertising-main.component';
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
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import { AdvertisingDetailsComponent } from './advertising-details/advertising-details.component';
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
import {MatSliderModule} from "@angular/material/slider";
import {CdkColumnDef} from "@angular/cdk/table";
import {HistoryPageComponent} from './history-page/history-page.component';
import {NgbRatingModule} from "@ng-bootstrap/ng-bootstrap";
import {MatListModule} from "@angular/material/list";
import {FileSizePipe} from './pipes/file-size.pipe';
import {AuthInterceptor} from "./interceptor/auth.interceptor";
import { UserRegisterComponent } from './user-register/user-register.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { StringCutPipe } from './pipes/string-cut.pipe';

@NgModule({
  declarations: [
    AppComponent,
    AdvertisingMainComponent,
    AdvertisingDetailsComponent,
    AdvertisingCreateComponent,
    AdvertisingListByUseridComponent,
    AdvertisingFavoritesComponent,
    PopUpDialogComponent,
    CommentDisplayComponent,
    HistoryPageComponent,
    FileSizePipe,
    UserRegisterComponent,
    UserLoginComponent,
    StringCutPipe,
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
    MatSliderModule,
    NgbRatingModule,
    MatListModule
  ],
  providers: [CdkColumnDef, { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },],
  bootstrap: [AppComponent]
})
export class AppModule { }
