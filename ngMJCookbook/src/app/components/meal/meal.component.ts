import { RecipeService } from './../../services/recipe.service';
import { AuthService } from 'src/app/services/auth.service';
import { MealService } from './../../services/meal.service';
import { Component, OnInit } from '@angular/core';
import { Meal } from 'src/app/models/meal';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';

@Component({
  selector: 'app-meal',
  templateUrl: './meal.component.html',
  styleUrls: ['./meal.component.css']
})
export class MealComponent implements OnInit {

  meals: Meal[] = [];
  selectedMeal: Meal = new Meal();
  recipes: Recipe[] = [];
  showMealForm: Boolean = false;
  loggedIn: Boolean = false;
  showDelete: Boolean = false;

  constructor(private router: Router,
    private mealSvc: MealService,
    private authSvc: AuthService,
    private recipeSvc: RecipeService) { }

  ngOnInit(): void {
    this.reload();
  }

  reload(){
    // check if user is logged in
    if (this.authSvc.checkLogin()){
      // set user is logged in field to true
      this.loggedIn = true;

      // set other fields appropriately
      this.meals = [];
      this.selectedMeal = new Meal();
      this.recipes = [];
      this.showMealForm = false;
      this.showDelete = false;

      // get user's meals through injected service
      this.mealSvc.getMealsByUser().subscribe(
        data => {
          this.meals = data;
        },
        err => {
          console.error('MealComponent.reload(): error fetching meals by user');
        }
      )
    }
  }

  redirectToLogIn(){
    this.router.navigateByUrl('account');
  }

  // passes an existing meal to the form
  showEditMealForm(meal: Meal){
    this.populateRecipes();
    this.selectedMeal = meal;
    this.showMealForm = true;
  }

  // creates new meal and recipe objects to pass to form in prep for field selection
  showAddNewMealForm(){
    this.populateRecipes();
    this.selectedMeal = new Meal();
    this.selectedMeal.recipe = new Recipe();
    this.showMealForm = true;
  }

  // grab all recipes to pass into meal form
  populateRecipes(){
    this.recipeSvc.getAllRecipes().subscribe(
      data => {
        this.recipes = data;
      },
      err => {
        console.error('MealComponent.populateRecipes(): error getting recipes');
      }
    );
  }

  submit(meal: Meal){
    if (meal.completed == null){
      meal.completed = false;
    }
    if (meal.id == null){
      this.mealSvc.postMeal(meal).subscribe(
        data => {
          this.reload();
        },
        err => {
          console.error('MealComponent.submit(meal): error creating meal');
        }
      );
    } else {
      this.mealSvc.putMeal(meal).subscribe(
        data => {
          this.reload();
        },
        err => {
          console.error('MealComponent.submit(meal): error updating meal');
        }
      );
    }
  }

  disable(meal: Meal){
    meal.enabled = false;
    this.mealSvc.putMeal(meal).subscribe(
      data => {
        this.reload();
      },
      err => {
        console.error('MealComponent.disable(meal): error disabling meal')
      }
    );
  }

}
