package com.example.bookStoreWebApp.service;

import java.util.Optional;

import com.example.bookStoreWebApp.dto.UserDto;
import com.example.bookStoreWebApp.dto.UserRegistrationRequestDTO;
import com.example.bookStoreWebApp.model.Users;

public interface UserService{
	
    Users saveUser(Users user);

	  // New method for user registration with optional address
    Users registerUserWithAddress(UserRegistrationRequestDTO requestDTO);
    
    Optional<Users> findByUsername(String userName);
    Optional<UserDto> findUserByUsername(String userName);
    Optional<Users> updateUser(String userName, String firstName, String lastName, String password, String mobileNumber);

}
