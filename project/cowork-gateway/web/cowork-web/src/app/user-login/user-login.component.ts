import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthenticationResponseDto, UserDto, UserService} from "../generated";
import {AuthenticateService} from "../services/authenticate.service";
import {CookieService} from "ngx-cookie-service";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.scss']
})
export class UserLoginComponent implements OnInit {
  userForm: FormGroup;

  constructor(public userService: UserService,
              public authenticateService: AuthenticateService,
              private fb: FormBuilder,
              private cookieService: CookieService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.userForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  login(): void {
    this.userService.login(this.userForm.value as UserDto)
      .pipe(catchError(err => {
        console.log(err);
        return throwError(err);
      }))
      .subscribe((authenticationResponseDto: AuthenticationResponseDto) => {
        console.log(authenticationResponseDto);
        const token: string = authenticationResponseDto.token;
        const expirationDate: number = JSON.parse(
          atob(token.split('.')[1]))
          .exp;
        this.authenticateService.subjectIsLoggedIn.next(true);
        this.cookieService.set('token', token, {expires: new Date(expirationDate * 1000)});
        this.router.navigate(['advertising']);
      });
  }
}
