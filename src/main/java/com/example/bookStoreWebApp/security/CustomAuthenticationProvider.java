package com.example.bookStoreWebApp.security;

import com.example.bookStoreWebApp.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;

    @Autowired
    public CustomAuthenticationProvider(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userService.loadUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(user, password, Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
