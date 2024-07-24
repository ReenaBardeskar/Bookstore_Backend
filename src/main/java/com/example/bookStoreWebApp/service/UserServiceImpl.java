package com.example.bookStoreWebApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.bookStoreWebApp.model.Users;
import com.example.bookStoreWebApp.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Users saveUser(Users user) {
		return userRepository.save(user);
	}
	

    public Optional<Users> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

}
