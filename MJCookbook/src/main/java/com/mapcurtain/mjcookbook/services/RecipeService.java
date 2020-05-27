package com.mapcurtain.mjcookbook.services;

import java.util.List;

import com.mapcurtain.mjcookbook.entities.Recipe;

public interface RecipeService {

	List<Recipe> getAllRecipes();

	Recipe getOneRecipe(int id);

	Recipe createRecipe(Recipe recipe);

	Recipe updateRecipe(int id, Recipe recipe);

}
