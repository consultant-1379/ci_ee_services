/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echchik
 * @since 2011
 *
 */
public class LTECallFailureTrackingAreaGroupCategorySummaryResult implements QueryResult {

    private String trackingAreaGroupName;

    private String categoryType;

    private String categoryID;

    private int failures;

    private int impactedSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        trackingAreaGroupName = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categoryID = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        categoryType = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }

    public String getTrackingAreaGroupName() {
        return trackingAreaGroupName;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public int getFailures() {
        return failures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
}
