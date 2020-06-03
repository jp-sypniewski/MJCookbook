package com.mapcurtain.mjcookbook.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Ingredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String amount;
	
	private String substitute;
	
	private String inclusion;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="instruction_id")
	private Instruction instruction;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="recipe_id")
	private Recipe recipe;

	public Ingredient() {
		super();
	}

	public Ingredient(int id, String name, String amount, String substitute, String inclusion, Instruction instruction,
			Recipe recipe) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.substitute = substitute;
		this.inclusion = inclusion;
		this.instruction = instruction;
		this.recipe = recipe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSubstitute() {
		return substitute;
	}

	public void setSubstitute(String substitute) {
		this.substitute = substitute;
	}

	public String getInclusion() {
		return inclusion;
	}

	public void setInclusion(String inclusion) {
		this.inclusion = inclusion;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
