package com.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Admin
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorkNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

	/**
	 * @param string
	 */
	public WorkNotFoundException(String message) {
		super(message);
	}
}
