package com.smoothstack.gcfashion.service;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Spring configuration class that sets up beans used in the application
 * 
 * @author jalveste
 *
 */
@EnableSwagger2
@Configuration
public class SpringConfig {

	@Bean
	public StoreService storeServiceBean() {
		return new StoreService();
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/gcfashions/sales/**"))
				.apis(RequestHandlerSelectors.basePackage("com.smoothstack"))
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "GC Fashion Sales API", 
	      "This API manages cash register operations for GC Fashion retail stores.", 
	      "API TOS", 
	      "Terms of service", 
	      new Contact("Team Smoothies A", "http://www.smoothstack.com", "myeaddress@smoothstack.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
}
