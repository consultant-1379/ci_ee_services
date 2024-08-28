/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.dataconnection;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ejoegaf
 * @since 2012
 *
 */
public class SubCauseCodePieChartResult implements QueryResult {

    private String subCauseCodeId;

    private String subCauseCodeDesc;

    private int noOccurrences;

    private int noImpactedSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        subCauseCodeId = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        subCauseCodeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        noOccurrences = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        noImpactedSubscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the subCauseCodeId
     */
    public String getSubCauseCodeId() {
        return subCauseCodeId;
    }

    /**
     * @return the subCauseCodeDesc
     */
    public String getSubCauseCodeDesc() {
        return subCauseCodeDesc;
    }

    /**
     * @return the noOccurrences
     */
    public int getNoOccurrences() {
        return noOccurrences;
    }

    /**
     * @return the noImpactedSubscribers
     */
    public int getNoImpactedSubscribers() {
        return noImpactedSubscribers;
    }
}
