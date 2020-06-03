package com.mapcurtain.mjcookbook.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	
	@Column(name="recipe_text")
	private String recipeText;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToOne
	@JoinColumn(name="user_username")
	private User user;
	
	private Boolean enabled;
	
	private String status;
	
	// start json ignore fields for not using
	
	@JsonIgnore
	@ManyToMany(mappedBy="favoriteRecipes")
	private List<User> favoritedBy;
	
	@JsonIgnore
	@OneToMany(mappedBy="recipe")
	private List<Instruction> instructions;
	
	@JsonIgnore
	@OneToMany(mappedBy="recipe")
	private List<Ingredient> ingredients;
	
	// ^end json ignore recipe fields for not using

	public Recipe() {
		super();
	}

	public Recipe(int id, String title, String recipeText,
			LocalDateTime createdAt, LocalDateTime updatedAt, User user, Boolean enabled,
			String status, List<User> favoritedBy, List<Instruction> instructions,
			List<Ingredient> ingredients) {
		super();
		this.id = id;
		this.title = title;
		this.recipeText = recipeText;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.enabled = enabled;
		this.status = status;
		this.favoritedBy = favoritedBy;
		this.instructions = instructions;
		this.ingredients = ingredients;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRecipeText() {
		return recipeText;
	}

	public void setRecipeText(String recipeText) {
		this.recipeText = recipeText;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<User> getFavoritedBy() {
		return favoritedBy;
	}

	public void setFavoritedBy(List<User> favoritedBy) {
		this.favoritedBy = favoritedBy;
	}

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
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
		Recipe other = (Recipe) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
