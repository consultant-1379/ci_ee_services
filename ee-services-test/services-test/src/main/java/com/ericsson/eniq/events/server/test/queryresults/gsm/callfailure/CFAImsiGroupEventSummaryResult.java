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
public class CFAImsiGroupEventSummaryResult implements QueryResult {

    private String categoryDescription;

    private int numberOfFailures;

    private int categoryId;

    private String impactedSubscribers;

    private String groupName;

    private double ratio;

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
        categoryId = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        ratio = object.getDouble(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        groupName = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    public int getNumberOfFailures() {
        return numberOfFailures;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public String getGroupName() {
        return groupName;
    }

    public double getRatio() {
        return ratio;
    }
}
