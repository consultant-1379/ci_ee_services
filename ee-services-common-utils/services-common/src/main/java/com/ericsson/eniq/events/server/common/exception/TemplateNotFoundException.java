/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.exception;

import org.apache.commons.collections.keyvalue.MultiKey;

/**
 * Exception to indicate that template matching specified set of parameters does not exist
 * Should never occur in production environment - exception thrown to aid debugging in development
 * 
 * @author eemecoy
 *
 */
public class TemplateNotFoundException extends RuntimeException {

    /**
     * @param multiKey
     */
    public TemplateNotFoundException(final MultiKey multiKey) {
        super("No template found to match: " + multiKey);
    }

    /**
     * @param multiKey
     * @param multiKeyWithoutTimeRange
     */
    public TemplateNotFoundException(final MultiKey firstMultiKey, final MultiKey secondMultiKey) {
        super("No template found to match: " + firstMultiKey + " or " + secondMultiKey);
    }

}
