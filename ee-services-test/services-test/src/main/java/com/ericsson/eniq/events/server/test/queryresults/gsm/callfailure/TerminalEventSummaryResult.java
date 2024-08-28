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

/**
 * @author ewanggu
 * @since 2012
 *
 */
public class TerminalEventSummaryResult implements QueryResult {

    private String manufacturer;

    private String model;

    private String eventType;

    private int failures;

    private int impactedSubscribers;

    private int categoryId;

    private long tac;

    private double failureratio;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        manufacturer = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        model = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failureratio = object.getDouble(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        categoryId = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
    }

    public long getTac() {
        return tac;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getEventType() {
        return eventType;
    }

    public int getFailures() {
        return failures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getFailureRatio() {
        return failureratio;
    }
}
