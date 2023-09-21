import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  private authenticateUrl: string = 'http://localhost:8082/api/v1/authentication';

  subjectIsLoggedIn: Subject<boolean> = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {
  }

  logout(): Observable<any> {
    return this.http.post(this.authenticateUrl + '/logout', {});
  }
}
