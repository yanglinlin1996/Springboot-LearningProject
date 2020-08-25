package com.linlinyang.restservices.springbootbuildingblocks.exceptions;

public class UserExistsException extends Exception {

	private static final long serialVersionUID = -9155742669329530299L;

	public UserExistsException(String message) {
		super(message);
	}
	
}
