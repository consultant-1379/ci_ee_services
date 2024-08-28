/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.schema;

/**
 * Hold information about details on an SQL column (column name, column type, whether column can be null) 
 * 
 * @author EEMECOY
 *
 */
public class ColumnDetails {

    private final String columnName;

    private final String columnType;

    private final Nullable nullableInfo;

    public ColumnDetails(final String columnName, final String columnType, final Nullable nullableInfo) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.nullableInfo = nullableInfo;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public boolean canBeNull() {
        return nullableInfo == Nullable.CAN_BE_NULL;
    }
}
