package com.intelligentLibrary.test.hibernate;

public class User {
	public User() {
		super();
		
	}
	private Integer id;
	private String password;
	private String username;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User(Integer id, String password, String username) {
		super();
		this.id = id;
		this.password = password;
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
