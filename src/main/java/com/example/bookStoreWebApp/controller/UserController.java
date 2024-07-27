package com.example.bookStoreWebApp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookStoreWebApp.dto.JwtResponse;
import com.example.bookStoreWebApp.dto.LoginRequest;
import com.example.bookStoreWebApp.dto.UpdateUserDto;
import com.example.bookStoreWebApp.dto.UserDto;
import com.example.bookStoreWebApp.model.Users;
import com.example.bookStoreWebApp.security.JwtUtil;
import com.example.bookStoreWebApp.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/add")
    public String add(@RequestBody Users user) {
        userService.saveUser(user);
        return "New User is Added";
    }
    
    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String username = loginRequest.getUsername(); // Extract username from the login request
//        String token = jwtUtil.generateToken(username);
//
//        return ResponseEntity.ok("Bearer " + token);
    	 Authentication authentication = authenticationManager.authenticate(
                 new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
         SecurityContextHolder.getContext().setAuthentication(authentication);

         String username = loginRequest.getUsername(); // Extract username from the login request
         String token = jwtUtil.generateToken(username);

         Users user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

         JwtResponse response = new JwtResponse("Bearer " + token, user.getAccountTypeId());

         return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        Optional<UserDto> userDto = userService.findUserByUsername(username);
        return userDto.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PutMapping("/update/{username}")
    public ResponseEntity<Users> updateUser(@PathVariable String username, @RequestBody UpdateUserDto updateUserDto) {
        Optional<Users> updatedUser = userService.updateUser(
                username,
                updateUserDto.getFirstName(),
                updateUserDto.getLastName(), // Only the last name is provided here
                updateUserDto.getPassword(),
                updateUserDto.getMobileNumber()
        );
        return updatedUser.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }



}
