package com.ericsson.eniq.events.server.test.sql;

import java.sql.*;

/**
 * Execute given SQL against given DB connection
 * 
 * @author EEMECOY
 *
 */
public class SQLExecutor {

    private final Connection connection;

    private Statement statement;

    public SQLExecutor(final Connection connection) {
        this.connection = connection;
    }

    public void executeUpdate(final String query) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        System.out.println(query);
        statement.executeUpdate(query);
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
    }

    public static void closeSQLExector(final SQLExecutor sqlExecutor) throws SQLException {
        if (sqlExecutor != null) {
            sqlExecutor.close();
        }
    }

}
