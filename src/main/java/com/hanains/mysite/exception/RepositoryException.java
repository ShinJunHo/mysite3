package com.hanains.mysite.exception;

public class RepositoryException extends RuntimeException {
	public RepositoryException() {
		// TODO Auto-generated constructor stub
		super("Repository Exception");
	}
	public RepositoryException(String message){
		super("RepositoryException :: "+message);
	}
	
}
