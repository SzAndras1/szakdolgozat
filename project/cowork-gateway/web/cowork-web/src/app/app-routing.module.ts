import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdwertisingTestComponent} from './adwertising-test/adwertising-test.component';
import {AdvertisingCreateComponent} from "./advertising-create/advertising-create.component";
import {AdvertisingRemoveComponent} from "./advertising-remove/advertising-remove.component";
import {AdvertisingUpdateComponent} from "./advertising-update/advertising-update.component";
import {AdvertisingDetailsComponent} from "./advertising-details/advertising-details.component";

const routes: Routes = [
  {path: 'advertising', component: AdwertisingTestComponent},
  {path: 'advertising/add', component: AdvertisingCreateComponent},
  {path: 'advertising/remove', component: AdvertisingRemoveComponent},
  {path: 'advertising/:id', component: AdvertisingDetailsComponent},
  {path: 'advertising/update/:id', component: AdvertisingUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
