/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config;

/**
 * Exception for problems accessing or reading properties from the
 * Glassfish AMX JMX bean
 * 
 * @author eemecoy
 *
 */
public class CannotReadAMXPropertyException extends Exception {

    public CannotReadAMXPropertyException(final Exception rootException) {
        super(rootException);
    }

}
