package com.jb.springbootrestapi;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jb.springbootrestapi.model.Person;

public class RestTemplateApp {
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8099/people";
		ResponseEntity<List> forEntity = restTemplate.getForEntity(url, List.class);
		System.out.println(forEntity.getStatusCode().name());
		System.out.println(forEntity.getBody());
		ResponseEntity<Person[]> forEntity2 = restTemplate.getForEntity(url, Person[].class);
		System.out.println(Arrays.toString(forEntity2.getBody()));
		ResponseEntity<Void> postForEntity = restTemplate.postForEntity(url, new Person("ddd", 77), Void.class);
		System.out.println(postForEntity.getStatusCode().name());
		restTemplate.delete(url + "/nehemia");

		ResponseEntity<Void> exchange = restTemplate.exchange(url + "/ddd", HttpMethod.DELETE, null, Void.class);
		System.out.println(exchange.getStatusCode().name());
		
	}
}
