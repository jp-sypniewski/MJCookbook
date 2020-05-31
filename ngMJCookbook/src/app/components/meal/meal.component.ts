import { Component, OnInit } from '@angular/core';
import { Meal } from 'src/app/models/meal';

@Component({
  selector: 'app-meal',
  templateUrl: './meal.component.html',
  styleUrls: ['./meal.component.css']
})
export class MealComponent implements OnInit {

  meals: Meal[] = [];
  selectedMeal: Meal = new Meal();
  showMealForm: Boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  showAddNewMealForm(){
    this.selectedMeal = new Meal();
    this.showMealForm = true;
  }

}
