/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ericsson.eniq.events.server.common.utils.StringUtilities;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.ColumnTypes;
import com.ericsson.eniq.events.server.test.schema.MssColumnTypes;
import com.ericsson.eniq.events.server.test.schema.Nullable;

/**
 * Execute SQL commands.
 * <p/>
 * Note: not thread safe.
 *
 * @author eemecoy
 */
public class SQLCommand {

    private final Connection connection;

    private static StringBuilder buf;

    public SQLCommand(final Connection connection) {
        this.connection = connection;
        if (buf == null) {
            buf = new StringBuilder(512);
        }
    }

    public void createTable(final String table, final String schema) throws SQLException {
        final SQLExecutor sqlExecutor = new SQLExecutor(connection);
        try {
            clearBuffer();
            buf.append("create local temporary table ");
            buf.append(table).append('(');
            buf.append(schema);
            buf.append(')');
            sqlExecutor.executeUpdate(buf.toString());
        } finally {
            clearBuffer();
            sqlExecutor.close();
        }
    }

    /**
     * Create temporary table with nullable information provided
     * This method doesn't expect any type information for the columns - it determines this using the
     * ColumnTypes class
     *
     * @param table name of temporary table
     * @param tableColumnsWithNullableDetails
     *              map of column names, and whether column can be null or not
     */
    public void createTemporaryTableWithNullableInfo(final String table,
            final Map<String, Nullable> tableColumnsWithNullableDetails) throws SQLException {
        final List<ColumnDetails> columnDetails = addColumnTypeInfo(tableColumnsWithNullableDetails);
        createTemporaryTable(table, columnDetails);
    }

    public void createTemporaryTableWithColumns(final String table, final List<ColumnDetails> columns)
            throws SQLException {
        createTemporaryTable(table, columns);
    }

    private List<ColumnDetails> addColumnTypeInfo(final Map<String, Nullable> tableColumnsWithNullableDetails) {
        final List<ColumnDetails> columnDetails = new ArrayList<ColumnDetails>();
        for (final String columnName : tableColumnsWithNullableDetails.keySet()) {
            columnDetails.add(new ColumnDetails(columnName, ColumnTypes.getColumnType(columnName),
                    tableColumnsWithNullableDetails.get(columnName)));
        }
        return columnDetails;
    }

    /**
     * Create temporary table with columns and types provided
     * Each column thats created won't be nullable
     *
     * @param table   name of temporary table
     * @param columns map of column names and column types
     */
    public void createTemporaryTable(final String table, final Map<String, String> columns) throws SQLException {
        final List<ColumnDetails> columnsWithTypesAndNullableInfo = addDefaultNullableInfo(columns);
        createTemporaryTable(table, columnsWithTypesAndNullableInfo);
    }

    /**
     * Create temporary table with columns provided
     * Each columns thats created won't be nullable
     * This method doesn't expect any type information for the columns - it determines this using the
     * ColumnTypes class
     *
     * @param table   name of temporary table
     * @param columns list of the column names
     */
    public void createTemporaryTable(final String table, final Collection<String> columns) throws Exception {
        final Map<String, String> columnsWithTypes = getColumnTypes(columns);
        final List<ColumnDetails> columnsWithTypesAndNullableInfo = addDefaultNullableInfo(columnsWithTypes);
        createTemporaryTable(table, columnsWithTypesAndNullableInfo);
    }

    private List<ColumnDetails> addDefaultNullableInfo(final Map<String, String> columnsWithTypes) {
        final List<ColumnDetails> columnDetails = new ArrayList<ColumnDetails>();
        for (final String columnName : columnsWithTypes.keySet()) {
            columnDetails.add(new ColumnDetails(columnName, columnsWithTypes.get(columnName), Nullable.CANNOT_BE_NULL));
        }
        return columnDetails;
    }

    private Map<String, String> getColumnTypes(final Collection<String> columns) {
        final Map<String, String> columnTypes = new HashMap<String, String>();
        for (final String column : columns) {
            columnTypes.put(column, ColumnTypes.getColumnType(column));
        }
        return columnTypes;
    }

