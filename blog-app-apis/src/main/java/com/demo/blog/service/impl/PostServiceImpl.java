package com.demo.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demo.blog.entities.Category;
import com.demo.blog.entities.Post;
import com.demo.blog.entities.User;
import com.demo.blog.exceptions.ResourceNotFoundException;
import com.demo.blog.payloads.PostDto;
import com.demo.blog.payloads.PostResponse;
import com.demo.blog.repository.CategoryRepository;
import com.demo.blog.repository.PostRepository;
import com.demo.blog.repository.UserRepository;
import com.demo.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	//create post
	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category Id", categoryId));
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost=this.postRepository.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	//update post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post newPost=this.postRepository.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	//get delete post
	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepository.delete(post);
	}

	//get all post
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort=sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		/*
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}*/
			
		Pageable pageable=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePosts=this.postRepository.findAll(pageable);
		List<Post> allPosts=pagePosts.getContent();
		List<PostDto> postDto=allPosts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements(pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPagr(pagePosts.isLast());
		return postResponse;
	}

	//get post by postId
	@Override
	public PostDto getPostById(Integer postId) {
		Post posts=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
		PostDto postDto=this.modelMapper.map(posts, PostDto.class);
		return postDto;
	}

	//get posts by categoryId
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts=this.postRepository.findByCategory(category);
		List<PostDto> postDto=posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	//get posts by userId
	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts= this.postRepository.findByUser(user);
		List<PostDto> postDto= posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> post=this.postRepository.findByTitleContening(keyword);
		List<PostDto> newPost=post.stream().map((Posts)->this.modelMapper.map(Posts, PostDto.class)).collect(Collectors.toList());
		return newPost;
	}

}
