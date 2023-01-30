package com.demo.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.blog.entities.Category;
import com.demo.blog.entities.Post;
import com.demo.blog.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContening(String title);
}
