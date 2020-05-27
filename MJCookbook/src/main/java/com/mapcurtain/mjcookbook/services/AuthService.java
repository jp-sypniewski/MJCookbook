package com.mapcurtain.mjcookbook.services;

import java.security.Principal;

import com.mapcurtain.mjcookbook.entities.User;

public interface AuthService {

	User register(User user);

	User update(User user, String username);

}
