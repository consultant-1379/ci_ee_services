/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author emohasu
 * @since 2012
 *
 */

public class LTEHandoverFailureCauseCodeSummaryListResult implements QueryResult {

    private String eventypeDesc;

    private String causeCodeId;

    private String causeCodeDescp;

    public String getCauseCodeID() {
        return causeCodeId;
    }

    public String getEventTypeDesc() {
        return eventypeDesc;
    }

    public String getCauseCodeDesc() {
        return causeCodeDescp;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        eventypeDesc = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeId = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        causeCodeDescp = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    }
}
