package com.mapcurtain.mjcookbook.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapcurtain.mjcookbook.repositories.InstructionRepository;

@Service
public class InstructionServiceImpl implements InstructionService {
	
	@Autowired
	private InstructionRepository instructionRepo;

}
