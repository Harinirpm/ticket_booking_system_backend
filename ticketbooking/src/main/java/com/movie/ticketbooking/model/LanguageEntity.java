package com.movie.ticketbooking.model;

import java.util.List;

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
	private Long langId;
	@Column(name = "langname", unique = true, nullable=false)
	private String langname;
	
	@OneToMany(mappedBy = "language")
	private List<MoviesEntity> movies;
	
	
	public LanguageEntity(Long langId, String langname) {
		this.langId = langId;
		this.langname = langname;
	}
	public LanguageEntity() {}
	
	
	public List<MoviesEntity> getMovies() {
		return movies;
	}
	public void setMovies(List<MoviesEntity> movies) {
		this.movies = movies;
	}
	public Long getLangId() {
		return langId;
	}
	public void setLangId(Long langId) {
		this.langId = langId;
	}
	public String getLangname() {
		return langname;
	}
	public void setLangname(String langname) {
		this.langname = langname;
	}
	
	
	
	
	
}
