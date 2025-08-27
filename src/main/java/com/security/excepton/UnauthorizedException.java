package com.security.excepton;

public class UnauthorizedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	public UnauthorizedException(String msg) {
		super(msg);
	}
}
