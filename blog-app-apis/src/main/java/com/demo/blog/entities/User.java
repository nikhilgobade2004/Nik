package com.demo.blog.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User")
@NoArgsConstructor
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	public int id;
	
	@Column(name="User_Name",nullable = false,length = 50)
	public String name;
	
	@Column(name="Email")
	public String email;
	
	@Column(name="PassWard")
	public String passward;
	
	@Column(name="About")
	public String about;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Post> posts=new ArrayList<>();
}
