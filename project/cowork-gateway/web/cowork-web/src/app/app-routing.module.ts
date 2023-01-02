import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {AdwertisingTestComponent} from './adwertising-test/adwertising-test.component';

const routes: Routes = [
 {path: 'advertising', component: AdwertisingTestComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
