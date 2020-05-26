package com.mapcurtain.mjcookbook.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Profile {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	private String description;
	
	@OneToOne(mappedBy="profile")
	private User user;

}
