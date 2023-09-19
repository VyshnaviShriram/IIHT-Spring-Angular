import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Questions } from 'src/app/Entity/questions';
import { UserServiceService } from 'src/app/Services/user-service.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

  newQuestion : Questions = new Questions();
  addedQuestion: boolean = false;

  validate(questionForm: NgForm){
    if(questionForm.valid){
      this.postNewQuestion(questionForm);
    }
  }
  postNewQuestion(questionForm: NgForm) {
    const promise = this.userService.postAQuestion(questionForm.value);
    promise.subscribe(
      (response)=>{
          console.log(response); 
          questionForm.resetForm();
          this.addedQuestion = true;
          //alert("Question is added succesfully");
      },(error)=>{
        console.log(error);
      }
    );
  }

  constructor(private userService: UserServiceService) { }

  ngOnInit(): void {
  }

}
