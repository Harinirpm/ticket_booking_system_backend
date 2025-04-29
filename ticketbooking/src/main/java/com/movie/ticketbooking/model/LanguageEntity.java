package com.movie.ticketbooking.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="languages")
public class LanguageEntity {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "languageId")
	private Long languageId;
	@Column(name = "langname", unique = true, nullable=false)
	private String langname;
	
	@JsonIgnore
	@OneToMany(mappedBy = "language")
	private List<MoviesEntity> movies;
	
	
	public LanguageEntity(Long languageId, String langname) {
		this.languageId = languageId;
		this.langname = langname;
	}
	public LanguageEntity() {}
	
	
	public List<MoviesEntity> getMovies() {
		return movies;
	}
	public void setMovies(List<MoviesEntity> movies) {
		this.movies = movies;
	}
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}
	public String getLangname() {
		return langname;
	}
	public void setLangname(String langname) {
		this.langname = langname;
	}	
}
