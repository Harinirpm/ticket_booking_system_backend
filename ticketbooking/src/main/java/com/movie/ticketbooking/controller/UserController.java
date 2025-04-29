package com.movie.ticketbooking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.movie.ticketbooking.config.AuthenticationConfiguration;
import com.movie.ticketbooking.model.LoginRequest;
import com.movie.ticketbooking.model.UserEntity;
import com.movie.ticketbooking.payload.LoginResponse;
import com.movie.ticketbooking.security.JwtUtil;
import com.movie.ticketbooking.services.UserServices;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

	@Autowired	
	UserServices userService;
	@Autowired
	JwtUtil jwtutil;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/admin/addUser")
	public UserEntity addUser(@RequestBody UserEntity user) {
		return userService.addUser(user);
	}
	
	//register as new user
	@PostMapping("/register")
	public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user){
		UserEntity savedUser = userService.addUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
	}

	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("admin/getAllUsers")
	public List<UserEntity> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/admin/profile")
    public ResponseEntity<String> profile() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName(); 
        return ResponseEntity.ok("Welcome , this is your profile page.");
    }
	
}
