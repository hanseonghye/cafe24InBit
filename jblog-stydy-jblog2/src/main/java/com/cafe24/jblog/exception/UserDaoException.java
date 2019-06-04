package com.cafe24.jblog.exception;

public class UserDaoException extends RuntimeException {

	private static final long serialVersionUID = 1L; 
	
	public UserDaoException() {
		super("userdao exception occurs");
	}
	
	public UserDaoException(String msg) {
		super(msg);
	}
}
