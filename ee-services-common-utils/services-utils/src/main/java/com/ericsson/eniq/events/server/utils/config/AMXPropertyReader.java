/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config;

import java.lang.management.ManagementFactory;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.management.MBeanServer;
import javax.management.ObjectName;

/**
 * Access Glassfish properties and resources via the AMX JMX beans in Glassfish
 * 
 * @author eemecoy
 *
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class AMXPropertyReader {

    /**
     * Get the specified attribute from AMX for the specified propertyName of type propertyType
     * This method will look up the bean "amx:pp=/domain/resources,type=" + propertyType + ",name=" + propertyName
     * and call getAttribute() on this bean
     * 
     * @param propertyType              eg jdbc-resource or jdbc-connection-pool
     * @param propertyName              eg EniqPool1
     * @param attributeName             eg MaxPoolSize
     * @return
     * @throws CannotReadAMXPropertyException
     */
    public String getAttribute(final String propertyType, final String propertyName, final String attributeName)
            throws CannotReadAMXPropertyException {
        final MBeanServer platformMBeanServer = getPlatformMBeanServerFromManagementFactory();

        ObjectName poolObjectName;
        try {
            poolObjectName = new ObjectName("amx:pp=/domain/resources,type=" + propertyType + ",name=" + propertyName);
            return (String) platformMBeanServer.getAttribute(poolObjectName, attributeName);
        } catch (final Exception e) {
            throw new CannotReadAMXPropertyException(e);
        }
    }

    /**
     * extracted out for unit test
     * @return
     */
    MBeanServer getPlatformMBeanServerFromManagementFactory() {
        return ManagementFactory.getPlatformMBeanServer();
    }

}
