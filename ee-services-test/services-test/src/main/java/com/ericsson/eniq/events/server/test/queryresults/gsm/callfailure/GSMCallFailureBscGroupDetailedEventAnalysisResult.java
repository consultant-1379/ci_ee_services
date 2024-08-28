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
public class GSMCallFailureBscGroupDetailedEventAnalysisResult extends GSMCallFailureBscDetailedEventAnalysisResult
        implements QueryResult {

    private String controller;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        super.parseJSONObject(object);
        controller = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getController() {
        return controller;
    }

}
