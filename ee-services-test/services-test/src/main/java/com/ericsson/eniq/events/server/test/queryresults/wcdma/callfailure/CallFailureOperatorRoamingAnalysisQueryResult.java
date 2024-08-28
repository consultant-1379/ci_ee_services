/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 *
 * @author eemecoy
 *
 */
public class CallFailureOperatorRoamingAnalysisQueryResult implements QueryResult {

    private String operator;

    private int numErrors;

    private int numRoamers;

    private String country;

    private String mcc;

    private String mnc;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        operator = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        numErrors = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numRoamers = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        country = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        mcc = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        mnc = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    public String getOperator() {
        return operator;
    }

    public int getNumErrors() {
        return numErrors;
    }

    public int getNumRoamers() {
        return numRoamers;
    }

    public String getCountry() {
        return country;
    }

    public String getMCC() {
        return mcc;
    }

    public String getMNC() {
        return mnc;
    }

}
