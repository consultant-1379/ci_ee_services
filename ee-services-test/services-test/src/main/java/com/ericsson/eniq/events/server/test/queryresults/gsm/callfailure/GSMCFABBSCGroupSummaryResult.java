/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.*;
import com.ericsson.eniq.events.server.test.queryresults.*;

public class GSMCFABBSCGroupSummaryResult implements QueryResult {

    private int numberOfFailures;

    private int numberOfImpactedSubscribers;

    private String categoryDescription;

    private double ratio;

    private int categoryId;


    /*
     * (non-Javadoc)
     * 
     * @see
     * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
     * (com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        categoryDescription = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        numberOfFailures = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numberOfImpactedSubscribers = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        ratio = object.getDouble(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        categoryId = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }
    
    public int getNumberOfFailures() {
        return numberOfFailures;
    }

    public int getNumberOfImpactedSubscribers() {
        return numberOfImpactedSubscribers;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getRatio() {
        return ratio;
    }
}
