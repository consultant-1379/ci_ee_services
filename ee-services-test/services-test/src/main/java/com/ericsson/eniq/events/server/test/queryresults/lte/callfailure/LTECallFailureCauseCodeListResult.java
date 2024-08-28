/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author echchik
 * @since 2011
 *
 */

public class LTECallFailureCauseCodeListResult implements QueryResult {

    private String causeCodeId;

    private String causeCodeDescp;

    public String getCauseCodeId() {
        return causeCodeId;
    }

    public String getCauseCodeDescp() {
        return causeCodeDescp;
    }

    @Override
    public void parseJSONObject(JSONObject jsonObject) throws JSONException {
        causeCodeId = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDescp = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    }
}
