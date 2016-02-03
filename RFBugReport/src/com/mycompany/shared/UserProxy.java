package com.mycompany.shared;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.mycompany.server.User;
import com.mycompany.server.UserLocator;

@ProxyFor(value=User.class, locator=UserLocator.class)
public interface UserProxy extends EntityProxy{
	
	
	
	

	public String getName();
	
	public void setName(String name);

}
