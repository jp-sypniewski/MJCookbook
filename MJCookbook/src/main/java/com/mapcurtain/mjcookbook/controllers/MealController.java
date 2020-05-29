package com.mapcurtain.mjcookbook.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapcurtain.mjcookbook.entities.Meal;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class MealController {
	
	// add the meal service autowire
	
	@GetMapping(value="meals")
	public List<Meal> getMealsByUser(){
		List<Meal> meals = new ArrayList<>();
		
		return meals;
	}
	
	@GetMapping(value="meals/{id}")
	public Meal getMealByMealId(){
		Meal meal = new Meal();
		return meal;
	}
	
	@PostMapping(value="meals")
	public Meal createMeal() {
		return null;
	}
	
	@PutMapping(value="meals/{id}")
	public Meal updateMeal() {
		return null;
	}

}
