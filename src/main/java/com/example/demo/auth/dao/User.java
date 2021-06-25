package com.example.demo.auth.dao;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Document("user")
@Data
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	public String id;
	
	public String username;
	
	public String password;
	
	public String tenant;
	
	public User(User user){

		this.id = user.id;
		this.username = user.username;
		this.password = user.password;
		this.tenant = user.tenant;				
	}
}
