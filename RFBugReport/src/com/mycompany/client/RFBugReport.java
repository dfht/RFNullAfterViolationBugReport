package com.mycompany.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.mycompany.shared.IDomainRequestFactory;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class RFBugReport implements EntryPoint {
	
	private EventBus eventBus;
	private IDomainRequestFactory factory;
	
	
	private static RFBugReport application;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		application = this;
		this.eventBus = new SimpleEventBus();
		this.factory = GWT.create(IDomainRequestFactory.class);
		factory.initialize(eventBus);
		
		
		showUpToDateUserScreen();
		
		
		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		
	

	
	}
	
	
	
	
	public static RFBugReport getApplication() {
		return application;
	}




	public void showUpToDateUserScreen()
	{
		RootPanel root = RootPanel.get("container");
		root.clear();
		
		UserScreen screen = new UserScreen(eventBus, factory);
		root.add(screen);
		screen.populate();
		
		
	}
}
