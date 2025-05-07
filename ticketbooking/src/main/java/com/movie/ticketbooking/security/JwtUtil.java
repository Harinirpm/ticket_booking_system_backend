package com.movie.ticketbooking.security;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.movie.ticketbooking.model.UserEntity;
import com.movie.ticketbooking.repository.UsersRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtil {

//  private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	private static final SecretKey SECRET_KEY = new SecretKeySpec("4w2IsNb1HnxwhJ8Vqlpivd675lr9VqZcCvF3NlD/LitcwOj0uB5jlBG91JncPYWrUSGVjMYyCdip/i3JmtfGtw==".getBytes(StandardCharsets.UTF_8),"HmacSHA512");
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(UserEntity user) {
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("name", user.getUsername())
                .claim("role","ROLE_"+user.getRole().toUpperCase()) 
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS512)
                .compact();
        System.out.println("Generated Token: " + token);
        return token;
    }

    public Claims parseToken(String token) {
        try {
        	Claims claims =  Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("Parsed JWT Token Claims: " + claims);
            return claims;
        } catch (JwtException e) {
            throw new RuntimeException("Invalid or expired JWT token", e);
            
        }
    }
    public String getEmailFromToken(String token) {
        return parseToken(token).getSubject();
    }
    public String getRoleFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("role", String.class); 
    }
    public boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }
}

