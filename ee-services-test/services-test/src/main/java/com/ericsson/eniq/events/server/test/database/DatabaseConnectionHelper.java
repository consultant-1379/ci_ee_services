/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.database;

import java.sql.Connection;
import java.sql.DriverManager;

import com.ericsson.eniq.events.server.test.common.TestProperties;

/**
 * @author eemecoy
 *
 */
public class DatabaseConnectionHelper {

    /**
     * Create connection to the database specified in the jdbc.properties file
     *
     * @return
     * @throws Exception
     */
    public static Connection getDBConnection() throws Exception {
        final TestProperties testProperties = new TestProperties();
        final String jdbcURL = testProperties.getProperty("jdbc.url");
        final String userName = testProperties.getProperty("jdbc.username");
        final String password = testProperties.getProperty("jdbc.password");
        final String driverClassName = testProperties.getProperty("jdbc.driverClassName");
        System.out.println("Connecting to database " + jdbcURL + " with userName: " + userName + " and password: "
                + password + " and driver " + driverClassName);
        Class.forName(driverClassName);
        return DriverManager.getConnection(jdbcURL, userName, password);
    }

    public static Connection getDWHREPDBConnection() throws Exception {
        final TestProperties testProperties = new TestProperties();
        final String jdbcURL = testProperties.getProperty("dwhrep.jdbc.url");
        final String userName = testProperties.getProperty("dwhrep.jdbc.username");
        final String password = testProperties.getProperty("dwhrep.jdbc.password");
        final String driverClassName = testProperties.getProperty("dwhrep.jdbc.driverClassName");
        System.out.println("Connecting to database " + jdbcURL + " with userName: " + userName + " and password: "
                + password + " and driver " + driverClassName);
        Class.forName(driverClassName);
        return DriverManager.getConnection(jdbcURL, userName, password);
    }

}
