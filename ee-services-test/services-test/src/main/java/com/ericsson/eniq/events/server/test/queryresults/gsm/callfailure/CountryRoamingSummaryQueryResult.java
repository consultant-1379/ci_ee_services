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
 * @author eatiaro
 * 2012
 *
 */
public class CountryRoamingSummaryQueryResult implements QueryResult {

    private int numErrors;

    private int numRoamers;

    private String country;

    private String mcc;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        country = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        numErrors = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numRoamers = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        mcc = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
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

}
