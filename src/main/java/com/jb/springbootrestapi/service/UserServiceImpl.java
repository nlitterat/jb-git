package com.jb.springbootrestapi.service;

import com.jb.springbootrestapi.exception.AuthorizatioException;
import com.jb.springbootrestapi.service.model.LoginResponse;

public class UserServiceImpl {
	public LoginResponse login(String userName, String password) {
		if (true) {
			return new LoginResponse();
		} else {
			throw new AuthorizatioException();
		}
	}
}
