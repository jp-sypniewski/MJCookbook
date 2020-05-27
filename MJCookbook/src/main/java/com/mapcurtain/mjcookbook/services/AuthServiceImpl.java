package com.mapcurtain.mjcookbook.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mapcurtain.mjcookbook.entities.User;
import com.mapcurtain.mjcookbook.repositories.ProfileRepository;
import com.mapcurtain.mjcookbook.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProfileRepository profileRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User register(User user) {
		String encodedPw = encoder.encode(user.getPassword());
		user.setPassword(encodedPw);
		user.setProfile(profileRepo.saveAndFlush(user.getProfile()));
		user = userRepo.saveAndFlush(user);
		return user;
	}
	
	@Override
	public User update(User user, String username) {
		Optional<User> opt = userRepo.findById(username);
		if (opt.isPresent()) {
			User managedUser = opt.get();
			managedUser.setProfile(profileRepo.saveAndFlush(user.getProfile()));
			managedUser.setPassword(encoder.encode(user.getPassword()));
			managedUser = userRepo.saveAndFlush(managedUser);
			return managedUser;
		} else {
			return null;
		}
	}
	

}
