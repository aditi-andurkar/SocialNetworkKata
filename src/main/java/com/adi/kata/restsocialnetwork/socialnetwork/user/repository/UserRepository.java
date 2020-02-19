package com.adi.kata.restsocialnetwork.socialnetwork.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adi.kata.restsocialnetwork.socialnetwork.user.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer>{

}
