import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAnswerComponent } from './Components/add-answer/add-answer.component';
import { AddQuestionComponent } from './Components/add-question/add-question.component';
import { GetAllQuestionsComponent } from './Components/get-all-questions/get-all-questions.component';
import { HomeComponent } from './Components/home/home.component';
import { LoginComponent } from './Components/login/login.component';
import { RegisterComponent } from './Components/register/register.component';
import { AuthGuard } from './guard/auth.guard';

const routes: Routes = [{ path : "", component : HomeComponent},
                        { path : "register", component : RegisterComponent},
                        { path : "login", component : LoginComponent},
                        { path : "addQuestion", component : AddQuestionComponent, canActivate: [AuthGuard]},
                        { path : "addAnswer", component : AddAnswerComponent, canActivate: [AuthGuard]},
                        { path : "getQuestions", component : GetAllQuestionsComponent, canActivate: [AuthGuard]}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
