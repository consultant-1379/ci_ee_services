/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * Test object for access area ranking.
 * @author etonayr
 * @since 2011
 *
 */
public class AccessAreaCallFailureRankingResult implements QueryResult {

    private int rank;

    private String vendor;

    private String accessArea;

    private int failures;

    private String controller;

    private String slope;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        slope = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);

    }

    public int getRank() {
        return rank;
    }

    public String getVendor() {
        return vendor;
    }

    public String getAccessArea() {
        return accessArea;
    }

    public int getFailures() {
        return failures;
    }

    public String getController() {
        return controller;
    }

    public String getSlope() {
        return slope;
    }
}