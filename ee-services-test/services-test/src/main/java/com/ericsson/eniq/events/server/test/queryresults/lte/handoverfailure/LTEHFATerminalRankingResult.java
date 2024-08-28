/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ejamves
 * @since 2012
 *
 */
public class LTEHFATerminalRankingResult implements QueryResult {

    private int rank;

    private String categoryID2;

    private String manufacturer;

    private String model;

    private String tac;

    private int failures;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categoryID2 = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        manufacturer = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        model = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        tac = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    public int getRank() {
        return rank;
    }

    public String getCategoryID2() {
        return categoryID2;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getTac() {
        return tac;
    }

    public int getFailures() {
        return failures;
    }
}
