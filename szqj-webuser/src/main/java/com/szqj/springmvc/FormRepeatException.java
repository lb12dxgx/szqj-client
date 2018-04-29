package com.szqj.springmvc;

public class FormRepeatException extends RuntimeException {
	 
    public FormRepeatException(String message){ super(message);}
 
    public FormRepeatException(String message, Throwable cause){ super(message, cause);}
}