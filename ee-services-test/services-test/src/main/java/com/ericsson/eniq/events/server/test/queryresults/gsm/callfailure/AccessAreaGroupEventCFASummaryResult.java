/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eramiye
 * @since 2012
 *
 */
public class AccessAreaGroupEventCFASummaryResult implements QueryResult {
    private String vendor;

    private String hier321_ID;

    private String categoryDescription;

    private int numberOfFailures;

    private int impactedSubscribers;

    private double failureRatio;

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
     * (com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        vendor = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        hier321_ID = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        categoryDescription = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numberOfFailures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failureRatio = object.getDouble(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    public String getVendor() {
        return vendor;
    }

    public String getHier321_ID() {
        return hier321_ID;
    }

    public int getNumberOfFailures() {
        return numberOfFailures;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public double getFailureRatio() {
        return failureRatio;
    }

}
