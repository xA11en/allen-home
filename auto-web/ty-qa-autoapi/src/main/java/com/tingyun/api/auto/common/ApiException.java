package com.tingyun.api.auto.common;

public class ApiException extends RuntimeException{

	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	
	 public ApiException() 
	 {
	        super();
	 }
	
	public ApiException(String string) 
	{
		// TODO Auto-generated constructor stub
		super(string);
	}
	
	public ApiException(String string, Throwable throwable)
	{
		
		super(string, throwable);
	}
	public ApiException(Throwable throwable)
	{
		
		super(throwable);
	}
}
