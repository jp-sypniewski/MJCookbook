package com.mapcurtain.mjcookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

}
