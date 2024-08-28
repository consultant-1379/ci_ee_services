/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.exception;

/**
 * ExceptionMapper for when an Exception occurs that should result in Error message being returned to UI
 * 
 * @author ejoegaf
 * @since 2011
 *
 */
public class ServiceUserInfoException extends RuntimeException {

    /**
     * @param rootException
     */
    public ServiceUserInfoException(final Exception rootException) {
        super(rootException);
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message The error message
     */
    public ServiceUserInfoException(final String message) {
        super(message);
    }
}
