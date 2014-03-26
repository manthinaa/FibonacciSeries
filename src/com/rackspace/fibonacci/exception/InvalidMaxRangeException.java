package com.rackspace.fibonacci.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class InvalidMaxRangeException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public InvalidMaxRangeException() {
		super(Response.status(Response.Status.BAD_REQUEST).build());
	}

	public InvalidMaxRangeException(String message) {
		super(Response.status(Response.Status.BAD_REQUEST).entity(message)
				.type(MediaType.TEXT_PLAIN).build());
	}

}
