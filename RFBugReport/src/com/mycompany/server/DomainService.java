/**
 * 
 */
package com.mycompany.server;


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
		
		//It's a clone that is coming back so let's put the change into the original.
		User.getInstance().applyChangesFrom(user);
	}
	
	
	public User user()
	{
		return User.getInstance().cloned();
	}
}
