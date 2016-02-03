package com.mycompany.server;

public class User {
	
	private static User instance = new User();
	
	
	
	
	public static User getInstance() {
		return instance;
	}

	public User() {
		super();
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
