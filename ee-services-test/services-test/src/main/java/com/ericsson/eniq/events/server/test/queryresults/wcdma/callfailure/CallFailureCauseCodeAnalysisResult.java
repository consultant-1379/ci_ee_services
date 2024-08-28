/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author epesmit
 * @since 2011
 *
 */
public class CallFailureCauseCodeAnalysisResult implements QueryResult {

    private String causeCodeId;

    private String causeCodeDescp;

    private int numFailures;

    private int subscribers;

    public String getCauseCodeId() {
        return causeCodeId;
    }

    public String getCauseCodeDescp() {
        return causeCodeDescp;
    }

    public int getNumFailures() {
        return numFailures;
    }

    public int getSubscribers() {
        return subscribers;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        causeCodeId = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDescp = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        numFailures = jsonObject.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        subscribers = jsonObject.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);

    }
}
