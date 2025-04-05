package com.movie.ticketbooking.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity	
@Table (name = "Movies")
public class MoviesEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movieId")
	private Long movieId;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name="duration")
	private int duration;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "genreId", referencedColumnName = "genreId")
	private GenreEntity genre;
	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="langId", referencedColumnName = "languageId")
	private LanguageEntity language;
	
	public MoviesEntity(Long movieId, String title, String description, int duration, GenreEntity genre,
			LanguageEntity language) {
		super();
		this.movieId = movieId;
		this.title = title;
		this.description = description;
		this.duration = duration;
		this.genre = genre;
		this.language = language;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public GenreEntity getGenre() {
		return genre;
	}
	public void setGenre(GenreEntity genre) {
		this.genre = genre;
	}
	public LanguageEntity getLanguage() {
		return language;
	}
	public void setLanguage(LanguageEntity language) {
		this.language = language;
	}
	
	
	

}
