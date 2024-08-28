/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Read properties from test files 
 * @author eemecoy
 *
 */
public class TestProperties {

    private static final String JDBC_PROPERTIES_FILE = "com/ericsson/eniq/events/server/test/jdbc.properties";

    public String getProperty(final String propertyName) throws IOException {
        final InputStream jdbcPropertiesStream = ClassLoader.getSystemClassLoader().getResourceAsStream(
                JDBC_PROPERTIES_FILE);
        final Properties propertiesInFile = new Properties();
        propertiesInFile.load(jdbcPropertiesStream);
        return propertiesInFile.getProperty(propertyName);
    }

    public String getProperty(final String propertyName, final String defaultPropertyValue) throws IOException {
        final String propertyValue = getProperty(propertyName);
        if (propertyValue == null) {
            return defaultPropertyValue;
        }
        return propertyValue;
    }

}
