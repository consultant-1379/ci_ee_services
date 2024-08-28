/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author evijred
 * @since 2012
 *
 */
public class LTEHFATerminalEventSummaryResultRaw implements QueryResult {

    private String tac;

    private String manufacture;
    
    private String model;

    private String eventType;
    
    private String eventID;
    
    private int failures;
    
    private int impactedSubscribers;
      
    public String getTac() {
        return tac;
    }

    public String getManufacture() {
        return manufacture;
    }

    public String getModel() {
        return model;
    }
    
    public String getEventType() {
        return eventType;
    }
    
    public String getEventID() {
        return eventID;
    }
    
    public int getFailures() {
        return failures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }


    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
    	tac = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        manufacture = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        model = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        eventID = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failures = jsonObject.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

}
