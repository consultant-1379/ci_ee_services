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
public class GSMCallFailureCauseCodeResult implements QueryResult {

    private String causeCodeId;

    private String causeCodeDesc;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        causeCodeId = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the causeCodeId
     */
    public String getCauseCodeId() {
        return causeCodeId;
    }

    /**
     * @return the causeCodeDesc
     */
    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }
}
