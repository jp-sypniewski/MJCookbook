package com.mapcurtain.mjcookbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	
	long deleteByRecipeId(int id);
	List<Ingredient> findByRecipeId(int id);

}
