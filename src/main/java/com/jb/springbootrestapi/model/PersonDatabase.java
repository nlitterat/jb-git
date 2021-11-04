package com.jb.springbootrestapi.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PersonDatabase {
	private List<Person> people= new ArrayList<Person>();
	
	public List<Person> getPeople() {
		
		return people;
	}
}
