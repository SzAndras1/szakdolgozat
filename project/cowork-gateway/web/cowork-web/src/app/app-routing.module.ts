import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdwertisingTestComponent} from './adwertising-test/adwertising-test.component';
import {AdvertisingDetailsComponent} from './advertising-details/advertising-details.component';

const routes: Routes = [
 {path: 'advertising', component: AdwertisingTestComponent},
 { path: 'advertising/add', component: AdvertisingDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
