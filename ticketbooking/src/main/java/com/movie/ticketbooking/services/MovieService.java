package com.movie.ticketbooking.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.ticketbooking.model.GenreEntity;
import com.movie.ticketbooking.model.LanguageEntity;
import com.movie.ticketbooking.model.MoviesEntity;
import com.movie.ticketbooking.repository.GenreRepository;
import com.movie.ticketbooking.repository.LanguageRepository;
import com.movie.ticketbooking.repository.MoviesRepository;

@Service
public class MovieService {

	@Autowired
	MoviesRepository movieRepo;
	
	@Autowired
	GenreRepository genreRepository;
	
	@Autowired
	LanguageRepository languageRepo;
	
	public MoviesEntity addMovie(MoviesEntity movie){
		Long genreId = movie.getGenre().getGenreId();
		Long languageId = movie.getLanguage().getLanguageId();
		GenreEntity genre = genreRepository.findById(genreId)
				.orElseThrow(() -> new RuntimeException("Genre not found with id: " + genreId));
		LanguageEntity lang = languageRepo.findById(languageId)
				.orElseThrow(() -> new RuntimeException("Language not found with id: " + languageId));
		movie.setGenre(genre);
		movie.setLanguage(lang);
		return movieRepo.save(movie);
	}
	
	public MoviesEntity updateMovie(Long id, MoviesEntity movie) {
		//check if movie exists
		MoviesEntity existingMovie = movieRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
		//to avoid detached error we fetch the genre and lang entities.
		Long genreId = movie.getGenre().getGenreId();
		Long languageId = movie.getLanguage().getLanguageId();
		GenreEntity genre = genreRepository.findById(genreId)
				.orElseThrow(() -> new RuntimeException("Genre not found with id: " + genreId));
		LanguageEntity lang = languageRepo.findById(languageId)
				.orElseThrow(() -> new RuntimeException("Language not found with id: " + languageId));
		
		existingMovie.setGenre(genre);
		existingMovie.setLanguage(lang);
		existingMovie.setDescription(movie.getDescription());
		existingMovie.setDuration(movie.getDuration());
		existingMovie.setTitle(movie.getTitle());
		
		return movieRepo.save(existingMovie);
	}
	
	public List<MoviesEntity> getAllMovies(){
		return movieRepo.findAll();
	}
}
