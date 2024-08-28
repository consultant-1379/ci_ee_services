/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.exception;

/**
 * Exception thrown if the services layer cannot access the ENIQ licensing service
 * @author eemecoy
 *
 */
public class CannotAccessLicensingServiceException extends RuntimeException {

    /**
     * @param rootException
     */
    public CannotAccessLicensingServiceException(final Exception rootException) {
        super(rootException);
    }

}
