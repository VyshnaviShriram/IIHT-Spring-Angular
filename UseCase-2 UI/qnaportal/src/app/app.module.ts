import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './Components/header/header.component';
import { RegisterComponent } from './Components/register/register.component';
import { LoginComponent } from './Components/login/login.component';
import { AddQuestionComponent } from './Components/add-question/add-question.component';
import { AddAnswerComponent } from './Components/add-answer/add-answer.component';
import { HomeComponent } from './Components/home/home.component';
import { FooterComponent } from './Components/footer/footer.component';
import { GetAllQuestionsComponent } from './Components/get-all-questions/get-all-questions.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { UserServiceService } from './Services/user-service.service';
import { AuthInterceptor } from './guard/auth.interceptor';
import { AuthGuard } from './guard/auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    RegisterComponent,
    LoginComponent,
    AddQuestionComponent,
    AddAnswerComponent,
    HomeComponent,
    FooterComponent,
    GetAllQuestionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AuthGuard,
    {
      useClass : AuthInterceptor,
      provide : HTTP_INTERCEPTORS,
      multi : true
    },
    UserServiceService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
