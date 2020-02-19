package com.adi.kata.restsocialnetwork.socialnetwork.user.service;

import java.util.List;

import com.adi.kata.restsocialnetwork.socialnetwork.user.model.Post;
import com.adi.kata.restsocialnetwork.socialnetwork.user.model.User;

public interface PostService {

	public List<Post> getAllPosts();
	public List<Post> getPostsForUser(Integer id);
	public Post getPostById(Integer id);
	public Post save(Post post);
	public void deletePostById(Integer id);
}
