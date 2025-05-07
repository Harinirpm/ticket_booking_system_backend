package com.movie.ticketbooking.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.movie.ticketbooking.model.UserEntity;
import com.movie.ticketbooking.model.UserEntityPrincipal;
import com.movie.ticketbooking.repository.UsersRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	UsersRepository userRepo;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserEntity> users = userRepo.findByUsername(username);
//		
//		UserEntity user = users.orElseThrow(() -> 
//        new UsernameNotFoundException("User not found: " + username)
//    );
//		return new UserEntityPrincipal(user);
//		
//	}
	 @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        UserEntity userEntity = userRepo.findByEmail(email)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

	        return User.builder()
	            .username(userEntity.getEmail())
	            .password(userEntity.getPassword())
	            .roles(userEntity.getRole().toUpperCase())
	            .build();
	    }

	

}
