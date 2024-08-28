/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author evijred
 * @since 2011
 *
 */

public class LTEHandoverFailureEnodeBCauseCodeSummaryResultRaw implements QueryResult {

    private String hier3Id;

    private String eventId;

    private String eventType;

    private String causeCodeId;

    private String causeCodeDescp;

    private String noOfErrors;

    private String impactedSubscribers;

    public String getHier3Id() {
        return hier3Id;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getCauseCodeId() {
        return causeCodeId;
    }

    public String getCauseCodeDescp() {
        return causeCodeDescp;
    }

    public String getNoOfErrors() {
        return noOfErrors;
    }

    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        hier3Id = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        causeCodeId = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        causeCodeDescp = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    }
}
