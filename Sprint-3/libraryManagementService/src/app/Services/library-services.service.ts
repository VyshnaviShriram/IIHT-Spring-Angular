import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../Entity/Book';

const URL = "http://localhost:8081";
@Injectable({
  providedIn: 'root'
})
export class LibraryServicesService {

  getBooks(){
    return this.http.get(URL+"/allbooks");
  }

  addbook(book:Book){
    return this.http.post(URL+"/add/book",book);
  }

  deletebook(book){
    return this.http.delete(URL+"/remove/"+book.id);
  }

  constructor(private http: HttpClient) { }
}
