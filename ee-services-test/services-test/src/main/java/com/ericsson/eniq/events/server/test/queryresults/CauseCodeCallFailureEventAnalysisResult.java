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
public class CauseCodeCallFailureEventAnalysisResult implements QueryResult {
    
    
    private String description;
    private int causeValue;
    private int occurences;
    private int impactedSubscribers;
    

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(JSONObject object) throws JSONException {
        description = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        causeValue= object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        occurences =  object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers =  object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }


    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    /**
     * @return the causeValue
     */
    public int getCauseValue() {
        return causeValue;
    }


    /**
     * @return the occurences
     */
    public int getOccurences() {
        return occurences;
    }


    /**
     * @return the impactedSubscribers
     */
    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
    
    

}
