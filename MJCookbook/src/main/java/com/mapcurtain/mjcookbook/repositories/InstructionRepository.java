package com.mapcurtain.mjcookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.Instruction;

public interface InstructionRepository extends JpaRepository<Instruction, Integer> {

}
