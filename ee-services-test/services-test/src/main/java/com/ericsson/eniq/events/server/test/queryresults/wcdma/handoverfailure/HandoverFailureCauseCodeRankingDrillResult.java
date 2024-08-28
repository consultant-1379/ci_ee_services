/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ehaoswa
 * @since 2011
 *
 */
public class HandoverFailureCauseCodeRankingDrillResult implements QueryResult {

    private String subCauseCode;

    private String subCauseCodeId;

    private int impacted_subscribers;

    private int failures;

    public String getSubCauseCodeId() {
        return subCauseCodeId;
    }

    public String getSubCauseCode() {
        return subCauseCode;
    }

    public int getImpactedSubscribers() {
        return impacted_subscribers;
    }

    public int getFailures() {
        return failures;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        subCauseCodeId = object.getString("1");
        subCauseCode = object.getString("2");
        failures = object.getInt("3");
        impacted_subscribers = object.getInt("4");
    }
}
