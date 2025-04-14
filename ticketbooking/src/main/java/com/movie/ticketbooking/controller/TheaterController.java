package com.movie.ticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.ticketbooking.model.TheaterEntity;
import com.movie.ticketbooking.services.TheaterService;

@RestController
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("/theater")
public class TheaterController {

	@Autowired
	TheaterService theaterservice;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addTheater")
	public TheaterEntity addTheater(@RequestBody TheaterEntity theater) {
		return theaterservice.addTheater(theater);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getAllTheater")
	public List<TheaterEntity> getAllTheater(){
		return theaterservice.getAllTheater();
	}
	
}
