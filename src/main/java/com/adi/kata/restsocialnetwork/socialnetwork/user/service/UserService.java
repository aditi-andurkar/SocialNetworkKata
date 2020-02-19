package com.adi.kata.restsocialnetwork.socialnetwork.user.service;

import java.util.List;

import com.adi.kata.restsocialnetwork.socialnetwork.user.model.User;

public interface UserService {

	public List<User> getAllUsers();
	public User getUserById(int id);
	public User save(User user);
	public void deleteById(Integer id);
}
