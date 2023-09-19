import { Component, OnInit } from '@angular/core';
import User from 'src/app/Entity/user';
import { UserService } from 'src/app/Services/user.service';

@Component({
  selector: 'app-reg-users',
  templateUrl: './reg-users.component.html',
  styleUrls: ['./reg-users.component.css']
})
export class RegUsersComponent implements OnInit {

  users: User[] = [];
  constructor(private userService : UserService) { 
  
  }

  sortUsers(){
    this.users.sort((user1,user2)=>{
      return user1.age-user2.age;
    })
  }

  deleteUser(user,index){
    const promise=this.userService.deleteUsers(user);
    promise.subscribe((response)=>{
      console.log(response);
      this.users.splice(index,1);
    },function(error){
      console.log(error);
      alert("something went wrong");
    })
  }
  ngOnInit(): void {

    const promise = this.userService.getUsers();
    promise.subscribe(
     (response)=>{
       console.log(response);
       this.users=response as User[];
     },function(error){
       console.log(error);
       alert('something is wrong');
     }
    )

  }

}
