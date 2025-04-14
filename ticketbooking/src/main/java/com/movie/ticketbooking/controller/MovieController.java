package com.movie.ticketbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.ticketbooking.model.MoviesEntity;
import com.movie.ticketbooking.services.MovieService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
	MovieService movieService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addMovie")
	public MoviesEntity addMovie(@RequestBody MoviesEntity movie) {
		return movieService.addMovie(movie);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/updateMovie/{id}")
	public MoviesEntity updateMovie(@PathVariable Long id, @RequestBody MoviesEntity updatedMovie) {
		return movieService.updateMovie(id, updatedMovie);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getAllMovies")
	public List<MoviesEntity> getAllMovies(HttpServletResponse response){
		response.setHeader("Cache-Control", "no-store");
		return movieService.getAllMovies();
	}
	
}
