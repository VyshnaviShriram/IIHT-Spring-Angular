import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataServiceService } from 'src/app/Services/user/data-service.service';
import { UserServiceService } from 'src/app/Services/user/user-service.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  navSelected: string=location.href.split('/')[location.href.split('/').length-1];
  public isLoggedIn() {
    return this.dataService.getToken();
  }

  public logout() {
    window.sessionStorage.clear();
    this.router.navigate(['']);
  }
  
  constructor(private router: Router,
              public userService: UserServiceService,
              private dataService: DataServiceService) { }

  ngOnInit(): void {
  }

}
