/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eemecoy
 *
 */
public class QOSStatisticsSummaryResult implements QueryResult {

    private String description;

    private int noSuccesses;

    private int noErrors;

    private int qciId;

    private int impactedSubscribers;

    public String getDescription() {
        return description;
    }

    public int getNoErrors() {
        return noErrors;
    }

    public int getNoSuccesses() {
        return noSuccesses;
    }

    public int getQCIId() {
        return qciId;
    }

    public int getImpactedSubscribers() {
        return impactedSubscribers;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        qciId = jsonObject.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        description = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        noErrors = jsonObject.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        noSuccesses = jsonObject.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
    }

}
