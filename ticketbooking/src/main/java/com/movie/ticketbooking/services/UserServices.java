package com.movie.ticketbooking.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.ticketbooking.model.UserEntity;
import com.movie.ticketbooking.repository.UsersRepository;
import java.util.Optional;

@Service
public class UserServices {
	
	@Autowired
	UsersRepository userRepo;
	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
	
//	public UserServices(UsersRepository userRepo) {
//		this.userRepo = userRepo;
//		this.passwordEncoder = new BCryptPasswordEncoder();
//	}
	
	public UserEntity addUser(UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}
	
	
	public List<UserEntity> getAllUsers(){
		return userRepo.findAll();
	}
	
	public Optional<UserEntity> getUserByEmail(String email) {
	    return userRepo.findByEmail(email);
	}

	
	//authenticate the user .
	public UserEntity authenticate(String email, String password) {
		System.out.println("Inside authenticate method");

		Optional<UserEntity> userEmail = userRepo.findByEmail(email);
		if(userEmail.isEmpty()) {
			throw new BadCredentialsException("Invalid email or password");
		}
		UserEntity user = userEmail.get();
		//converting plaintext into hashed text.
//		String hashedPassword = passwordEncoder.encode(password);
//		user.setPassword(hashedPassword);
//		userRepo.save(user);
		System.out.println("Email from request: " + email);
	    System.out.println("Raw password from request: " + password);
	    System.out.println("Encoded password in DB: " + user.getPassword());
	    System.out.println("Password match result: " + passwordEncoder.matches(password, user.getPassword()));

		if (!passwordEncoder.matches(password, user.getPassword())) {
	        throw new BadCredentialsException("Invalid email or password");
	    }
        return user;
    }


	
}
