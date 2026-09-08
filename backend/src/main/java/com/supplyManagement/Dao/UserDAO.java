package com.supplyManagement.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.supplyManagement.Entity.User;
import com.supplyManagement.Repository.UserRepository;

@Repository
public class UserDAO {

	@Autowired
	private UserRepository repo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<User> getAllUser() {
		return repo.findAll();

	}

	public User createuser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}

}
