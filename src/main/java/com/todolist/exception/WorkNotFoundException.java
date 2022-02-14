package com.todolist.exception;

/**
 * @author Admin
 *
 */
public class WorkNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	

	/**
	 * @param string
	 */
	public WorkNotFoundException(String message) {
		super(message);
	}
}
