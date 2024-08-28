/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.populator;

import java.util.List;
import java.util.Map;

/**
 * Schema for the look up tables files
 * @author eemecoy
 *
 */
public class LookupTableDetails {

    private final List<String> tableColumns;

    private final List<Map<String, Object>> tableValues;

    public LookupTableDetails(final List<String> tableColumns, final List<Map<String, Object>> tableValues) {

        this.tableColumns = tableColumns;
        this.tableValues = tableValues;
    }

    public List<String> getColumns() {
        return tableColumns;
    }

    public List<Map<String, Object>> getValues() {
        return tableValues;
    }

}
