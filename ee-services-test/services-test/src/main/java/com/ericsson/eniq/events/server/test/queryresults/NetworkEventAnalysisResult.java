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
 * @author eflatib
 * @since 2011
 *
 */
public class NetworkEventAnalysisResult implements QueryResult {

    private String ranVendor;

    private String controller;

    private String failureType;
    
    private int failures;

    private int impactedSubscribers;

    private double failureRate;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        ranVendor = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        failureType = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failureRate = object.getDouble(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
    }

	public String getRanVendor() {
		return ranVendor;
	}

	public String getController() {
		return controller;
	}

	public String getFailureType() {
		return failureType;
	}

	public int getFailures() {
		return failures;
	}

	public int getImpactedSubscribers() {
		return impactedSubscribers;
	}
    
	
    public double getFailureRate() {
		return failureRate;
	}
    
}
