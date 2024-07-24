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

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookStoreWebApp.dto.LoginRequest;
import com.example.bookStoreWebApp.model.Users;
import com.example.bookStoreWebApp.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody Users user) {
        userService.saveUser(user);
        return "New User is Added";
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<Users> user = userService.findByUsername(loginRequest.getUsername());
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }


}
