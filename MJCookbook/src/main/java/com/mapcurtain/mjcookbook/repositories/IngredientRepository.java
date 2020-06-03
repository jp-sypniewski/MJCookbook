package com.mapcurtain.mjcookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

}
