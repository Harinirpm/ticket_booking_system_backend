package com.movie.ticketbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.ticketbooking.model.ScreenEntity;

@Repository
public interface ScreenRepository extends JpaRepository<ScreenEntity, Long>{
	List<ScreenEntity> findByTheater_TheaterId(Long theaterId);
}
