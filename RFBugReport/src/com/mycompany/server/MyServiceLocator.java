/**
 * 
 */
package com.mycompany.server;

import com.google.web.bindery.requestfactory.shared.ServiceLocator;


public class MyServiceLocator implements ServiceLocator {
	  @Override
	  public Object getInstance(Class<?> clazz) {
	    
		  
		 return  DomainService.getInstance();
		}
	}