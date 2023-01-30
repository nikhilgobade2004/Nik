package com.demo.blog.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.blog.payloads.ApiResponse;
import com.demo.blog.payloads.UserDto;
import com.demo.blog.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		UserDto createUserDto=userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId")Integer uId,@RequestBody UserDto userDto){
		UserDto updatedUserDto=userService.updateUser(userDto, uId); 
		return new ResponseEntity<UserDto>(updatedUserDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId")Integer uId){
		userService.deleteUser(uId);
		//return new ResponseEntity<>("User has been deleted",HttpStatus.OK);
		//return new ResponseEntity<>(Map.of("message","User has been deleted"),HttpStatus.OK);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted sucessfully",true),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers(){
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSinglrUser(@PathVariable("userId") Integer uId){
		return ResponseEntity.ok(this.userService.getUserById(uId));	
	}
	
}
