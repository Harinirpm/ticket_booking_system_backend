package com.movie.ticketbooking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
		private int userId;
	    @Column(name = "name")
		private String username;
	    @Column(name = "role")
		private String role;
	    @Column(name ="mail_id")
		private String email;
	    @Column (name = "password")
	    private String password;
	    @Column (name = "phone_no")
	    private String phonenumber;
	    
	    public UserEntity(int userId, String username, String role, String email,String password,String phonenumber) {
	    	this.userId = userId;
	    	this.username = username;
	    	this.role = role;
	    	this.email = email;
	    	this.password = password;
	    	this.phonenumber = phonenumber;
	    }
	    public UserEntity() {
	    	
	    }
	    public void setUserId(int userId) {
	    	this.userId = userId;
	    }
	    public int getUserId() {
	    	return userId;
	    }
	    public void serUserName(String username) {
	    	this.username = username;
	    }
	    public String getUsername() {
	    	return username;
	    }
	    public void setRole(String role) {
	    	this.role = role;
	    }
	    public String getRole() {
	    	return role;
	    }
	    public void setEmail(String email) {
	    	this.email = email;
	    }
	    public String getEmail() {
	        return email;
         }
	    public void setPassword(String password) {
	    	this.password = password;
	    }
	    public String getPassword() {
	        return password;
         }
	    public void setPhonenumber(String phonenumber) {
	    	this.phonenumber = phonenumber;
	    }
	    public String getPhonenumber() {
	    	return phonenumber;
	    }
	    
	    
	}
