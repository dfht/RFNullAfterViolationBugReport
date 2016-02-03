/**
 * 
 */
package com.mycompany.server;

import java.lang.annotation.ElementType;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.validation.client.impl.ConstraintViolationImpl;
import com.google.gwt.validation.client.impl.PathImpl;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;

/**
 * Copyright © Serisys Limited 2013-2016
 * @author Darren
 *
 * 2 Feb 2016
 */
public class MySLD extends ServiceLayerDecorator {

	/**
	 * 
	 */
	public MySLD() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> Set<ConstraintViolation<T>> validate(T domainObject) {
		
		//I don't care about JSR 303 validation here so I haven't wired it in.
		final Set<ConstraintViolation<T>> violations = new HashSet<ConstraintViolation<T>>();
				//super.validate(domainObject);
		if(domainObject instanceof User)
		{
			User user = (User) domainObject;
			String name = user.getName();
			if(name != null)
			{
				
				String m = "Value for name ({0}) is not null";
				m = MessageFormat.format(m, name);
				ConstraintViolation<T> violation = ConstraintViolationImpl.<T> builder() //
				        .setConstraintDescriptor(null) //
				        .setInvalidValue(null) //
				        .setLeafBean(user) //
				        .setMessage(m) //
				        .setMessageTemplate(m) //
				        .setPropertyPath(new PathImpl()) //
				        .setRootBean((T) user) //
				        .setRootBeanClass((Class<T>) user.getClass()) //
				        .setElementType(ElementType.TYPE) //
				        .build();
				violations.add(violation);
			}
		}
		return violations;
	}
	
	
	@Override
	public void setProperty(Object domainObject, String property,
			Class<?> expectedType, Object value) {
		super.setProperty(domainObject, property, expectedType, value);
	}
}
