import { Component, OnInit, resolveForwardRef } from '@angular/core';
import { Book } from 'src/app/Entity/Book';
import { LibraryServicesService } from 'src/app/Services/library-services.service';

@Component({
  selector: 'app-getbooks',
  templateUrl: './getbooks.component.html',
  styleUrls: ['./getbooks.component.css']
})
export class GetbooksComponent implements OnInit {

  book: Book = new Book();
  books: Book[] = [];

  deleteBook(book,index:number){
    const promise = this.libraryService.deletebook(book);
    promise.subscribe((response)=>{
      console.log(response);
      this.books.splice(index,1);
    },function(error){
      console.log(error);
    })
  }
  constructor(private libraryService: LibraryServicesService) { }

  ngOnInit(): void {
    const promise = this.libraryService.getBooks();
    promise.subscribe((response:any)=>{
      this.books=response;
      console.log(response);
    },function(error){
      console.log(error);
    })
  }

}
