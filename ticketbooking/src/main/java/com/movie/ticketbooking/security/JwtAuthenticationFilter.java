package com.movie.ticketbooking.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.movie.ticketbooking.services.CustomUserDetailsService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtutil;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public JwtAuthenticationFilter(JwtUtil jwtutil, CustomUserDetailsService customUserDetailsService) {
        this.jwtutil = jwtutil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //check if auth header is empty or not
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(7);
        String userEmail = jwtutil.getEmailFromToken(token);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userEmail);
            System.out.println("User Details :"+userDetails);
            if (!jwtutil.isTokenExpired(token)) {
            	//extract the role from token
            	String role = jwtutil.getRoleFromToken(token);
            	System.out.println("role : "+role);
            	//create authentication token
            	UsernamePasswordAuthenticationToken authToken =
            	        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            	//attach meta data about the http request 
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                System.out.println("Auth Token "+authToken);
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
