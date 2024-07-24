package com.example.bookStoreWebApp.repository;

import com.example.bookStoreWebApp.model.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
	Optional<Users> findByUsername(String userName);
}
