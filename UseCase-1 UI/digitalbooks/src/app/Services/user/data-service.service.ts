import { Injectable } from '@angular/core';
import { User } from 'src/app/Entity/user';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  public getToken() {
    return window.sessionStorage.getItem('token');
  }

  public setToken(token: string) {
    window.sessionStorage.setItem('token', token);
  }

  public getRole() {
    return window.sessionStorage.getItem('role');
  }

  public setRole(role: string) {
    window.sessionStorage.setItem('role', role);
  }

  public getUserid() {
    return window.sessionStorage.getItem('userid');
  }

  public setUserid(userid: string) {
    window.sessionStorage.setItem('userid', userid);
  }

  public roleMatch(allowedRole): boolean {
    let isMatch = false;
    const userRole: string = this.getRole();
    if (userRole === allowedRole) {
      isMatch = true;
      return isMatch;
    } else {
      return isMatch;
    }
  }

  constructor() { }
}
