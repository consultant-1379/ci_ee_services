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
 * @author etonayr
 * @since 2011
 *
 */
public class AccessAreaCallFailureEventSummaryResult implements QueryResult {

    private String vendor;

    private String controller;

    private String eventType;

    private int failures;

    private int impactedSubscribers;

    private int eventId;

    private String cellId;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        vendor = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        cellId = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        eventId = object.getInt(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @return the failures
     */
    public int getFailures() {
        return failures;
    }

    /**
     * @return the impactedSubscribers
     */
    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public int getEventId() {
        return eventId;
    }

    public String getCellId() {
        return cellId;
    }

}
