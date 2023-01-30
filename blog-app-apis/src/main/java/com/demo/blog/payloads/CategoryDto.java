package com.demo.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	
	@NotEmpty
	@Size(min=4,message="Minimum size of category title should be 4.")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min=10,message="Minimum size of category description should be 10.")
	private String categoryDescription;
}
