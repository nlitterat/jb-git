package com.jb.springbootrestapi.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.springbootrestapi.exception.ValidationException;
import com.jb.springbootrestapi.model.Person;
import com.jb.springbootrestapi.model.PersonDatabase;
import com.jb.springbootrestapi.service.model.UserDetails;

@RestController
// @RequestMapping(value = "/campany/")
public class PersonController {
	
	  

	@Autowired
	private PersonDatabase personDatabase;

	@Autowired
	private UserDetails userDetails;
	/**
	 * this method throw exception so we can test how we handle exception 
	 * in our system
	 * @return nothing
	 */
	@GetMapping(path = "validationexception")
	public ResponseEntity<Void> validationException() {
		throw new ValidationException();
	}
	//@GetMapping(path = "helloworld", headers = "content-type=text/*")
	@GetMapping(path = "helloworld", consumes = "*/*", produces = "application/json")
	public String helloWorld(@RequestHeader("user-agent") String userAgent, HttpServletRequest request) {
		System.out.println(userDetails);
		
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			System.out.println(headerName + " : " + request.getHeader(headerName));
		}
		
		return "Hello World ofek: " + userAgent;
	}

	@GetMapping(path = "people")
	public ResponseEntity<List<Person>> getAll() {
		return ResponseEntity.ok(personDatabase.getPeople());
		// return
		// ResponseEntity.status(HttpStatus.FORBIDDEN).body(personDatabase.getPeople());
	}

	@GetMapping(path = "people/{personName}")
	public ResponseEntity<Person> getPerson(@PathVariable String personName) {
		Optional<Person> optionalPerson = personDatabase.getPeople().stream()
				.filter(person -> person.getName().equals(personName)).findFirst();

		if (optionalPerson.isPresent()) {
			return ResponseEntity.ok(optionalPerson.get());
		}
		return ResponseEntity.badRequest().header("Yossi", "XDG").build();

		// return
		// ResponseEntity.status(HttpStatus.FORBIDDEN).body(personDatabase.getPeople());
	}

	@GetMapping(path = "people/search")
	public ResponseEntity<List<Person>> searchPerson(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer age) {
		List<Person> searchResult = personDatabase.getPeople().stream().filter(person -> {
			if (name != null && age != null) {
				return person.getName().equals(name) && person.getAge() == age;
			} else {
				if (name != null) {
					return person.getName().equals(name);
				} else {
					if (age != null) {
						return person.getAge() == age;
					}
				}
			}
			return false;
		}).collect(Collectors.toList());
		
		return ResponseEntity.ok(searchResult);

	}

	@PostMapping(path = "people")	
	public ResponseEntity<String> addPerson(@RequestBody Person person) {
		if (personDatabase.getPeople().indexOf(person) < 0) {
			personDatabase.getPeople().add(person);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("person already exist");
		}
	}

	/**
	 * Write a function which accept person find it in the database and update the
	 * age nehemia 47
	 * 
	 * nehemia 80
	 */
	@PutMapping(path = "people")
	public ResponseEntity<String> updatePerson(@RequestBody Person person) {
		List<Person> people = personDatabase.getPeople();
		int personIndex = people.indexOf(person);
		if (personIndex >= 0) {
			people.get(personIndex).setAge(person.getAge());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("person not found");
	}

	/**
	 * Write a function which will delete a person it is enough to send just the
	 * name of the person
	 */

//	@DeleteMapping(path = "people")
	public ResponseEntity<String> deletePerson(@RequestBody Person person) {
		boolean removed = personDatabase.getPeople().remove(person);
		if (removed) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("person not found");
	}
	@DeleteMapping(path = "people/{personName}")
	public ResponseEntity<Map<String, String>> deleteSpecificPerson(@PathVariable String personName) {
		Optional<Person> optionalPerson = personDatabase.getPeople().stream().filter(p -> p.getName().equals(personName))
				.findFirst();
		Map<String, String> returnValue = new HashMap<>();
		if (optionalPerson.isPresent()) {
			personDatabase.getPeople().remove(optionalPerson.get());			
			returnValue.put("reason","Person is deleted successfully");
			return ResponseEntity.ok(returnValue);
		} else {
			returnValue.put("reason","Person not found");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(returnValue);
		}
	}


}
