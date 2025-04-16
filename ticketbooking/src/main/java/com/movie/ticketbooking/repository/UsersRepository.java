package com.movie.ticketbooking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.ticketbooking.model.UserEntity;

@Repository	
public interface UsersRepository extends JpaRepository<UserEntity,Integer>{

	Optional<UserEntity> findByUsername(String username);
	UserEntity findByEmailAndPassword(String email, String password);
}
