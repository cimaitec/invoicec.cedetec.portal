package com.cimait.invoicec.portal.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerInfoException extends RuntimeException {

	private static final long serialVersionUID = 8268629272145197185L;

}
