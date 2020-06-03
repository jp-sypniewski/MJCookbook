package com.mapcurtain.mjcookbook.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mapcurtain.mjcookbook.entities.Ingredient;
import com.mapcurtain.mjcookbook.entities.Instruction;
import com.mapcurtain.mjcookbook.entities.Recipe;
import com.mapcurtain.mjcookbook.repositories.IngredientRepository;
import com.mapcurtain.mjcookbook.repositories.InstructionRepository;

@Service
public class InstructionServiceImpl implements InstructionService {
	
	@Autowired
	private IngredientRepository ingredientRepo;
	
	@Autowired
	private InstructionRepository instructionRepo;
	
	@Transactional
	@Override
	public boolean makeNewInstructionsForRecipe(int recipeId, List<Instruction> instructions) {
		try {
			List<Ingredient> previousIngredients = ingredientRepo.findByRecipeId(recipeId);
			ingredientRepo.deleteByRecipeId(recipeId);
			
			List<Instruction> previousInstructions = instructionRepo.findByRecipeId(recipeId);
			instructionRepo.deleteByRecipeId(recipeId);
			
			// add the new instructions
			for(Instruction instruction : instructions) {
				if (instruction.getRecipe() == null) {
					Recipe recipe = new Recipe();
					recipe.setId(recipeId);
					instruction.setRecipe(recipe);
				}
				instruction = instructionRepo.saveAndFlush(instruction);
				for (Ingredient ingredient : instruction.getIngredients()) {
					if (ingredient.getRecipe() == null) {
						Recipe recipe = new Recipe();
						recipe.setId(recipeId);
						ingredient.setRecipe(recipe);
					}
					if (ingredient.getInstruction() == null) {
						ingredient.setInstruction(instruction);
					}
					ingredientRepo.saveAndFlush(ingredient);
				}
			}
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	@Override
	public Instruction updateInstruction(int instructionId, Instruction instruction) {
		Optional<Instruction> opt = instructionRepo.findById(instructionId);
		if (opt.isPresent()) {
			Instruction managedInstruction = opt.get();
			managedInstruction.setText(instruction.getText());
			managedInstruction = instructionRepo.saveAndFlush(managedInstruction);
		}
		return null;
	}

}
