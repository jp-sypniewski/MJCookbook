package com.mapcurtain.mjcookbook.services;

import java.util.List;

import com.mapcurtain.mjcookbook.entities.Instruction;

public interface InstructionService {

	boolean makeNewInstructionsForRecipe(int recipeId, List<Instruction> instructions);

	Instruction updateInstruction(int instructionId, Instruction instruction);

}
