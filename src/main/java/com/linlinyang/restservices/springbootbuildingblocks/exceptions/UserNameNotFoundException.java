package com.linlinyang.restservices.springbootbuildingblocks.exceptions;

public class UserNameNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	// Superclass Constructor
	public UserNameNotFoundException(String message) {
		super(message);
	}

}
