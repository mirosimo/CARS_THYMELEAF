package com.mirosimo.car_showroom.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	
	//@ExceptionHandler(value = {ApiCarException.class})
	/*public ResponseEntity<Object> handleApiCarException(ApiCarException e) {
		// 1. Create payload containing exception details
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiException apiException = new ApiException(
				e.getMessage(),
				e,
				badRequest,
				ZonedDateTime.now(ZoneId.of("Z"))
		);
		// 2. Return Response entity
		return new ResponseEntity<>(apiException, badRequest);
	}*/
}
