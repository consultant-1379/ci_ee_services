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
public class TerminalEventAnalysisResult implements QueryResult {

    private String manufacturer;

    private String model;

    private String eventType;
    
    private int failures;

    private int impactedSubscribers;

    


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
  
}
