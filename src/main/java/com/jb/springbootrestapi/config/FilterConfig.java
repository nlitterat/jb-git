package com.jb.springbootrestapi.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextListener;

import com.jb.springbootrestapi.filter.AuthenticationFilter;

@Configuration
public class FilterConfig {
	
//	@Bean
//	@Order(0)
//	public RequestContextListener requestContextListener() {
//	    return new RequestContextListener();
//	}
	@Bean
	@Order(1)
	public Filter authenticationFilter() {
		
		return new AuthenticationFilter();
		
		
	}

}
