package com.lhs.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false, length=20, unique=true)
	private String userId;
	
	private String password;
	
	private String name;
	
	private String email;

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", userId='" + getUserId() + "'" +
			", password='" + getPassword() + "'" +
			", name='" + getName() + "'" +
			", email='" + getEmail() + "'" +
			"}";
	}

	public void update (User user) {
		this.userId		= user.getUserId();
		this.password	= user.getPassword();
		this.email		= user.getEmail();
		this.name		= user.getName();
	}

	//#################################################################
	// @Getter @Setter
	//#################################################################
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getId() {
		return id;
	}
}
