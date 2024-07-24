package com.example.bookStoreWebApp.service;

import java.util.Optional;


import com.example.bookStoreWebApp.model.Users;

public interface UserService{
	public Users saveUser(Users user);
    Optional<Users> findByUsername(String userName);

}
