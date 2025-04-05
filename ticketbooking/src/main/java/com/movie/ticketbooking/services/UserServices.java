package com.movie.ticketbooking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.movie.ticketbooking.model.UserEntity;
import com.movie.ticketbooking.repository.UsersRepository;

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
	
	
	
}
