package com.movie.ticketbooking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.ticketbooking.model.TheaterEntity;
import com.movie.ticketbooking.repository.TheaterRepository;

@Service
public class TheaterService {

	@Autowired
	TheaterRepository theaterRepo;
	
	public TheaterEntity addTheater(TheaterEntity theater) {
		return theaterRepo.save(theater);
	}
	
	public List<TheaterEntity> getAllTheater(){
		return theaterRepo.findAll();
	}
}
