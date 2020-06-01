package com.mapcurtain.mjcookbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {
	
	List<Meal> findByUserUsername(String username);
	
	List<Meal> findByUserUsernameAndEnabled(String username, Boolean enabled);

}
