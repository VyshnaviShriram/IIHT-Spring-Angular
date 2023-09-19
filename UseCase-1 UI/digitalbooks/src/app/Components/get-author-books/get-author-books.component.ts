import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Book } from 'src/app/Entity/book';
import { DataServiceService } from 'src/app/Services/user/data-service.service';
import { UserServiceService } from 'src/app/Services/user/user-service.service';


@Component({
  selector: 'app-get-author-books',
  templateUrl: './get-author-books.component.html',
  styleUrls: ['./get-author-books.component.css']
})
export class GetAuthorBooksComponent implements OnInit {

  book: Book;
  books: Book[];
  editBookStatus: boolean = false;
  editBookFlg: boolean = false;
  isBookEdited: boolean = false;
  isBookFound: boolean = false;
  errorMessage: string='';

  editBook(authorId,bookId){
    this.editBookFlg = true;
    this.getBookData(bookId);
  }

  validate(editBookForm:NgForm) {
    if(editBookForm.valid){
      this.submitEditedBook(this.book);
    }else{
      alert("Please fill all the values");
    }
  }

  getBookData(bookId:number){
    const promise = this.userService.getBookByID(bookId);
    promise.subscribe((response:any)=>{
      //console.log(response);
      this.book = response;
    },function(error){
      console.log(error);
      alert("Something went wrong, please try again later");
    });
  }

  submitEditedBook(book:Book){
   // this.book =editBookForm.value;
    const promise = this.userService.editBook(parseInt(this.dataService.getUserid()),book.bookid,book);
    promise.subscribe((response:any)=>{
     // console.log(response);
      this.isBookEdited = true;
      this.editBookFlg = false;
     // editBookForm.resetForm();
    // window.location.reload();
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(()=>{
      this.router.navigate(["getAuthorBooks"]);
    });
    },function(error){
      console.log(error);
      alert("Something went wrong, please try again later");
    });
  }

  changeBookStatus(authorID:string,bookId:number){
    const promise = this.userService.changeStatusOfBook(parseInt(authorID),bookId);
    promise.subscribe((response)=>{
      //console.log(response);
      const prevStatus = (response) == 'Yes' ? 'Inactive' : 'Active';
      const currStatus = (response) == 'Yes' ? 'Active' : 'Inactive';
      alert('Status of book is changed from '+prevStatus+' to '+currStatus);
       this.router.navigateByUrl('/', { skipLocationChange: true }).then(()=>{
        this.router.navigate(["getAuthorBooks"]);
      });
    },(error)=>{
      console.log(error);
      
    })
  }

  constructor(private userService:UserServiceService,
    public dataService: DataServiceService,
    private router:Router) {
      const promise = this.userService.getAllAuthorBooks(parseInt(this.dataService.getUserid()));
      promise.subscribe((response:any)=>{
        if(response.length > 0){
          this.books = response;
          this.isBookFound = true;
        }else{
          this.errorMessage = 'No Books Found';
          this.isBookFound = false;
        }
        //console.log(response);
      },(error)=>{
          console.log(error);
      });
   }

  ngOnInit(): void {
  }

}
