import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserServiceService } from 'src/app/Services/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  errorMessage : string='';
  isSignUpSuccess: boolean = false;
  isSignUpFailed: boolean = false;
  
  validate(signupForm) {
    if (signupForm.valid) {
      this.signup(signupForm);
    }
  }

  signup(signupForm: NgForm){
      const promise = this.userService.signupUser(signupForm.value);
      promise.subscribe((response)=>{
        this.isSignUpSuccess = true;
        this.errorMessage = '';
        //console.log(response);
        signupForm.resetForm();
      },(error)=>{
        console.log(error);
        this.isSignUpFailed = true;
        if(error.status == 403){
          this.errorMessage = error.error;
        }
        alert('something is wrong, try again');
        
      });
  }
  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {
  }

}
