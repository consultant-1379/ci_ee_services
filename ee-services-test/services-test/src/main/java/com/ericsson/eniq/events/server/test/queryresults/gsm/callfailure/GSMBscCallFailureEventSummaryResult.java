/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

public class GSMBscCallFailureEventSummaryResult implements QueryResult {

    private String vendor;

    private String bscName;

    private int numberOfFailures;

    private int numberOfImpactedSubscribers;

    private int numberOfImpactedCells;

    private String categoryDescription;

    private int categoryId;

    private double failureRatio;

    private String hier3_ID;

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
        bscName = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        categoryDescription = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numberOfFailures = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        numberOfImpactedSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        numberOfImpactedCells = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        failureRatio = object.getDouble(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        categoryId = object.getInt(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        hier3_ID = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
    }

    public String getVendor() {
        return vendor;
    }

    public String getController() {
        return bscName;
    }

    public int getNumberOfFailures() {
        return numberOfFailures;
    }

    public int getNumberOfSubscribers() {
        return numberOfImpactedSubscribers;
    }

    public int getNumberOfImpactedCells() {
        return numberOfImpactedCells;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public double getFailureRatio() {
        return failureRatio;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getHier3_ID() {
        return hier3_ID;
    }
}
