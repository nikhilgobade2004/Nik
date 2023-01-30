package com.demo.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 4,message="User name must be minimum of 4 characters")
	private String name;
	
	@Email(message="Email address is not valid !!!")
	private String email;
	
	@NotEmpty
	@Size(min = 3,max = 10,message="Passward must be minimum of 3 and maximum of 10 charecters.")
	//@Pattern(regexp = )
	private String passward;
	
	@NotEmpty
	private String about;
}
