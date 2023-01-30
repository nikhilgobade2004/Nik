package com.demo.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.blog.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
