package com.adi.kata.restsocialnetwork.socialnetwork.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adi.kata.restsocialnetwork.socialnetwork.user.model.Post;

@Repository("postRepository")
public interface PostRepository extends JpaRepository<Post, Integer>{

}
