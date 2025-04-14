package com.movie.ticketbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="screen")
public class ScreenEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "screenId")
	private Long screenId;
	
	@Column(name="screenNumber")
	private Long screenNumber;
	
	@Column(name="totalSeats")
	private Long totalSeats;
	
	@ManyToOne
	@JoinColumn(name="theaterId",nullable=false)
	@JsonIgnore
	private TheaterEntity theater;

	public ScreenEntity() {}

	public ScreenEntity(Long screenId, Long screenNumber, Long totalSeats, TheaterEntity theater) {
		super();
		this.screenId = screenId;
		this.screenNumber = screenNumber;
		this.totalSeats = totalSeats;
		this.theater = theater;
	}

	public Long getScreenId() {
		return screenId;
	}

	public void setScreenId(Long screenId) {
		this.screenId = screenId;
	}

	public Long getScreenNumber() {
		return screenNumber;
	}

	public void setScreenNumber(Long screenNumber) {
		this.screenNumber = screenNumber;
	}

	public Long getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Long totalSeats) {
		this.totalSeats = totalSeats;
	}

	public TheaterEntity getTheater() {
		return theater;
	}

	public void setTheater(TheaterEntity theater) {
		this.theater = theater;
	}
	
	
	
	
	
}
