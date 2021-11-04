package com.jb.springbootrestapi.service.model;

public enum Status {
	OPEN(1), APPROVED(3), FAILED(4), REVIEW(2);
	
	Status(int code) {
		this.code = code;
	}

	private int code;
	
	
}
