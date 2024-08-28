/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.common.exception;

/**
 * exception thrown if no RoleBased Permission found for specified user
 * @author EFLATIB
 */

public class NoRolePermissionException extends Exception {
    /**
     * @param exceptionDetails
     */
    public NoRolePermissionException(final String exceptionDetails) {
        super(exceptionDetails);
    }
}