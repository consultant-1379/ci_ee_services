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
 * @author ewanggu
 * @since 2011
 *
 */
public class GSMCallFailureSubCauseCodeEventSummaryResult implements QueryResult {

    private String extendedCauseCodeDescription;

    private int extendedCauseCodeId;

    private int failures;

    private String causeCodeDescription;

    private int causeCode;

    private int impactedSubscriber;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        extendedCauseCodeDescription = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        extendedCauseCodeId = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscriber = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        causeCodeDescription = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        causeCode = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
    }

    public String getExtendedCauseCodeDescription() {
        return extendedCauseCodeDescription;
    }

    public int getExtendedCauseCodeId() {
        return extendedCauseCodeId;
    }

    public int getFailures() {
        return failures;
    }

    public String getCauseCodeDescription() {
        return causeCodeDescription;
    }

    public int getCauseCode() {
        return causeCode;
    }

    public int getImpactedSubscriber() {
        return impactedSubscriber;
    }
}
