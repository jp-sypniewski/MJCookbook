package com.mapcurtain.mjcookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
