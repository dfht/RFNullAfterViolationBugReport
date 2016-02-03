/**
 * 
 */
package com.mycompany.shared;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.mycompany.server.DomainService;
import com.mycompany.server.MyServiceLocator;



@Service(value=DomainService.class, locator = MyServiceLocator.class)
public interface DomainRequestContext extends RequestContext {

	
	Request<Void> save(UserProxy article);
	
	Request<UserProxy> user();
	
}
