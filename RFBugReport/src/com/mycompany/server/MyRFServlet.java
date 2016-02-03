/**
 * 
 */
package com.mycompany.server;

import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;


public class MyRFServlet extends RequestFactoryServlet {

	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public MyRFServlet() {
		super( new DefaultExceptionHandler(), new MySLD());
	}

	

}
