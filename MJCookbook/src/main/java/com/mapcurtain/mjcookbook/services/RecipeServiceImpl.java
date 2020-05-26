package com.mapcurtain.mjcookbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.mapcurtain.mjcookbook.entities.Recipe;
import com.mapcurtain.mjcookbook.repositories.RecipeRepository;

public class RecipeServiceImpl implements RecipeService {
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Override
	public List<Recipe> getAllRecipes(){
		List<Recipe> recipes = recipeRepo.findAll();
		return recipes;
	}
	
	@Override
	public Recipe getOneRecipe(int id) {
		Optional<Recipe> opt = recipeRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	@Override
	public Recipe createRecipe(Recipe recipe) {
		recipe = recipeRepo.saveAndFlush(recipe);
		return recipe;
	}
	
	@Override
	public Recipe updateRecipe(int id, Recipe recipe) {
		Optional<Recipe> opt = recipeRepo.findById(id);
		if (opt.isPresent()) {
			Recipe managedRecipe = opt.get();
			managedRecipe.setTitle(recipe.getTitle());
			managedRecipe.setRecipeText(recipe.getRecipeText());
			managedRecipe = recipeRepo.saveAndFlush(managedRecipe);
			return managedRecipe;
		}
		return null;
	}

}
