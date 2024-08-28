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
 * @since 2011
 *
 */
public class LTEHFATerminalHostResultRaw implements QueryResult {

    private String tac;

    private String manufacture;
    
    private String model;

    private String handoverStage;
    
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
    
    public String getHandoverStage() {
        return handoverStage;
    }
    
    public int getFailures() {
        return failures;
    }

    public int getImapctedSubscribers() {
        return impactedSubscribers;
    }


    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
    	tac = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        manufacture = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        model = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        handoverStage = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failures = jsonObject.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    }

}
