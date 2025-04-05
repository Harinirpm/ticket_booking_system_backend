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
@Table(name="genres")
public class GenreEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "genreId")
	private Long genreId;
	@Column(name = "genrename", unique = true, nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "genre") //movies -> genre field 
	private List<MoviesEntity> movies;
	
	
	public GenreEntity(Long genreId, String name) {
		this.genreId = genreId;
		this.name = name;
	}
	
	public GenreEntity() {}
	
	public long getGenreId() {
		return genreId;
	}
	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
