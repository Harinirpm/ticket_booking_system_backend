package com.movie.ticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.ticketbooking.model.ScreenEntity;
import com.movie.ticketbooking.services.ScreenService;

@RestController
@RequestMapping("/screens")
@CrossOrigin(origins = "http://localhost:5173") 
public class ScreenController {

	 @Autowired
	 private ScreenService screenService;
	 
	 //get all screens that are comes under the specific theater id.
	 @PreAuthorize("hasAnyRole('ADMIN','USER')")
	    @GetMapping("/getByTheater/{theaterId}")
	    public List<ScreenEntity> getScreensByTheaterId(@PathVariable Long theaterId) {
	        return screenService.getScreensByTheaterId(theaterId);
	    }
	 
	 
}
