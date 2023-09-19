import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { DataServiceService } from '../Services/user/data-service.service';
import { UserServiceService } from '../Services/user/user-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate {

  constructor(
    private dataService: DataServiceService,
    private router: Router,
    private userService: UserServiceService
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if (this.dataService.getToken() !== null) {
      const role = route.data['role'] as string;

      if (role) {
        const match = this.dataService.roleMatch(role);

        if (match) {
          return true;
        } else {
          this.router.navigate(['/login']);
          alert("You are not allowed to access this page")
          return false;
        }
      }
    }

    this.router.navigate(['/login']);
    return false;
  }

}
