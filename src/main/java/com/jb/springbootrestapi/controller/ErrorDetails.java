package com.jb.springbootrestapi.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorDetails {
	private int code;
	private String message;
}
