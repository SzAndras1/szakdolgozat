import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdvertisingComponent} from './components/advertising/advertising.component';

const routes: Routes = [
  {path: 'advertising', component: AdvertisingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
