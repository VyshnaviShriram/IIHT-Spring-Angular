import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { Book } from 'src/app/Entity/book';
import { DataServiceService } from 'src/app/Services/user/data-service.service';
import { UserServiceService } from 'src/app/Services/user/user-service.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {

  book: Book;
  category: string;
  title: string;
  author: string;
  price: number;
  publisher: string;
  active: string;
  content: string;
  logo: string;
  releaseDate: Date;
  isBookCreated: boolean=false;
  // base64: any='';
  // convertBase64 = (file) => {
  //   return new Promise((resolve, reject) => {
  //     const fileReader = new FileReader();
  //     fileReader.readAsDataURL(file); 
  //     fileReader.onload = () => {
  //       resolve(fileReader.result);
  //     }; 
  //     fileReader.onerror = (error) => {
  //       reject(error);
  //     };
  //   });
  // };
  // uploadImage = async (event) => {
  //   const file = event.target.files[0];
  //   this.base64 = await this.convertBase64(file);
  //   this.logo = this.base64;
  // };

  validate(addBookForm:NgForm) {
    if(addBookForm.valid){
      this.addBook(addBookForm);
    }else{
      alert("Please fill all the values");
    }
  }


  addBook(addBookForm:NgForm){
    this.book =addBookForm.value;
   // this.book.logo=this.base64;
    const promise = this.userService.addBook(parseInt(this.dataService.getUserid()),this.book);
    promise.subscribe((response:any)=>{
      //console.log(response);
      this.isBookCreated = true;
      alert('Book is added succesfully.');
       this.router.navigateByUrl('/', { skipLocationChange: true }).then(()=>{
         this.router.navigate(["addBook"]);
       });
    },function(error){
      console.log(error);
      alert("Something went wrong, please try again later");
    });
  }


  constructor(private dataService: DataServiceService,
    private userService:UserServiceService,
    private router: Router) {}

  ngOnInit(): void {
    
  }

}
