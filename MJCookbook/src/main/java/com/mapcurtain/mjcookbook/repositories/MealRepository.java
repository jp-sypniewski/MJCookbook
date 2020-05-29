package com.mapcurtain.mjcookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.Meal;

public interface MealRepository extends JpaRepository<Meal, Integer> {

}
