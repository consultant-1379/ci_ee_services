/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.validator;

/**
 * Represents a grid column in the UIMetaData.json file
 * @author eemecoy
 *
 */
public class GridColumn {

    private final String dataType;

    private final String columnName;

    /**
     * @param dataType
     */
    public GridColumn(final String columnName, final String dataType) {
        this.columnName = columnName;
        this.dataType = dataType;
    }

    public boolean isIntegerDataType() {
        return dataType.equals("int");
    }

    public String getColumnName() {
        return columnName;
    }

}
