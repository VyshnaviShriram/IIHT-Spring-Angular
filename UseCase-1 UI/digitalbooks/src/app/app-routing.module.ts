import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddBookComponent } from './Components/add-book/add-book.component';
import { GetAuthorBooksComponent } from './Components/get-author-books/get-author-books.component';
import { GetBookComponent } from './Components/get-book/get-book.component';
import { HomeComponent } from './Components/home/home.component';
import { LoginComponent } from './Components/login/login.component';
import { SearchComponent } from './Components/search/search.component';
import { SignupComponent } from './Components/signup/signup.component';
import { AuthguardGuard } from './guard/authguard.guard';

const routes: Routes = [{ path: "", component: HomeComponent },
                        { path: "search", component: SearchComponent },
                        { path: "addBook", component: AddBookComponent, canActivate:[AuthguardGuard], data:{role:'Author'} },
                        { path: "getAuthorBooks", component: GetAuthorBooksComponent, canActivate:[AuthguardGuard], data:{role:'Author'} },
                        { path: "getBooks", component: GetBookComponent, canActivate:[AuthguardGuard], data:{role:'Reader'} },
                        { path: "login", component: LoginComponent },
                        { path: "signup", component: SignupComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
