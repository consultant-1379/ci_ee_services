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
 * @author ebrowpa
 * @since 2011
 *
 */
public class NetworkEventAnalysisAccessAreaResult implements QueryResult {

    private String vendor;

    private String controller;
    
    private String accessArea;

    private String failureType;

    private int failures;

    private int impactedSubscribers;

    private double failureRate;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        vendor = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        failureType = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        failureRate = object.getDouble(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
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
     * @return the controller
     */
    public String getAccessArea() {
        return accessArea;
    }
    
    /**
     * @return the eventType
     */
    public String getFailureType() {
        return failureType;
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

    public double getFailureRate() {
		return failureRate;
	}

}
