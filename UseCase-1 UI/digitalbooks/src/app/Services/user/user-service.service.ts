import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from 'src/app/Entity/book';
import { User } from 'src/app/Entity/user';
import { DataServiceService } from './data-service.service';


const USER_URL = 'http://localhost:5000';
const BOOK_URL = 'http://localhost:5001/book';

//const USER_URL = 'http://ec2-34-201-70-199.compute-1.amazonaws.com:5000';
//const BOOK_URL = 'http://ec2-34-201-70-199.compute-1.amazonaws.com:5001/book';

@Injectable({
  providedIn: 'root'
})

export class UserServiceService {

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  roleMatch(role: string) {
    return role === this.dataService.getRole();
  }

  loginUser(loginData) {
    return this.http.post(USER_URL + "/sign-in", loginData, {
      headers: this.requestHeader
    });
  }

  signupUser(signupData) {
    return this.http.post(USER_URL + "/sign-up", signupData, {
      headers: this.requestHeader, responseType: "text"
    });
  }

  searchBooks(queryParam: string) {
    return this.http.get(BOOK_URL + "/search?" + queryParam);
  }

  //  To subscribe a book
  subscribeBooks(user: User, bookid: number) {
    return this.http.post(USER_URL + "/readers/"+bookid+"/subscribingBook", user);
  }

  getListofSubscribedBook(user: User) {
    return this.http.get(USER_URL + "/readers/" + user.userid + "/books");
  }

  unSubscribeBook(userID: number, subscriptionId: number) {
    return this.http.post(USER_URL + "/readers/" + userID + "/books/" + subscriptionId + "/read/cancel-subscription", '', { responseType: "text" });
  }

  viewContent(userId: number, subscriptionId: number){
    return this.http.get(USER_URL+ "/readers/" + userId + "/books/" + subscriptionId + "/read",{responseType: "text"});
  }

  addBook(userId: number,book:Book){
    return this.http.post(USER_URL+"/author/"+userId+"/books",book,{responseType: "text"})
  }

  getAllAuthorBooks(authorId: number){
    return this.http.get(USER_URL+"/author/"+authorId+"/getbooks");
  }

  editBook(authorId: number,bookId: number,book:Book){
    return this.http.put(USER_URL+"/author/"+authorId+"/books/"+bookId,book,{responseType:"text"});
  }

  getBookByID(bookId : number){
    return this.http.get(USER_URL+"/author/"+bookId+"/getbook");
  }

  changeStatusOfBook(authorId: number,bookId: number){
    return this.http.post(USER_URL+"/author/"+authorId+"/books/"+bookId+"/changeStatus",'',{responseType:"text"})
  }

  constructor(private http: HttpClient, private dataService: DataServiceService) { }
}
