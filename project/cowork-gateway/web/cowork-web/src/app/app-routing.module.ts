import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdvertisingMainComponent} from './advertising-main/advertising-main.component';
import {AdvertisingCreateComponent} from "./advertising-create/advertising-create.component";
import {AdvertisingDetailsComponent} from "./advertising-details/advertising-details.component";
import {AdvertisingListByUseridComponent} from "./advertising-list-by-userid/advertising-list-by-userid.component";
import {AdvertisingFavoritesComponent} from "./advertising-favorites/advertising-favorites.component";
import {HistoryPageComponent} from "./history-page/history-page.component";
import {canMatchGuardFn} from "./guards/canMatchGuard";
import {UserRegisterComponent} from "./user-register/user-register.component";
import {UserLoginComponent} from "./user-login/user-login.component";

const routes: Routes = [
  {path: 'advertising', component: AdvertisingMainComponent},
  {path: 'register', component: UserRegisterComponent},
  {path: 'login', component: UserLoginComponent},
  {path: 'advertising/favorites', component: AdvertisingFavoritesComponent, canMatch: [canMatchGuardFn]},
  {path: 'advertising/add', component: AdvertisingCreateComponent, canMatch: [canMatchGuardFn]},
  {path: 'advertising/history', component: HistoryPageComponent, canMatch: [canMatchGuardFn]},
  {path: 'advertising/user/:userId', component: AdvertisingListByUseridComponent, canMatch: [canMatchGuardFn]},
  {path: 'advertising/:id', component: AdvertisingDetailsComponent},
  {path: '**', redirectTo: 'advertising' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
