import {CanMatchFn, Router} from "@angular/router";
import {inject} from "@angular/core";
import {tap} from "rxjs";
import {AuthenticateService} from "../services/authenticate.service";

export const canMatchGuardFn: CanMatchFn = () => {
  const router = inject(Router);
  return inject(AuthenticateService)
    .subjectIsLoggedIn
    .pipe(tap((isLoggedIn) => !isLoggedIn && router.navigate(['advertising'])))
}
