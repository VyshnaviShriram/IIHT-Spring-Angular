import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddbooksComponent } from './Components/addbooks/addbooks.component';
import { GetbooksComponent } from './Components/getbooks/getbooks.component';
import { HomeComponent } from './Components/home/home.component';

const routes: Routes = [{path:"", component: HomeComponent},{path:"addbooks", component: AddbooksComponent},{path:"getbooks",component:GetbooksComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
