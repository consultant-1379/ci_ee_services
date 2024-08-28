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

/**
 * 
 * @author ezhelao
 *
 */
public class OperatorDrillRoamingAnalysisQueryResult implements QueryResult {

    private String categoryIDDesc;

    private int categoryID;

    private int numErrors;

    private Double failureRatio;

    private int impactedSubscribers;

    private String operatorName;

    private String mcc;

    private String mnc;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        operatorName = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        categoryIDDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numErrors = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        failureRatio = object.getDouble(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        categoryID = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        mcc = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        mnc = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
    }

    public String getCategoryIDDesc() {
        return categoryIDDesc;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getNumErrors() {
        return numErrors;
    }

    public Double getFailureRatio() {
        return failureRatio;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public String getMcc() {
        return mcc;
    }

    public String getMnc() {
        return mnc;
    }
}
