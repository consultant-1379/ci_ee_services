/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.*;
import com.ericsson.eniq.events.server.test.queryresults.*;

/**
 * @author eatiaro
 * @since 2011
 *
 */
public class GSMCallFailureAccessAreaGroupDetailedEventAnalysisResult extends
        GSMCallFailureAccessAreaDetailedEventAnalysisResult implements QueryResult {

    private String accessArea;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        super.parseJSONObject(object);
        accessArea = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getAccessArea() {
        return accessArea;
    }
}
