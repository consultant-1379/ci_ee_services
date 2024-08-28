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
public class GSMCallFailureCellCauseCodeAnalysisResult implements QueryResult {

    private String causeCodeId;

    private String causeCodeDesc;

    private int numFailures;

    private int numImpactedSubs;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        causeCodeId = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numImpactedSubs = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the causeCodeId
     */
    public String getCauseCodeId() {
        return causeCodeId;
    }

    /**
     * @param causeCodeId the causeCodeId to set
     */
    public void setCauseCodeId(final String causeCodeId) {
        this.causeCodeId = causeCodeId;
    }

    /**
     * @return the causeCodeDesc
     */
    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    /**
     * @param causeCodeDesc the causeCodeDesc to set
     */
    public void setCauseCodeDesc(final String causeCodeDesc) {
        this.causeCodeDesc = causeCodeDesc;
    }

    /**
     * @return the numFailures
     */
    public int getNumFailures() {
        return numFailures;
    }

    /**
     * @param numFailures the numFailures to set
     */
    public void setNumFailures(final int numFailures) {
        this.numFailures = numFailures;
    }

    /**
     * @return the numImpactedSubs
     */
    public int getNumImpactedSubs() {
        return numImpactedSubs;
    }

    /**
     * @param numImpactedSubs the numImpactedSubs to set
     */
    public void setNumImpactedSubs(final int numImpactedSubs) {
        this.numImpactedSubs = numImpactedSubs;
    }

}
