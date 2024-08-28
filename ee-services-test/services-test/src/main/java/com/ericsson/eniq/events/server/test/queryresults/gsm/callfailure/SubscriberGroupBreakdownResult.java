/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

public class SubscriberGroupBreakdownResult implements QueryResult {

    private String imsi;

    private String categoryDescription;

    private int numberOfFailures;

    private double ratio;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
     * (com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        imsi = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categoryDescription = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numberOfFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        ratio = object.getDouble(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }

    public String getImsi() {
        return imsi;
    }

    public int getNumberOfFailures() {
        return numberOfFailures;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public double getRatio() {
        return ratio;
    }
}
