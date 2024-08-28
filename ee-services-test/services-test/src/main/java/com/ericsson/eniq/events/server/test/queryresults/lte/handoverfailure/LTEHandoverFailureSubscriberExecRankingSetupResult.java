/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ejamves
 * @since 2011
 *
 */
public class LTEHandoverFailureSubscriberExecRankingSetupResult implements QueryResult {

    private String imsi;

    /* (non-Javadoc)
    * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
    */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        imsi = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);

    }

    public String getImsi() {
        return imsi;
    }

}
