/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.validator;

import static org.junit.Assert.*;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.util.JSONTestUtils;

/**
 * Validate a JSON result set against the expected schema as defined in the UIMetaData.json
 * Given a grid name from the meta data, this class will read the definition for that grid (ie the number
 * and types of columns), and validate the received JSON from the query against this grid definition
 * 
 * @author eemecoy
 *
 */
public class QueryResultValidator {

    public void validateAgainstGridDefinition(final String json, final String gridName) throws Exception {
        /*  final GridDefinition gridDefinition = new UIMetaDataReader().getGrid(gridName);
          final JSONObject rowInJSON = getFirstRowInJSON(json);
          System.out.println("Verifying against grid " + gridName);
          assertThat("Number of columns defined in grid " + gridName
                  + " does not match number of columns returned from query",
                  gridDefinition.getNumberColumnsDefinedInGrid(), is(getNumberColumnsReturnedInJSON(rowInJSON)));
          final List<GridColumn> gridColumns = gridDefinition.getGridColumns();
          for (int i = 1; i <= rowInJSON.length(); i++) {
              final Object value = rowInJSON.get(Integer.toString(i));
              checkForIntegerValues(value, gridColumns.get(i - 1), gridDefinition.getGridName());
          }*/

    }

    private JSONObject getFirstRowInJSON(final String json) throws JSONException {
        final JSONArray jsonResultObject = JSONTestUtils.getDataElement(json);
        return (JSONObject) jsonResultObject.get(0);
    }

    private void checkForIntegerValues(final Object value, final GridColumn gridColumn, final String gridName) {
        if (gridColumn.isIntegerDataType()) {
            checkThatValueInResultIsAnInt(value, gridName, gridColumn.getColumnName());
        }
    }

    private void checkThatValueInResultIsAnInt(final Object value, final String grid, final String columnName) {
        try {
            Integer.parseInt((String) value);
        } catch (final NumberFormatException e) {
            fail("In grid " + grid + " for column " + columnName + ", the value should have been an integer, but was: "
                    + value);
        }

    }

    private int getNumberColumnsReturnedInJSON(final JSONObject rowInJSON) {
        return rowInJSON.length();
    }
}
