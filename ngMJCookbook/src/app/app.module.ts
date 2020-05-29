import { AuthService } from 'src/app/services/auth.service';
import { RecipeService } from './services/recipe.service';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http'


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecipeListComponent } from './components/recipe-list/recipe-list.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AccountComponent } from './components/account/account.component';
import { HomeComponent } from './components/home/home.component';
import { MealComponent } from './components/meal/meal.component';

@NgModule({
  declarations: [
    AppComponent,
    RecipeListComponent,
    NavbarComponent,
    AccountComponent,
    HomeComponent,
    MealComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    RecipeService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
