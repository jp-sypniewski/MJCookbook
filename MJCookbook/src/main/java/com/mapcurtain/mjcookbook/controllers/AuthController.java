package com.mapcurtain.mjcookbook.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mapcurtain.mjcookbook.entities.User;
import com.mapcurtain.mjcookbook.services.AuthService;

@RestController
@CrossOrigin({ "*", "http://localhost:4200" })
public class AuthController {
	
	@Autowired
	private AuthService authSvc;
	
	@GetMapping(path="/authenticate")
	public Principal authenticate(Principal principal) {
		return principal;
	}
	
	@PostMapping(path="/register")
	public User register(HttpServletResponse res,
			@RequestBody User user) {
		user = authSvc.register(user);
		if (user != null) {
			res.setStatus(201);
			return user;
		} else {
			res.setStatus(400);
			return null;
		}
	}
	
	@PutMapping(path="/update")
	public User update(HttpServletResponse res,
			Principal principal,
			@RequestBody User user) {
		try {
			user = authSvc.update(user, principal.getName());
			if (user != null) {
				res.setStatus(202);
				return user;
			} else {
				res.setStatus(404);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			return null;
		}
		
		
	}

}
