package com.mapcurtain.mjcookbook.controllers;

import java.security.Principal;
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

import com.mapcurtain.mjcookbook.entities.Ingredient;
import com.mapcurtain.mjcookbook.entities.Instruction;
import com.mapcurtain.mjcookbook.entities.Recipe;
import com.mapcurtain.mjcookbook.entities.User;
import com.mapcurtain.mjcookbook.services.IngredientService;
import com.mapcurtain.mjcookbook.services.InstructionService;
import com.mapcurtain.mjcookbook.services.RecipeService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4200"})
public class RecipeController {
	
	@Autowired
	private IngredientService ingredientSvc;
	
	@Autowired
	private InstructionService instructionSvc;
	
	@Autowired
	private RecipeService recipeSvc;
	
	@GetMapping(value="recipes")
	public List<Recipe> getAllRecipes(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal){
		try {
			List<Recipe> recipes = recipeSvc.getAllRecipes();
			res.setStatus(200);
		
			return recipes;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(404);
			return null;
		}
	}
	
	@GetMapping(value="recipes/{id}")
	public Recipe getSingleRecipe(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id) {
		try {
			Recipe recipe = recipeSvc.getOneRecipe(id);
			if (recipe != null) {
				res.setStatus(200);
				return recipe;
			} else {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@PostMapping(value="recipes")
	public Recipe postRecipe(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@RequestBody Recipe recipe) {
		try {
			User userObjectForUsername = new User();
			userObjectForUsername.setUsername(principal.getName());
			recipe.setUser(userObjectForUsername);
			recipe = recipeSvc.createRecipe(recipe);
			res.setStatus(201);
			return recipe;
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	@PutMapping(value="recipes/{id}")
	public Recipe updateRecipe(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id,
			@RequestBody Recipe recipe) {
		try {
			recipe = recipeSvc.updateRecipe(id, recipe);
			if (recipe != null) {
				res.setStatus(200);
				return recipe;
			} else {
				res.setStatus(304);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	// takes in full list of instructions to replace old list
	// needs to reassign ingredients to instructions as needed before performing delete
	@PostMapping(value="recipes/{id}/instructions")
	public Recipe updateRecipeInstructionsWithOrder(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer recipeId,
			@RequestBody List<Instruction> instructions){
		try {
			// perform exchange of instructions
			if (instructionSvc.makeNewInstructionsForRecipe(recipeId, instructions)) {
				// if exchange worked then grab that recipe and send it back
				Recipe recipe = recipeSvc.getOneRecipe(recipeId);
				if (recipe != null) {
					res.setStatus(201);
					return recipe;
				}
				else {
					res.setStatus(404);
					return null;
				}
			}
			else {
				res.setStatus(400);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	// update single instruction, really only for changing instruction text
	@PutMapping(value="recipes/{rid}/instructions/{iid}")
	public Instruction updateInstruction(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("rid") Integer recipeId,
			@PathVariable("iid") Integer instructionId,
			@RequestBody Instruction instruction) {
		try {
			instruction = instructionSvc.updateInstruction(instructionId, instruction);
			if (instruction != null) {
				res.setStatus(200);
				return instruction;
			}
			else {
				res.setStatus(404);
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	
	
	// this endpoint should make it easier to "add an ingredient" to an existing recipe
	@PostMapping(value="recipes/{id}/ingredients")
	public Ingredient addIngredient(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("id") Integer id,
			@RequestBody Ingredient ingredient) {
		try {
			ingredient = ingredientSvc.createIngredient(ingredient);
			if (ingredient != null) {
				res.setStatus(204);
				return ingredient;
			}
			else {
				res.setStatus(400);
				return null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}
	
	// update single ingredient, really only for name/amount/instruction changes
	@PutMapping(value="recipes/{rid}/ingredients/{iid}")
	public Ingredient updateIngredient(HttpServletRequest req,
			HttpServletResponse res,
			Principal principal,
			@PathVariable("rid") Integer recipeId,
			@PathVariable("iid") Integer ingredientId,
			@RequestBody Ingredient ingredient) {
		try {
			ingredient = ingredientSvc.updateIngredient(ingredientId, ingredient);
			if (ingredient != null) {
				res.setStatus(200);
				return ingredient;
			}
			else {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
	}

}
