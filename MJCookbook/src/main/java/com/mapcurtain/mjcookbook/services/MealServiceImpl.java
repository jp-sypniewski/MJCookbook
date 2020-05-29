package com.mapcurtain.mjcookbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapcurtain.mjcookbook.entities.Meal;
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
		return null;
	}
	
	@Override
	public Meal putMeal(int id, Meal meal) {
		return null;
	}

}
