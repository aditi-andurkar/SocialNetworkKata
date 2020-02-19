package com.adi.kata.restsocialnetwork.socialnetwork.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adi.kata.restsocialnetwork.socialnetwork.user.repository.PostRepository;
import com.adi.kata.restsocialnetwork.socialnetwork.user.repository.UserRepository;
import com.adi.kata.restsocialnetwork.socialnetwork.user.model.Post;
import com.adi.kata.restsocialnetwork.socialnetwork.user.model.User;

@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;
	
	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsForUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
		
	}

	@Override
	public void deletePostById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	

}
