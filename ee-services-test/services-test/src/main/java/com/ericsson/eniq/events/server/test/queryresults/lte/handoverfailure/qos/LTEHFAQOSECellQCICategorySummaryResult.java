/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure.qos;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;


public class LTEHFAQOSECellQCICategorySummaryResult implements QueryResult {

    private String hier321ID;

    private String qci;

    private String qciDesc;

    private int categoryID;

    private String category;

    private int failures;

    private int impactedSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        // TODO Auto-generated method stub
        hier321ID = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        qci = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        qciDesc = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        categoryID = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        category = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getHier321ID() {
        return hier321ID;
    }

    public String getQCI() {
        return qci;
    }

    public String getQCIDesc() {
        return qciDesc;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getCategory() {
        return category;
    }

    public int getFailures() {
        return failures;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }
}
