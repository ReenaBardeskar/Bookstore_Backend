package com.example.bookStoreWebApp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import com.example.bookStoreWebApp.service.EmailService;
import com.example.bookStoreWebApp.service.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

//    @PostMapping("/add")
//    public String add(@RequestBody Users user) {
//        userService.saveUser(user);
//        return "New User is Added";
//    }
    
    
 // // Registration Endpoint
    @PostMapping("/add")
    public String add(@RequestBody Users user) {
        user.setAccountStatusId(0); // 0 for inactive
        Users savedUser = userService.saveUser(user);

        // Generate verification token
        Map<String, Object> claims = new HashMap<>();
        String token = jwtUtil.generateToken(claims, savedUser.getUserName());
        String confirmationUrl = "http://localhost:8080/user/confirm?token=" + token;

        emailService.sendEmail(user.getEmail(), "Account Confirmation", "Please confirm your account by clicking on this link: " + confirmationUrl);

        return "Registration successful. Please check your email to confirm your account.";
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmUser(@RequestParam("token") String token) {
        String username = jwtUtil.extractUsername(token);
        Users user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update user account status
        user.setAccountStatusId(1); // Assuming 1 is the ID for active status
        userService.saveUser(user);

        return ResponseEntity.ok("User verified successfully");
    }
    
    // Login Endpoint
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
////        Authentication authentication = authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
////        SecurityContextHolder.getContext().setAuthentication(authentication);
////
////        String username = loginRequest.getUsername(); // Extract username from the login request
////        String token = jwtUtil.generateToken(username);
////
////        return ResponseEntity.ok("Bearer " + token);
//    	 Authentication authentication = authenticationManager.authenticate(
//                 new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//         SecurityContextHolder.getContext().setAuthentication(authentication);
//
//         String username = loginRequest.getUsername(); // Extract username from the login request
//         String token = jwtUtil.generateToken(username);
//
//         Users user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
//
//         JwtResponse response = new JwtResponse("Bearer " + token, user.getAccountTypeId());
//
//         return ResponseEntity.ok(response);
//    }
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Fetch user details
        Users user = userService.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check account status
        if (user.getAccountStatusId() == 0) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED).body("Account not active. Please check your email to activate your account.");
        }

        // Generate token
        Map<String, Object> claims = new HashMap<>();
        String token = jwtUtil.generateToken(claims, loginRequest.getUsername());

        // Create response with token and account type ID
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
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        Users user = userService.findByUsername(username).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username not found");
        }

        Map<String, Object> claims = new HashMap<>();
        String token = jwtUtil.generateToken(claims, username);
        String resetUrl = "http://localhost:3000/reset-password?token=" + token + "&username=" + username;

        emailService.sendEmail(user.getEmail(), "Password Reset Request", "Click the link to reset your password: " + resetUrl);

        return ResponseEntity.ok("A password reset email has been sent.");
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("password");

        String username = jwtUtil.extractUsername(token);
        Users user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(newPassword);  // Not encrypting the password
        userService.saveUser(user);

        return ResponseEntity.ok("Your password has been reset.");
    }





}
