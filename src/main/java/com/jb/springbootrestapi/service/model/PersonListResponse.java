package com.jb.springbootrestapi.service.model;

import java.util.ArrayList;
import java.util.List;

import com.jb.springbootrestapi.model.Person;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonListResponse  {
	private List<Person> people = new ArrayList<Person>();
}
