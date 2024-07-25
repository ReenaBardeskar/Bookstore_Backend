package com.example.bookStoreWebApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bookStoreWebApp.dto.UserDto;
import com.example.bookStoreWebApp.model.Users;
import com.example.bookStoreWebApp.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Users user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUserName())
	                .password(user.getPassword())
	                .roles("USER") // Adjust roles as necessary
	                .build();
	    }
	
	@Override
	public Users saveUser(Users user) {
		return userRepository.save(user);
	}
	
	@Override
    public Optional<Users> findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }
	@Override
    public Optional<UserDto> findUserByUsername(String userName) {
        Optional<Users> user = userRepository.findByUsername(userName);
        if (user.isPresent()) {
            Users u = user.get();
            UserDto userDto = new UserDto(u.getUserName(), u.getEmail(), u.getFirstName(), u.getLastName());
            return Optional.of(userDto);
        }
        return Optional.empty();
    }



}
