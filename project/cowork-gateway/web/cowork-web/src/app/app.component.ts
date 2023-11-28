import {Component, OnInit} from '@angular/core';
import {AuthenticateService} from "./services/authenticate.service";
import {CookieService} from "ngx-cookie-service";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  activeRoute: string;
  error: string;
  isLogged: boolean = false;

  constructor(public authenticateService: AuthenticateService,
              private cookieService: CookieService,
              private snackBar: MatSnackBar) {
    this.activeRoute = window.location.pathname.split('/')[1];
  }

  setActiveRoute(route: string): void {
    this.activeRoute = route;
  }

  ngOnInit(): void {
    this.authenticateService.subjectIsLoggedIn.subscribe(
      (value: boolean) => this.isLogged = value);
  }

  // TODO: logout switch to front page or refresh queried ads
  logout(): void {
    this.authenticateService.logout()
      .subscribe(() => {
        this.openSnackBar('Successful logout!', 'Close')
        this.authenticateService.subjectIsLoggedIn.next(false);
        this.cookieService.delete('token');
        console.log('Successful logout!')
      });
  }

  openSnackBar(message: string, action: string): void {
    let durationInSeconds: number = 3;
    this.snackBar.open(message, action, {duration: durationInSeconds * 1000, verticalPosition: 'top'},);
  }
}
