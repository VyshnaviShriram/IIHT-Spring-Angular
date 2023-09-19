import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  public getUserName() {
    return window.sessionStorage.getItem('username');
  }

  public setUserName(name: string) {
    window.sessionStorage.setItem('username', name);
  }

  public getToken() {
    return window.sessionStorage.getItem('token');
  }

  public setToken(token: string) {
    window.sessionStorage.setItem('token', token);
  }

  public getUserid() {
    return window.sessionStorage.getItem('userid');
  }

  public setUserid(userid: string) {
    window.sessionStorage.setItem('userid', userid);
  }

    constructor() { }
}
