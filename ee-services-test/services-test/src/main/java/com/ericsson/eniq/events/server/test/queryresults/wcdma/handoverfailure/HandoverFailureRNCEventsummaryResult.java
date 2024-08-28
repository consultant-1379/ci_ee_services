/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class HandoverFailureRNCEventsummaryResult implements QueryResult {

    private int ratId;

    private String vendor;

    private String controller;

    private String handoverType;

    private int failures;

    private int impactedSubscribers;

    private int categoryID;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        ratId = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        categoryID = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        handoverType = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);

    }

    /**
     * @return the ratId
     */
    public int getRatId() {
        return ratId;
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
    public String getHandoverType() {
        return handoverType;
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

    public int getCategoryId() {
        return categoryID;
    }
}
