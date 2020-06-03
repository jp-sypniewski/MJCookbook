package com.mapcurtain.mjcookbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mapcurtain.mjcookbook.entities.Recipe;
import com.mapcurtain.mjcookbook.entities.User;

class RecipeTest {
	
	private EntityManager em;
	private static EntityManagerFactory emf;
	private Recipe recipe;

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
		recipe = em.find(Recipe.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		recipe = null;
	}

	@Test
	void test() {
		/*
		 * tests for id == 1
		 * 
		assertEquals(recipe.getTitle(), "pizza");
		assertEquals(recipe.getUser().getUsername(), "orangeisntblue");
		assertEquals(recipe.getFavoritedBy().size(), 2);
		*/
		assertTrue(recipe.getInstructions().size() > 0);
		assertTrue(recipe.getIngredients().size() > 0);

	}

}
