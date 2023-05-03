import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthService } from '../security/auth.service';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class LoginActiveService implements CanActivate{

  constructor(private auth: AuthService,
              private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.auth.isLogin()){
      this.router.navigateByUrl("home")
      return false
    }
    return true;
  }
}
