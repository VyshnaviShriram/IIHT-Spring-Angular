import { Component, OnInit } from '@angular/core';
import User from 'src/app/Entity/user';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-userform',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.css']
})
export class UserformComponent implements OnInit {
  title:string = 'Please fill the form below.';
  user: User = new User();
  save(){
    console.log("Hello "+this.user.firstname+" "+this.user.lastname);
    console.log("Your Email Id is "+this.user.email);
    const observables = this.userService.saveUser(this.user);
    observables.subscribe(
      (response:any)=>{

        console.log(response);
        this.user= new User();
      },function(error){
        console.log(error);
        alert("something went wrong");
      }
    )

 }
  constructor(private userService : UserService) {
   }

  ngOnInit(): void {
  }

}
