import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userId: number = 1;
  username: string = "admin";
  password: string = "admin";

  constructor() { }
}
