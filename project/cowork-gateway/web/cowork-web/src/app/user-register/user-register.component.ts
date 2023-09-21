import {Component, OnInit} from '@angular/core';
import {UserDto, UserService} from "../generated";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.scss']
})
export class UserRegisterComponent implements OnInit {
  userForm: FormGroup;

  constructor(public userService: UserService,
              private fb: FormBuilder,
              private router: Router) {
  }

  ngOnInit(): void {
    this.userForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  register(): void {
    this.userService.register(this.userForm.value as UserDto)
      .pipe(catchError(err => {
        console.log(err);
        return throwError(err);
      }))
      .subscribe((userDto: UserDto) => {
        console.log(userDto);
        this.router.navigate(['login']);
      });
  }
}
