package com.demo.blog.service;

import java.util.List;

import com.demo.blog.entities.Post;
import com.demo.blog.payloads.PostDto;
import com.demo.blog.payloads.PostResponse;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//getAll
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir);
	
	//get single Post
	PostDto getPostById(Integer postId);
	
	//get All Post by Category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get All Post by User
	List<PostDto> getPostByUser(Integer userId);
	
	//search Post   
	List<PostDto> searchPosts(String keyword);
}
