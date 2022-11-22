import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  activeRoute: string;

  constructor() {
    this.activeRoute = window.location.pathname.split('/')[1];
  }

  setActiveRoute(route: string) {
    this.activeRoute = route;
  }
}
