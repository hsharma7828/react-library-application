package com.learning.spring_boot_library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.learning.spring_boot_library.entity.Book;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer{
	
	private String theAllowedOrigins = "http://localhost:3000";
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, 
			CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions =  {HttpMethod.POST, 
														HttpMethod.PATCH, 
														HttpMethod.DELETE, 
														HttpMethod.PUT};
		
		config.exposeIdsFor(Book.class);
		
		disableHttpMethods(Book.class, config,theUnsupportedActions);
		
		/* Configure CORS Mapping */
		cors.addMapping(config.getBasePath() + "/**")
		.allowedOrigins(theAllowedOrigins);
	}

	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config,HttpMethod[] theUnsupportedActions) {

		config.getExposureConfiguration().
		forDomainType(theClass)
		.withItemExposure((metData, httpMethods) -> httpMethods.disable(theUnsupportedActions))
		.withCollectionExposure((metData, httpMethods) -> httpMethods.disable(theUnsupportedActions));
		
	}
}