    public void createTemporaryTableMss(final String table, final Collection<String> columns) throws Exception {
        final Map<String, String> columnsWithTypes = getColumnTypesMss(columns);
        final List<ColumnDetails> columnsWithTypesAndNullableInfo = addDefaultNullableInfo(columnsWithTypes);
        createTemporaryTable(table, columnsWithTypesAndNullableInfo);
    }

    private Map<String, String> getColumnTypesMss(final Collection<String> columns) {
        final Map<String, String> columnTypes = new HashMap<String, String>();
        for (final String column : columns) {
            columnTypes.put(column, MssColumnTypes.getColumnType(column));
        }
        return columnTypes;
    }

    private void createTemporaryTable(final String tableName, final List<ColumnDetails> columns) throws SQLException {
        SQLExecutor sqlExecutor = null;
        try {
            sqlExecutor = new SQLExecutor(connection);
            final Iterator<ColumnDetails> iterator = columns.iterator();
            clearBuffer();
            buf.append("create local temporary table ").append(tableName).append('(');
            while (iterator.hasNext()) {
                final ColumnDetails columnDetails = iterator.next();
                buf.append(columnDetails.getColumnName());
                buf.append(' ');
                buf.append(columnDetails.getColumnType());
                if (columnDetails.canBeNull()) {
                    buf.append(' ');
                    buf.append("NULL");
                }
                if (iterator.hasNext()) {
                    buf.append(',');
                }
            }

            sqlExecutor.executeUpdate(buf.append(')').toString());

        } finally {
            clearBuffer();
            if (sqlExecutor != null) {
                sqlExecutor.close();
            }
        }
    }

    public void insertRow(final String table, final Map<String, Object> values) throws SQLException {
        final SQLExecutor sqlExecutor = new SQLExecutor(connection);
        clearBuffer();
        try {
            buf.append("insert into ");
            buf.append(table);
            buf.append(' ').append('(');

            for (final String column : values.keySet()) {
                buf.append(column);
                buf.append(',');
            }
            buf.deleteCharAt(buf.length() - 1);
            buf.append(") values(");

            for (final String column : values.keySet()) {
                final Object value = values.get(column);
                if (value instanceof Number || "NULL".equals(value)) {
                    buf.append(value);
                } else {
                    buf.append('\'');
                    buf.append(value);
                    buf.append('\'');
                }
                buf.append(',');
            }

            buf.deleteCharAt(buf.length() - 1);
            buf.append(')');

            sqlExecutor.executeUpdate(buf.toString());
        } finally {
            clearBuffer();
            sqlExecutor.close();
        }
    }

    public void createTemporaryFromRealTable(final String realTableName, final String tempTableName,
            final String... columnNames) throws SQLException {
        clearBuffer();
        try {
            buf.append("SELECT ");
            buf.append(StringUtilities.convertStringArrayToCommaSeparatedString(columnNames));
            buf.append(" INTO ");

            buf.append(tempTableName);
            buf.append(" FROM ");
            buf.append(realTableName);
            buf.append(" WHERE 1=2;"); //Trick:Want to get just the table structure(get no rows).

            final SQLExecutor sqlExecutor = new SQLExecutor(connection);
            sqlExecutor.executeUpdate(buf.toString());
        } finally {
            clearBuffer();
        }
    }

    public void insertRowIntoTemporaryTable(final String temporaryTableName, final String... columnValues)
            throws SQLException {
        clearBuffer();
        try {
            buf.append("INSERT INTO ");
            buf.append(temporaryTableName);

            buf.append(" VALUES (");
            for (int i = 0; i < columnValues.length; i++) {
                if (i != 0) {
                    buf.append(',');
                }
                if (isNumeric(columnValues[i])) {
                    buf.append(columnValues[i]);
                } else {
                    buf.append('\'');
                    buf.append(columnValues[i]);
                    buf.append('\'');
                }
            }
            buf.append(')').append(';');

            final SQLExecutor sqlExecutor = new SQLExecutor(connection);
            sqlExecutor.executeUpdate(buf.toString());
        } finally {
            clearBuffer();
        }
    }

    private boolean isNumeric(final String data) {
        try {
            Long.parseLong(data);
        } catch (final NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void clearBuffer() {
        buf.setLength(0);
    }
}