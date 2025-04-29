package com.movie.ticketbooking.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie.ticketbooking.model.LoginRequest;
import com.movie.ticketbooking.model.UserEntity;
import com.movie.ticketbooking.payload.LoginResponse;
import com.movie.ticketbooking.security.JwtUtil;
import com.movie.ticketbooking.services.AuthenticationService;
import com.movie.ticketbooking.services.UserServices;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserServices userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(UserServices userService, JwtUtil jwtUtil, AuthenticationService authenticationService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.authenticationService = authenticationService;
    }

  
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            UserEntity authenticatedUser = authenticationService.loginAuthenticate(loginRequest);
            String token = jwtUtil.generateToken(authenticatedUser);

            LoginResponse loginResponse = new LoginResponse(authenticatedUser.getEmail(), authenticatedUser.getRole(), authenticatedUser.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("token", token);
            response.put("user", loginResponse); 

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }


    @GetMapping("/current-user")
    public ResponseEntity<?> checkCurrentUser(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            try {
                Claims claims = jwtUtil.parseToken(jwtToken);
                String email = claims.getSubject();
                Optional<UserEntity> userOpt = userService.getUserByEmail(email);

                if (userOpt.isPresent()) {
                    UserEntity user = userOpt.get();
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "success");
                    response.put("user", user);
                    return ResponseEntity.ok(response);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                            "status", "error",
                            "message", "User not found"
                    ));
                }
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "status", "error",
                        "message", "Invalid or expired token"
                ));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "status", "error",
                "message", "Authorization token is missing or invalid"
        ));
    }
}
