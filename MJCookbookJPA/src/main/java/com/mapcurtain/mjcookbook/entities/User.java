package com.mapcurtain.mjcookbook.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class User {
	
	@Id
	private String username;
	
	private String password;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	

}
