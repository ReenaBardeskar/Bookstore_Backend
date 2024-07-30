package com.example.bookStoreWebApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.bookStoreWebApp.dto.UserDto;
import com.example.bookStoreWebApp.dto.UserRegistrationRequestDTO;
import com.example.bookStoreWebApp.model.ShippingAddress;
import com.example.bookStoreWebApp.model.Users;
import com.example.bookStoreWebApp.repository.ShippingAddressRepository;
import com.example.bookStoreWebApp.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService, UserDetailsService{

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private ShippingAddressRepository shippingAddressRepository;
	
	 @Override
	    public Users saveUser(Users user) {
	        return userRepository.save(user);
	    }

	
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
	 public Users registerUserWithAddress(UserRegistrationRequestDTO requestDTO) {
	     Users user = new Users();
	     user.setUserName(requestDTO.getUserName());
	     user.setEmail(requestDTO.getEmail());
	     user.setPassword(requestDTO.getPassword());
	     user.setFirstName(requestDTO.getFirstName());
	     user.setLastName(requestDTO.getLastName());
	     user.setMobileNumber(requestDTO.getMobileNumber());
	     user.setSubscribeToPromo(requestDTO.isSubscribeToPromo() ? true : false);

	     // Save user
	     Users savedUser = saveUser(user);

	     // Handle shipping address
	     if (requestDTO.getShippingAddress() != null) {
	         ShippingAddress address = new ShippingAddress();
	         address.setStreet(requestDTO.getShippingAddress().getStreet());
	         address.setCity(requestDTO.getShippingAddress().getCity());
	         address.setState(requestDTO.getShippingAddress().getState());
	         address.setZipCode(requestDTO.getShippingAddress().getZipCode());
	         address.setUserId(savedUser.getUserId());
	         shippingAddressRepository.save(address);
	     }

	     return savedUser;
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
	
	@Override
	public Optional<Users> updateUser(String username, String firstName, String lastName, String password, String mobileNumber) {
	    Optional<Users> userOptional = userRepository.findByUsername(username);
	    if (userOptional.isPresent()) {
	        Users user = userOptional.get();
	        if (firstName != null && !firstName.isEmpty()) {
	            user.setFirstName(firstName);
	        }
	        if (lastName != null && !lastName.isEmpty()) {
	            user.setLastName(lastName);
	        }
	        if (password != null && !password.isEmpty()) {
	            user.setPassword(password); // Consider hashing the password
	        }
	        if (mobileNumber != null && !mobileNumber.isEmpty()) {
	            user.setMobileNumber(mobileNumber);
	        }
	        // Save and return the updated user
	        return Optional.of(userRepository.save(user));
	    }
	    return Optional.empty();
	}





}
