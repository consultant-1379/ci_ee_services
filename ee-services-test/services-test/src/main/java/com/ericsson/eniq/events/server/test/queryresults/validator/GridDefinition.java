/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.validator;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * Represents a grid definition in the UIMetaData.json file
 * 
 * @author eemecoy
 *
 */
public class GridDefinition {

    private final List<GridColumn> gridColumns = new ArrayList<GridColumn>();

    private final String gridName;

    /**
     * @param grid
     * @throws JSONException 
     */
    public GridDefinition(final JSONObject grid) throws JSONException {
        gridName = (String) grid.get("id");
        final JSONArray columns = (JSONArray) grid.get("columns");
        final int numberColumns = columns.length();
        for (int i = 0; i < numberColumns; i++) {
            final JSONObject column = (JSONObject) columns.get(i);
            final String columnName = (String) column.get("header");
            final String dataType = (String) column.get("datatype");
            gridColumns.add(new GridColumn(columnName, dataType));
        }
    }

    public List<GridColumn> getGridColumns() {
        return gridColumns;
    }

    public int getNumberColumnsDefinedInGrid() {
        return gridColumns.size();
    }

    public String getGridName() {
        return gridName;
    }

}
