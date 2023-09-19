import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/Entity/book';
import { User } from 'src/app/Entity/user';
import { DataServiceService } from 'src/app/Services/user/data-service.service';
import { UserServiceService } from 'src/app/Services/user/user-service.service';

@Component({
  selector: 'app-get-book',
  templateUrl: './get-book.component.html',
  styleUrls: ['./get-book.component.css']
})
export class GetBookComponent implements OnInit {

  user: User = new User();
  books:any[];
  subscribId: number;
  subscribDt: Date;
  popupOpen: boolean = false;

  unSubscribe(userID,subscriptionId,index){
    const promise = this.userService.unSubscribeBook(userID,subscriptionId);
    promise.subscribe((response)=>{
     // console.log(response);
      if(response == 'Cancelled Subscription'){
          this.books.splice(index,1);
      }
    },(error)=>{
      console.log(error);
      
    })
  }

  viewContentofBook(content:string){
    window.open(content, 'blank');
  }

  viewInvoice(subscriptionDt,subscriptionId){
    console.log(subscriptionDt+"   "+subscriptionId);
    this.subscribDt = subscriptionDt;
    this.subscribId = subscriptionId;
    this.popupOpen = true;
  }

  closePopup(){
    this.popupOpen = false;
  }

  constructor(private userService: UserServiceService,
    public dataService: DataServiceService) {
    this.user.userid = parseInt(this.dataService.getUserid());
    const promise = this.userService.getListofSubscribedBook(this.user);
    promise.subscribe((response: any) => {
      //console.log(response);
      response = JSON.parse(JSON.stringify(response));
      this.books = response.map(elem => JSON.parse(elem));
    }, (error) => {
      console.log(error);

    });
  }

  ngOnInit(): void {
  }

}
