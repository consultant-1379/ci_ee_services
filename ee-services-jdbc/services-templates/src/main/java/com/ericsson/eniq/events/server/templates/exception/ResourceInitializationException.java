package com.ericsson.eniq.events.server.templates.exception;

/**
 * General runtime exception for services layer to encapsulate
 * resource initialization issues. Underlying exceptions will
 * be chained where applicable. 
 * 
 * @author edeccox
 *
 */
public class ResourceInitializationException extends RuntimeException {

	private static final long serialVersionUID = -5876497487603808271L;

	public ResourceInitializationException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
