/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
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
public class SuccessCauseCodePieChartResult implements QueryResult, Comparable<SuccessCauseCodePieChartResult> {

    private int causeCodeId;

    private String causeCodeDesc;

    private int noOccurrences;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        causeCodeId = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        noOccurrences = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the causeCodeId
     */
    public int getCauseCodeId() {
        return causeCodeId;
    }

    /**
     * @return the causeCodeDesc
     */
    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    /**
     * @return the noOccurrences
     */
    public int getNoOccurrences() {
        return noOccurrences;
    }

    @Override
    public int compareTo(final SuccessCauseCodePieChartResult otherResult) {
        final int thisCauseCodeId = this.causeCodeId;
        final int otherCauseCodeId = otherResult.getCauseCodeId();
        return thisCauseCodeId - otherCauseCodeId;
    }

}
