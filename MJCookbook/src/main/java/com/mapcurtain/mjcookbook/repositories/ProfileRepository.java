package com.mapcurtain.mjcookbook.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mapcurtain.mjcookbook.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
