package com.mapcurtain.mjcookbook.services;

import com.mapcurtain.mjcookbook.entities.Ingredient;

public interface IngredientService {

	Ingredient createIngredient(int instructionId, int recipeId, Ingredient ingredient);

	Ingredient updateIngredient(int ingredientId, Ingredient ingredient);

}
