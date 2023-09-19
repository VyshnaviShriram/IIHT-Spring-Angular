import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './Components/header/header.component';
import { AddBookComponent } from './Components/add-book/add-book.component';
import { GetBookComponent } from './Components/get-book/get-book.component';
import { SearchComponent } from './Components/search/search.component';
import { HomeComponent } from './Components/home/home.component';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './Components/login/login.component';
import { SignupComponent } from './Components/signup/signup.component';
import { AuthInterceptor } from './guard/auth.interceptor';
import { AuthguardGuard } from './guard/authguard.guard';
import { UserServiceService } from './Services/user/user-service.service';
import { GetAuthorBooksComponent } from './Components/get-author-books/get-author-books.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AddBookComponent,
    GetBookComponent,
    SearchComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    GetAuthorBooksComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [
    AuthguardGuard,
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
