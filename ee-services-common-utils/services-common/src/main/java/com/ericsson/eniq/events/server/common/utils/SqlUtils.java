/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * SQL Utilities class. Not for instantiation.
 *
 * @author ealeerm
 * @since 07/2012
 */
public abstract class SqlUtils {

    private SqlUtils() {
    }

    public static void printStackTraceWithDetails(final SQLException e) {
        System.err.println("* SQLException with Error code: " + e.getErrorCode() + " and SQL State: " + e.getSQLState()
                + ":");
        e.printStackTrace();

        SQLException nextException = e.getNextException();
        int level = 0;
        while (nextException != null) {
            System.err.println((level != 0 ? "* Level " + level + ": " : "") + "SQLException.getNextException(): "
                    + nextException + ":");
            nextException.printStackTrace();
            if (nextException == e.getCause()) {
                break;
            }
            level++;

            if (nextException != nextException.getNextException()) {
                nextException = nextException.getNextException();
            } else {
                break;
            }
        }
    }

    public static String getSqlDetails(final SQLException e) {
        if (e == null) {
            return "exception=null";
        }
        return "Error msg: " + e.getMessage() + ", SQL Error code: " + e.getErrorCode() + ", SQL State: "
                + e.getSQLState()
                + (e.getNextException() != null ? (", NextException msg: " + e.getNextException().getMessage()) : "");
    }

    public static String getFullRecordDescription(final ResultSet rs) {
        if (rs == null) {
            return "ResultSet is null!";
        }

        ResultSetMetaData metaData;
        try {
            metaData = rs.getMetaData();
        } catch (final SQLException e) {
            return "{SQLException on getMetaData(): " + e.getMessage() + "}";
        }
        int columnCount;
        try {
            columnCount = metaData.getColumnCount();
        } catch (final SQLException e) {
            return "{SQLException on getColumnCount(): " + e.getMessage() + "}";
        }
        final StringBuilder buf = new StringBuilder(32 * columnCount);
        for (int i = 1; i <= columnCount; i++) {
            if (i > 1) {
                buf.append(',');
            }
            String colName;
            try {
                colName = metaData.getColumnName(i);
            } catch (final SQLException e) {
                colName = "{SQLException!}";
            }

            String columnTypeName;
            try {
                columnTypeName = metaData.getColumnTypeName(i);
            } catch (final SQLException e) {
                columnTypeName = "{SQLException!}";
            }
            Object value;
            try {
                value = rs.getObject(i);
            } catch (final SQLException e) {
                value = "{SQLException!}";
            }
            buf.append(colName).append('[').append(columnTypeName).append(']').append('=').append(value);
        }
        return buf.toString();
    }

}
