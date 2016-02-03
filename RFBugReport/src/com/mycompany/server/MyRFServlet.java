/**
 * 
 */
package com.mycompany.server;

import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

/**
 * Copyright © Serisys Limited 2013-2016
 * @author Darren
 *
 * 2 Feb 2016
 */
public class MyRFServlet extends RequestFactoryServlet {

	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MyRFServlet() {
		super( new DefaultExceptionHandler(), new MySLD());
	}

	

}
