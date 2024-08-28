/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.exception;

/**
 * Exception thrown if duplicate keys found in the template mapping .csv files in services-templates
 * @author eemecoy
 *
 */
public class DuplicateTemplateKeyException extends RuntimeException {

    public DuplicateTemplateKeyException(final String message) {
        super(message);
    }

}
