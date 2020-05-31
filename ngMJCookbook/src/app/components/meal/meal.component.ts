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

  constructor(private router: Router,
    private mealSvc: MealService,
    private authSvc: AuthService) { }

  ngOnInit(): void {
    this.reload();
  }

  reload(){
    if (this.authSvc.checkLogin()){
      this.loggedIn = true;
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

  showEditMealForm(meal: Meal){
    this.selectedMeal = meal;
    this.showMealForm = true;
  }

  showAddNewMealForm(){
    this.selectedMeal = new Meal();
    this.showMealForm = true;
  }

}
