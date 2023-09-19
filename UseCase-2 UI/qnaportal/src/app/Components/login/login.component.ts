import { Component, OnInit } from '@angular/core';
import { JwtResponse } from 'src/app/Entity/jwtResponse';
import { DataServiceService } from 'src/app/Services/data-service.service';
import { UserServiceService } from 'src/app/Services/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

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
    console.log(response);
   this.dataService.setToken(response.jwtToken);
   this.dataService.setUserid(response.user.userId);
   this.dataService.setUserName(response.user.firstName+" "+response.user.lastName);
  },(error)=>{
    this.isLoginFailed = true;
    console.log(error);
    if(error.status == 403){
      this.errorMessage="Please provide valid credentials";
    }
    
  })
}
  constructor(private userService: UserServiceService,
              private dataService: DataServiceService) { 
                if(this.dataService.getToken() != null){
                  this.isLoggedIn=true;
                }
               
              }

  ngOnInit(): void {
  }

}
