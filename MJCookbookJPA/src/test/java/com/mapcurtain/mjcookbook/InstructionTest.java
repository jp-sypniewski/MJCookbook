package com.mapcurtain.mjcookbook;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mapcurtain.mjcookbook.entities.Instruction;

class InstructionTest {

	private EntityManager em;
	private static EntityManagerFactory emf;
	private Instruction instruction;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("mjcookbook");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		instruction = em.find(Instruction.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		instruction = null;
	}

	@Test
	void test() {
		assertEquals(instruction.getId(), 5);
	}

}
