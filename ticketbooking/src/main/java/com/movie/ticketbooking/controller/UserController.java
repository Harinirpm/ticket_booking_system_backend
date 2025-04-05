package com.movie.ticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.ticketbooking.model.UserEntity;
import com.movie.ticketbooking.services.UserServices;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	@Autowired	
	UserServices userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addUser")
	public UserEntity addUser(@RequestBody UserEntity user) {
		return userService.addUser(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user){
		UserEntity savedUser = userService.addUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllUsers")
	public List<UserEntity> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/profile")
	public String profile() {
		return "profile"; 
	}
	
}
