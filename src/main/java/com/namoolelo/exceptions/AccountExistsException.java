package com.namoolelo.exceptions;

public class AccountExistsException  extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -6853421177267925904L;

	public AccountExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountExistsException(String message) {
        super(message);
    }

    public AccountExistsException() {
        super();
    }
}
