package com.mapcurtain.mjcookbook.services;

import java.util.List;

import com.mapcurtain.mjcookbook.entities.Meal;

public interface MealService {

	List<Meal> getMealsForUser(String username);

	Meal getMealById(int id);

	Meal postMeal(String username, Meal meal);

	Meal putMeal(int id, String username, Meal meal);

}
