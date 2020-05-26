package com.mapcurtain.mjcookbook.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mapcurtain.mjcookbook.entities.Recipe;
import com.mapcurtain.mjcookbook.services.RecipeService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class RecipeController {
	
	@Autowired
	private RecipeService recipeSvc;
	
	@GetMapping(value="recipes")
	public List<Recipe> getAllRecipes(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal){
		List<Recipe> recipes = recipeSvc.getAllRecipes();
		
		
		return recipes;
	}
	
	@GetMapping(value="recipes/{id}")
	public Recipe getSingleRecipe(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id) {
		Recipe recipe = recipeSvc.getOneRecipe(id);
		
		return recipe;
	}
	
	@PostMapping(value="recipes")
	public Recipe postRecipe(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@RequestBody Recipe recipe) {
		recipe = recipeSvc.createRecipe(recipe);
		
		return recipe;
	}
	
	@PutMapping(value="recipes/{id}")
	public Recipe updateRecipe(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id,
			@RequestBody Recipe recipe) {
		recipe = recipeSvc.updateRecipe(id, recipe);
		
		return recipe;
	}

}
