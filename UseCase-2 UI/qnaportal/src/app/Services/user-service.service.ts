import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Answers } from '../Entity/Answers';

const USER_URL = 'http://52.91.243.79:5000/';
//const QNA_URL = 'http://localhost:5001/QnA/';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  
  loginUser(loginData) {
    return this.http.post(USER_URL + "login", loginData, {
      headers: this.requestHeader
    });
  }

  signupUser(signupData) {
    return this.http.post(USER_URL + "register", signupData, {
      headers: this.requestHeader, responseType: "text"
    });
  }

  postAQuestion(questionData) {
    return this.http.post(USER_URL + "addQuestion", questionData);
  }

  addNewAnswer(answer:Answers){
    return this.http.post(USER_URL + "addAnswer", answer);
  }
  getAllQuestions(){
    return this.http.get(USER_URL + "getAllQuestions");
  }
  constructor(private http: HttpClient) { }
}
