package com.mapcurtain.mjcookbook.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {
	
	@Id
	private String username;
	
	private String password;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@OneToOne
	@JoinColumn(name="profile_id", unique=true)
	private Profile profile;
	
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(name="recipe_has_user",
		joinColumns=@JoinColumn(name="user_username"),
		inverseJoinColumns=@JoinColumn(name="recipe_id"))
	private List<Recipe> favoriteRecipes;
	
	

}
