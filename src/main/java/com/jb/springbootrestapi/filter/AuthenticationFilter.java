package com.jb.springbootrestapi.filter;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.jb.springbootrestapi.service.model.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;



public class AuthenticationFilter implements Filter, ApplicationContextAware {

	private ApplicationContext context;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authorizationHeader = httpRequest.getHeader("Authorization");
		System.out.println("Working!!!");
//		//create token -> login
//		String jws = Jwts.builder()
//				.setIssuer("Stormpath")
//				.setSubject("msilverman")
//				.claim("name", "Micah Silverman")
//				.claim("scope", "admins")
//				// Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
//				.setIssuedAt(Date.from(Instant.ofEpochSecond(1466796822L)))
//				// Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
//				.setExpiration(Date.from(Instant.ofEpochSecond(4622470422L)))
//				.signWith(
//						Keys.hmacShaKeyFor("sakjdfh askjhf8w rkadjh9832 oaweh".getBytes()))
//				.compact();
//		Jws<Claims> jwsParsed = Jwts.parserBuilder()
//		        .setSigningKey("sakjdfh askjhf8w rkadjh9832 oaweh".getBytes())
//		        .build()	
//		        .parseClaimsJws(jws);

		UserDetails userDetails = context.getBean(UserDetails.class);
		System.out.println(userDetails);

		userDetails.setUserId(324234L);
		chain.doFilter(httpRequest, response);
//		
//		System.out.println(jwsParsed.getBody().getSubject());
//		
//		System.out.println(jws);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;

	}

}
