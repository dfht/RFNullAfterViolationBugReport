package com.mycompany.server;

import com.google.web.bindery.requestfactory.shared.Locator;

public class UserLocator extends Locator<User, Integer> {

	@Override
	public User create(Class<? extends User> clazz) {
		// Not needed
		return null;
	}

	@Override
	public User find(Class<? extends User> clazz, Integer id) {
		return User.getInstance();
	}

	@Override
	public Class<User> getDomainType() {
		return User.class;
	}

	@Override
	public Integer getId(User domainObject) {
		return 1;
	}

	@Override
	public Class<Integer> getIdType() {
		return Integer.class;
	}

	@Override
	public Object getVersion(User domainObject) {
		return 1;
	}
	
	
	

}
