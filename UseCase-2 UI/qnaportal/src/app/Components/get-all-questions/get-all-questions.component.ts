import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Answers } from 'src/app/Entity/Answers';
import { Questions } from 'src/app/Entity/questions';
import { UserServiceService } from 'src/app/Services/user-service.service';

@Component({
  selector: 'app-get-all-questions',
  templateUrl: './get-all-questions.component.html',
  styleUrls: ['./get-all-questions.component.css']
})
export class GetAllQuestionsComponent implements OnInit {

  questions: Questions[];
  accordId = 'collapse-';
  accordTarget = '#collapse-';
  answer: Answers;
  newAns: Answers = new Answers();
  showField: boolean= false;
  addedAnswer: boolean = false;

  addAnswer(questionId,userId){
    this.newAns.questionId = questionId;
    this.newAns.userId = userId;
    const promise = this.userService.addNewAnswer(this.newAns);
    promise.subscribe(
      (response)=>{
        console.log(response);
        this.showField = false;
        this.addedAnswer = true;
        window.scrollTo(0,0)
        setTimeout( () => {
          this.addedAnswer = false;
          this.router.navigateByUrl('/', { skipLocationChange: true }).then(()=>{
            this.router.navigate(["getQuestions"]);
          });
        }, 2000);       
      },(error)=>{
        console.log(error);
        
      }
    )
  }

  constructor(private userService: UserServiceService,
    private router: Router) { 
    const promise = this.userService.getAllQuestions();
    promise.subscribe(
      (response)=>{
        console.log(response);
        this.questions = JSON.parse(JSON.stringify(response));
      },(error)=>{
        console.log(error);
        
      }
    )
  }

  ngOnInit(): void {
  }

}
