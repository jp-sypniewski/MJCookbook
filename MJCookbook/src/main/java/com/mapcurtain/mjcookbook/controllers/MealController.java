package com.mapcurtain.mjcookbook.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapcurtain.mjcookbook.entities.Meal;
import com.mapcurtain.mjcookbook.services.MealService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class MealController {
	
	@Autowired
	private MealService mealSvc;
	
	@GetMapping(value="meals")
	public List<Meal> getMealsByUser(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal){	
		try {
			List<Meal> meals = mealSvc.getMealsForUser(principal.getName());
			res.setStatus(200);
			return meals;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			return null;
		}
	}
	
	@GetMapping(value="meals/{id}")
	public Meal getMealByMealId(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id){
		try {
			Meal meal = mealSvc.getMealById(id);
			if (meal != null) {
				res.setStatus(200);
				return meal;
			} else {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@PostMapping(value="meals")
	public Meal createMeal(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@RequestBody Meal meal) {
		// the request will attach the user from the principal, and 
		// must already include at least the id for the recipe
		try {
			meal = mealSvc.postMeal(principal.getName(), meal);
			if (meal != null) {
				res.setStatus(200);
				return meal;
			} else {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@PutMapping(value="meals/{id}")
	public Meal updateMeal(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id,
			@RequestBody Meal meal) {
		try {
			meal = mealSvc.putMeal(id, principal.getName(), meal);
			if (meal != null) {
				res.setStatus(200);
				return meal;
			} else {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}

}
