/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.exception;

/**
 * exception thrown if no license found for specified feature/cxc number
 * @author EEMECOY
 *
 */
public class NoLicenseException extends RuntimeException {

    /**
     * @param exceptionDetails
     */
    public NoLicenseException(final String exceptionDetails) {
        super(exceptionDetails);
    }

}
