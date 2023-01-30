package com.demo.blog.service;

import java.util.List;

import com.demo.blog.payloads.UserDto;

public interface UserService {

	public UserDto createUser(UserDto userDto);
	
	public UserDto updateUser(UserDto userDto,Integer userId);
	
	public UserDto getUserById(Integer userId);
	
	public List<UserDto> getAllUser();
	
	public void deleteUser(Integer userId);
	
}
