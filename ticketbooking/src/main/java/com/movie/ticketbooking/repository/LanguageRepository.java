package com.movie.ticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.ticketbooking.model.LanguageEntity;

@Repository
public interface LanguageRepository extends JpaRepository<LanguageEntity, Long>{

}
