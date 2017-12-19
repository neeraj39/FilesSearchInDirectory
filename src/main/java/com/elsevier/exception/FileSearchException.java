package com.elsevier.exception;

/**
 * The Class FileSearchException.
 */
public class FileSearchException extends Exception {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public FileSearchException(String errorMessage) {
		
		this.errorMessage = errorMessage;
	}

	public FileSearchException() {
		
	}

}
