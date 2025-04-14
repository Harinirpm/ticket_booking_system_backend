package com.movie.ticketbooking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.ticketbooking.model.ScreenEntity;
import com.movie.ticketbooking.repository.ScreenRepository;

@Service
public class ScreenService {
	
	@Autowired
	ScreenRepository screenRepo;
	
	public List<ScreenEntity> getScreensByTheaterId(Long theaterId){
		return screenRepo.findByTheater_TheaterId(theaterId);
	}
}
