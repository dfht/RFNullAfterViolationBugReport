/**
 * 
 */
package com.mycompany.client;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.requestfactory.gwt.client.RequestFactoryEditorDriver;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;
import com.mycompany.shared.DomainRequestContext;
import com.mycompany.shared.IDomainRequestFactory;
import com.mycompany.shared.UserProxy;


public class UserScreen extends Composite implements Editor<UserProxy> {

	private static UserScreenUiBinder uiBinder = GWT
			.create(UserScreenUiBinder.class);

	interface UserScreenUiBinder extends UiBinder<Widget, UserScreen> {
	}
	
	private RequestFactoryEditorDriver<UserProxy, UserScreen> driver;
	
	//Request factory and editor framework.
	interface Driver extends RequestFactoryEditorDriver<UserProxy, UserScreen> {
		}
		

	
	@UiField
	TextBox name;

	
	@UiField
	@Ignore
	Label errorLabel;

	
	
	IDomainRequestFactory factory;
	
	DomainRequestContext editContext;
	UserProxy user;
	
	
	Request<Void> saveRequest;
	
	Receiver<Void> saveReceiver;
	
	
	
	private void showMessage(String message)
	{
		errorLabel.setText(message);
	}
	
	
	public UserScreen(EventBus bus, final IDomainRequestFactory factory) {
		initWidget(uiBinder.createAndBindUi(this));
		this.factory = factory;
		this.driver =  GWT.create(Driver.class);
		driver.initialize(bus, factory, this);
		
		
		
		
		
		
			}
		

	

	//Population.
	 void edit(UserProxy proxy, DomainRequestContext context)
	{
		 this.editContext = context;
		 this.user = proxy;
		 driver.edit( user, editContext);
	}
	
	
	@UiField
	Button button;

	
	private static String escapeHtml(String html) {
	    if (html == null) {
	        return null;
	      }
	      return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(
	          ">", "&gt;");
	}
	private static String text(List<EditorError> errors)
	{
		Set<String> content = new HashSet<String>();
		
		
		StringBuilder b = new StringBuilder();
		
		for (EditorError e : errors) {
			
			StringBuilder s = new StringBuilder();
			
			s.append(e.getAbsolutePath());
			s.append("\n");
			s.append(escapeHtml(e.getMessage()));
			s.append("\n");
			String c = s.toString();
			if(!content.contains(c))
			{
				content.add(c);
				b.append(s);
				
			}
			
			
		}
		
		
		
		return b.toString();
	}
	
	
	private boolean processErrors()
	{
		boolean hasErrors = driver.hasErrors() ;
		String message = hasErrors ? text(driver.getErrors()) : "";
		showMessage(message);
		return hasErrors;
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		
		
		//Flush changes.
		driver.flush();
		boolean hasErrors = processErrors();
		if(!hasErrors)
		{
			if(saveRequest == null) //Change this to true to show what happens when request is rebuilt each time (i.e. it works then).
			{
				saveReceiver = 
						new Receiver<Void>() {
					
					public void onSuccess(Void response) {
						
						//Build a fresh user screen so the view is guaranteed to be up-to-date 
						//and so we have a whole new request ready for the next submit etc.
						RFBugReport.getApplication().showUpToDateUserScreen();
					}
					
					@Override
					public void onConstraintViolation(
							Set<ConstraintViolation<?>> violations) {
						
						driver.setConstraintViolations(violations);
						processErrors();
						
						
						//The super call just converts them all to Violation objects then strangely throws them away 
						//and creates a server failure  with just a generic message that doesn't tell you what failed.
						//and routes it to the onFailure method, which I don't want.
						//I guess the idea was to override onViolation, but that itself is deprecated!!!!!!!!!!!!!
						//super.onConstraintViolation(violations);
					}
					public void onFailure(com.google.web.bindery.requestfactory.shared.ServerFailure error) {
						
						showMessage(error.getMessage());
					}
					
					};
				saveRequest = editContext.save(user);
				saveRequest.to(saveReceiver);
				
			}
			saveRequest.fire();
		}
		
		
		
	}

	void populate()
	{
		DomainRequestContext request = factory.domainRequestContext();
		Request<UserProxy> entityFetchRequest = request.user();
		
		entityFetchRequest.fire(
				new Receiver<UserProxy>() {
					
					public void onSuccess(UserProxy response) {
						
						 DomainRequestContext editContext =  factory.domainRequestContext();
						 UserProxy editingProxy = editContext.edit(response);
						 edit(editingProxy, editContext);
						 
						
					};
					
					@Override
					public void onConstraintViolation(
							Set<ConstraintViolation<?>> violations) {
						
						super.onConstraintViolation(violations);
					}
					public void onFailure(com.google.web.bindery.requestfactory.shared.ServerFailure error) {
						
						showMessage(error.getMessage());
					};
					}
				);
	}
	

}
