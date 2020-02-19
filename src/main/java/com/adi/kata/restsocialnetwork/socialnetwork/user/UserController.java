package com.adi.kata.restsocialnetwork.socialnetwork.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.adi.kata.restsocialnetwork.socialnetwork.Exception.UserException;
import com.adi.kata.restsocialnetwork.socialnetwork.user.model.Post;
import com.adi.kata.restsocialnetwork.socialnetwork.user.model.User;
import com.adi.kata.restsocialnetwork.socialnetwork.user.service.UserService;
import com.adi.kata.restsocialnetwork.socialnetwork.user.service.PostService;

import com.adi.kata.restsocialnetwork.socialnetwork.user.model.Response;



@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger("UserController.class");
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService PostService;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> retrieveAllUsers(){
		logger.info("Returning all the users");
		return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> retrievesUserById(@PathVariable int id) throws UserException{
		logger.info("User id to return " + id);
		User user = userService.getUserById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}/posts")
	public ResponseEntity<List<Post>> retrieveAllPostsForAUser(@PathVariable int id) throws UserException{
		logger.info("Getting all posts of User id " + id);
		return new ResponseEntity<List<Post>>(userService.getUserById(id).getPosts(), HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Response> deleteUserById(@PathVariable int id) throws UserException{
		logger.info("User id to delete " + id);
		User user = userService.getUserById(id);
		userService.deleteById(id);
		return new ResponseEntity<Response>(new Response(HttpStatus.OK.value(), "User has been deleted"), HttpStatus.OK);
	}
	
	@PostMapping("/users/")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws UserException, Exception{
		logger.info("User to be created name = " + user.getName());
		if(user.getId() != null) {
			throw new Exception();
		}
		User userSaved = userService.save(user);
		return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
	}
	
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> savePost(@PathVariable int id, @RequestBody Post post) throws UserException{
		logger.info("Post to be created for user id = " + id);
		User user = userService.getUserById(id);
		post.setUser(user);
		Post dbPost = PostService.save(post);
		return new ResponseEntity<Post>(dbPost, HttpStatus.CREATED);
	}
}
