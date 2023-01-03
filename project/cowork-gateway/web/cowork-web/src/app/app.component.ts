import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from './user.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  activeRoute: string;
  isLogged: boolean = false;
  error: string;

  constructor(public userService: UserService) {
    this.activeRoute = window.location.pathname.split('/')[1];
  }

  setActiveRoute(route: string) {
   this.activeRoute = route;
  }

  login() {
    this.isLogged = true;
  }

  logout() {
    this.isLogged = false;
  }
}
