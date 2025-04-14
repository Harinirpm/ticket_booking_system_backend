package com.movie.ticketbooking.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="theater")
public class TheaterEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="theaterId")
	private Long theaterId;
	
	@Column(name="theater_name")
	private String theater_name;
	
	@Column(name="theater_location")
	private String theater_location;
	
	@OneToMany(mappedBy="theater",cascade = CascadeType.ALL,orphanRemoval=true)
//	@JsonManagedReference
	private List<ScreenEntity> screens = new ArrayList<>();
	

	public TheaterEntity(Long theaterId, String theater_name, String theater_location) {
		super();
		this.theaterId = theaterId;
		this.theater_name = theater_name;
		this.theater_location = theater_location;
	}
	
	public TheaterEntity() {}
	
	public List<ScreenEntity> getScreens() {
		return screens;
	}
	public void setScreens(List<ScreenEntity> screens) {
		this.screens = screens;
	}
	public Long getTheaterId() {
		return theaterId;
	}
	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}
	public String getTheater_name() {
		return theater_name;
	}
	public void setTheater_name(String theater_name) {
		this.theater_name = theater_name;
	}
	public String getTheater_location() {
		return theater_location;
	}
	public void setTheater_location(String theater_location) {
		this.theater_location = theater_location;
	}
//	public void addScreen(ScreenEntity screen) {
//        screens.add(screen);
//        screen.setTheater(this);
//    }
//
//    public void removeScreen(ScreenEntity screen) {
//        screens.remove(screen);
//        screen.setTheater(null);
//    }
	
	
	
}
