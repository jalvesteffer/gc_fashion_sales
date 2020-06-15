package com.smoothstack.gcfashion.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class that sets up beans used in the application
 * 
 * @author jalveste
 *
 */
@Configuration
public class SpringConfig {

	@Bean
	public StoreService storeServiceBean() {
		return new StoreService();
	}
}
