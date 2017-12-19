package com.elsevier.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.elsevier.bean.ErrorResponse;
import com.elsevier.exception.FileSearchException;

/**
 * The Class ExceptionControllerAdvice.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	/**
	 * Exception handler to handle all exception types.
	 *
	 * @param e
	 * @return error Response
	 */
	@ExceptionHandler(Exception.class)
	ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage("Please contact your Administartor");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
	
	/**
	 * This method will handle  the exceptions that occur during the search for the user input string when it is not found in subdirectories
	 *
	 * @param	genExp	Exception raised during pattern search.
	 * @return 	error 	Holds the exceptional response with error code.
	 */
	@ExceptionHandler(FileSearchException.class)
	ResponseEntity<ErrorResponse> noFileFoundExceptionHandler(Exception genExp) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NO_CONTENT.value());
		error.setMessage("No Result Found");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
