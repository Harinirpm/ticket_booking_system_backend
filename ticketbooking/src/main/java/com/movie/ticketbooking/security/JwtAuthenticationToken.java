package com.movie.ticketbooking.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public JwtAuthenticationToken(User principal) {
        super(principal, null, principal.getAuthorities());
        super.setAuthenticated(true);
    }
}

