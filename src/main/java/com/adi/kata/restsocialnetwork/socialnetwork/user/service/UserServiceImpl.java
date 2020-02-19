package com.adi.kata.restsocialnetwork.socialnetwork.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adi.kata.restsocialnetwork.socialnetwork.user.repository.PostRepository;
import com.adi.kata.restsocialnetwork.socialnetwork.user.repository.UserRepository;
import com.adi.kata.restsocialnetwork.socialnetwork.user.model.Post;
import com.adi.kata.restsocialnetwork.socialnetwork.user.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired 
	private UserRepository userRepository;
	
	@Autowired 
	private PostRepository postRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public User save(User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

}
