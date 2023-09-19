import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { JwtResponse } from 'src/app/Entity/jwtResponse';
import { User } from 'src/app/Entity/user';
import { DataServiceService } from 'src/app/Services/user/data-service.service';
import { UserServiceService } from 'src/app/Services/user/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  user: User=new User();
  role: string='';
  jwtResponse: JwtResponse;
  errorMessage: string = '';
  isLoggedIn: boolean;
  isLoginFailed: boolean;

 validate(loginForm){
  if(loginForm.valid){
    this.login(loginForm);
  }
}

  login(loginForm){
    const promise = this.userService.loginUser(loginForm.value);
    promise.subscribe((response:any)=>{
      this.isLoggedIn=true;
      this.errorMessage="";
      this.jwtResponse = response;
      //console.log(response);
      this.dataService.setToken(response.jwtToken);
      this.dataService.setUserid(response.user.userid);
       this.role = response.user.role;
       this.dataService.setRole(this.role);
      // if (this.role === 'Author') {
      //   this.router.navigate(['/addBook']);
      // } else if (this.role === 'Reader') {
      //   this.router.navigate(['/getBooks']);
      // }
    },(error)=>{
      this.isLoginFailed = true;
      console.log(error);
      if(error.status == 403){
        this.errorMessage="Please provide valid credentials";
      }
      
    })
  }
  constructor(private userService: UserServiceService,
              private router: Router,
              private dataService: DataServiceService) { }

  ngOnInit(): void {
    if (this.dataService.getToken()) {
      this.isLoggedIn = true;
      this.role = this.dataService.getRole();
    }
  }

}
