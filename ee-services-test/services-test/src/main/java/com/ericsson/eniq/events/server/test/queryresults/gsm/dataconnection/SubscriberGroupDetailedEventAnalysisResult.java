/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.dataconnection;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.*;

/**
 * @author eatiaro
 * @since 2011
 *
 */
public class SubscriberGroupDetailedEventAnalysisResult extends SubscriberDetailedEventAnalysisResult {

    private String imsi;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        super.parseJSONObject(object);
        imsi = object.getString(INDEX_OF_FORTY_SEVENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getIMSI() {
        return imsi;
    }
}
