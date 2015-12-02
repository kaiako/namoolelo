package com.namoolelo.exceptions.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Chris on 6/30/14.
 */
@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5150242190581789510L;

	public BadRequestException() {
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
