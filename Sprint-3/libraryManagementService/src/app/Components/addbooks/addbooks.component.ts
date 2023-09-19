import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Book } from 'src/app/Entity/Book';
import { LibraryServicesService } from 'src/app/Services/library-services.service';

@Component({
  selector: 'app-addbooks',
  templateUrl: './addbooks.component.html',
  styleUrls: ['./addbooks.component.css']
})
export class AddbooksComponent implements OnInit {
  book: Book = new Book();

  validate(loginForm:NgForm) {
    if(loginForm.touched && loginForm.valid){
      this.addBook(loginForm);
    }else{
      alert("Please fill all the values");
    }
  }

  checkAllFields(loginForm:NgForm){
    return loginForm.valid;
  }

  addBook(loginForm:NgForm) {
    const promise = this.libraryService.addbook(loginForm.value);
    promise.subscribe((response) => {
      console.log(response);
      alert("Added book succesfully");
     loginForm.resetForm();
    }, function (error) {
      console.log(error);
      alert("something is wrong, Please try again.")
    })

  }
  constructor(private libraryService: LibraryServicesService) { }

  ngOnInit(): void {
  }

}
