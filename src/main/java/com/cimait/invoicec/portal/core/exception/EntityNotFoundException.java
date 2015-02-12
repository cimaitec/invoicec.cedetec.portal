package com.cimait.invoicec.portal.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2880079394459889901L;
}
	
