package com.movie.ticketbooking.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) 
@EnableWebSecurity		
public class Authentication {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
		return http
				 .cors(Customizer.withDefaults()) 
				.csrf(Customizer -> Customizer.disable())
			       .authorizeHttpRequests(request -> request
			    	   .requestMatchers("/users/login","/users/register").permitAll()
			    	   .requestMatchers("/users/**").hasAnyRole("ADMIN","USER")
					   .requestMatchers("/admin/**").hasRole("ADMIN")
					   .anyRequest().authenticated())
			           .formLogin(AbstractHttpConfigurer::disable)
			           .httpBasic(Customizer.withDefaults())
			           .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			           .build();
			   
	}
	

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		provider.setUserDetailsService(userDetailsService);
		return provider;
		
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.addAllowedOrigin("http://localhost:5173"); 
	    configuration.addAllowedMethod("*"); 
	    configuration.addAllowedHeader("*"); 

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

	
 
	
	
}
