package com.mapcurtain.mjcookbook.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.Instruction;

public interface InstructionRepository extends JpaRepository<Instruction, Integer> {
	
	long deleteByRecipeId(int id);
	List<Instruction> findByRecipeId(int id);

}
