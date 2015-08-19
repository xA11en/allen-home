package com.tingyun.api.auto.common;

public class MyException extends RuntimeException{

	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;
	
	 public MyException() 
	 {
	        super();
	 }
	
	public MyException(String string) 
	{
		// TODO Auto-generated constructor stub
		super(string);
	}
	
	public MyException(String string, Throwable throwable)
	{
		
		super(string, throwable);
	}
}
