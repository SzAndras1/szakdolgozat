import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdwertisingTestComponent} from './adwertising-test/adwertising-test.component';
import {AdvertisingCreateComponent} from "./advertising-create/advertising-create.component";
import {AdvertisingDetailsComponent} from "./advertising-details/advertising-details.component";
import {AdvertisingListByUseridComponent} from "./advertising-list-by-userid/advertising-list-by-userid.component";
import {AdvertisingFavoritesComponent} from "./advertising-favorites/advertising-favorites.component";

//TODO: miért hozza be detail componenst, ha a favorites van legalul?
const routes: Routes = [
  {path: 'advertising', component: AdwertisingTestComponent},
  {path: 'advertising/favorites', component: AdvertisingFavoritesComponent},
  {path: 'advertising/add', component: AdvertisingCreateComponent},
  {path: 'advertising/:id', component: AdvertisingDetailsComponent},
  {path: 'advertising/user/:userId', component: AdvertisingListByUseridComponent},
  {path: '**', redirectTo: 'advertising' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
