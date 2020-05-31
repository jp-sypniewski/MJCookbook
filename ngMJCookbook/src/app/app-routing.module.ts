import { HomeComponent } from './components/home/home.component';
import { MealComponent } from './components/meal/meal.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RecipeListComponent } from './components/recipe-list/recipe-list.component';
import { AccountComponent } from './components/account/account.component';


const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: '', component: HomeComponent},
  {path: 'recipeList', component: RecipeListComponent},
  {path: 'account', component: AccountComponent},
  {path: 'meals', component: MealComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
