import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdwertisingTestComponent} from './adwertising-test/adwertising-test.component';
import {AdvertisingCreateComponent} from "./advertising-create/advertising-create.component";
import {AdvertisingDetailsComponent} from "./advertising-details/advertising-details.component";
import {AdvertisingListAdsComponent} from "./advertising-list-ads/advertising-list-ads.component";

const routes: Routes = [
  {path: 'advertising', component: AdwertisingTestComponent},
  {path: 'advertising/add', component: AdvertisingCreateComponent},
  {path: 'advertising/:id', component: AdvertisingDetailsComponent},
  {path: 'advertising/list-ads', component: AdvertisingListAdsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
