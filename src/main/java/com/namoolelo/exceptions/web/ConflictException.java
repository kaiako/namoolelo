package com.namoolelo.exceptions.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5957706101565964033L;

	public ConflictException() {
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }
}
