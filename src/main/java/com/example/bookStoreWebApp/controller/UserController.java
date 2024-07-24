//package com.example.bookStoreWebApp.controller;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.bookStoreWebApp.model.Users;
////import com.example.bookStoreWebApp.security.JwtTokenUtil;
//
//
//import com.example.bookStoreWebApp.service.UserService;
//
//
//@RestController
//@RequestMapping("/user")
//@CrossOrigin
//public class UserController {
//
//	@Autowired
//	private UserService userService;
////	 @Autowired
////	private JwtTokenUtil jwtTokenUtil;
//	
//	@PostMapping("/add")
//	public String add(@RequestBody Users user) {
//		userService.saveUser(user);
//		return "New User is Added";
//	}
//
////    @PostMapping("/login")
////    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
////        Optional<Users> user = userService.findByUsername(loginRequest.getUsername());
////        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
////            String token = jwtTokenUtil.generateToken(user.get().getUserName());
////            return ResponseEntity.ok(token);
////        }
////        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
////    }
//
//
//}


package com.example.bookStoreWebApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookStoreWebApp.dto.LoginRequest;
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
    
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        Optional<Users> user = userService.findByUsername(loginRequest.getUsername());
//        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        }
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//    }
    
    // Login Endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String username = loginRequest.getUsername(); // Extract username from the login request
        String token = jwtUtil.generateToken(username);

        return ResponseEntity.ok("Bearer " + token);
    }


}
