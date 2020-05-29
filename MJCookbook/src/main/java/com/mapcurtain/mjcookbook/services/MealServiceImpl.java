package com.mapcurtain.mjcookbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapcurtain.mjcookbook.entities.Meal;
import com.mapcurtain.mjcookbook.entities.User;
import com.mapcurtain.mjcookbook.repositories.MealRepository;

@Service
public class MealServiceImpl implements MealService {
	
	@Autowired
	private MealRepository mealRepo;
	
	@Override
	public List<Meal> getMealsForUser(String username){
		List<Meal> userMeals = mealRepo.findByUserUsername(username);
		return userMeals;
	}
	
	@Override
	public Meal getMealById(int id) {
		Optional<Meal> opt = mealRepo.findById(id);
		if (opt.isPresent()) {
			Meal meal = opt.get();
			return meal;
		}
		return null;
	}
	
	@Override
	public Meal postMeal(String username, Meal meal) {
		User user = new User();
		user.setUsername(username);
		meal.setUser(user);
		meal = mealRepo.saveAndFlush(meal);
		return meal;
	}
	
	@Override
	public Meal putMeal(int id, String username, Meal meal) {
		Optional<Meal> opt = mealRepo.findById(id);
		if (opt.isPresent()) {
			Meal managedMeal = opt.get();
			if (managedMeal.getUser().getUsername().equals(username)) {
				if (meal.getPlannedFor() != null) {
					managedMeal.setPlannedFor(meal.getPlannedFor());
				}
				if (meal.getCompleted() != null) {
					managedMeal.setCompleted(meal.getCompleted());
				}
				if (meal.getRating() != null) {
					managedMeal.setRating(meal.getRating());
				}
				if (meal.getRecipe() != null) {
					managedMeal.setRecipe(meal.getRecipe());
				}
				if (meal.getEnabled() != null) {
					managedMeal.setEnabled(meal.getEnabled());
				}
				managedMeal = mealRepo.saveAndFlush(managedMeal);
				return managedMeal;
				
			}
		}
		return null;
	}

}
