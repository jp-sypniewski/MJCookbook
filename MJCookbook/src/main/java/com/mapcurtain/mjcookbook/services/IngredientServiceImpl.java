package com.mapcurtain.mjcookbook.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapcurtain.mjcookbook.entities.Ingredient;
import com.mapcurtain.mjcookbook.repositories.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
	@Override
	public Ingredient createIngredient(Ingredient ingredient) {
		ingredient = ingredientRepo.saveAndFlush(ingredient);
		return ingredient;
	}
	
	@Override
	public Ingredient updateIngredient(int ingredientId, Ingredient ingredient) {
		Optional<Ingredient> opt = ingredientRepo.findById(ingredientId);
		if (opt.isPresent()) {
			Ingredient managedIngredient = opt.get();
			managedIngredient.setName(ingredient.getName());
			managedIngredient.setAmount(ingredient.getAmount());
			if (ingredient.getSubstitute() != null) {
				managedIngredient.setSubstitute(ingredient.getSubstitute());
			}
			managedIngredient.setInclusion(ingredient.getInclusion());
			managedIngredient = ingredientRepo.saveAndFlush(managedIngredient);
			return managedIngredient;
		}
		
		return null;
	}

}
