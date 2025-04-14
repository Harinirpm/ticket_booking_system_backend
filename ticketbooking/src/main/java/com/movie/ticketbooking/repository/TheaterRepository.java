package com.movie.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.ticketbooking.model.TheaterEntity;

@Repository
public interface TheaterRepository extends JpaRepository<TheaterEntity, Long>{

}
