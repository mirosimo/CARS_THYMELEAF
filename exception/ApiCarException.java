package com.mirosimo.car_showroom.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ApiCarException extends RuntimeException {
	private String errorMessageToStr;
	
	public ApiCarException(String message) {
		super(message);
	}
			
	
	public ApiCarException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	// Return the printStackTrace()
	public String getErrorMessageToStr() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		this.printStackTrace(pw);
		this.errorMessageToStr = sw.toString();
		return this.errorMessageToStr;
	}

  
}
