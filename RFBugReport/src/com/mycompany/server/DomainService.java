/**
 * 
 */
package com.mycompany.server;

/**
 * Copyright © Serisys Limited 2013-2016
 * @author Darren
 *
 * 2 Feb 2016
 */
public class DomainService {
	
	private static DomainService instance = new DomainService();
	
	
	public static DomainService getInstance() {
		return instance;
	}

	private DomainService()
	{
		
	}
	
	public void save(User user)
	{
		//Don't need to do anything, it's just so we actually get to the validation stage.
		System.out.print(user.getName());
	}
	
	
	public User user()
	{
		return User.getInstance();
	}
}
