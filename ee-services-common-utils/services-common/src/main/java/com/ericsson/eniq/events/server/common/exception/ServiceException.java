/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.exception;

/**
 * Generic exception when an error occurs during a service call
 * 
 * @author eemecoy
 *
 */
public class ServiceException extends RuntimeException {

    /**
     * @param rootException
     */
    public ServiceException(final Exception rootException) {
        super(rootException);
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message The error message
     */
    public ServiceException(final String message) {
        super(message);
    }
}
