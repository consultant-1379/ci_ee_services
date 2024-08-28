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
 * @author estepdu
 * @since 2011
 *
 */

public class LTECallFailureECellEventSummaryResultRaw implements QueryResult {

    private String eventId;

    private String eventType;

    private String vendor;

    private String hier321Id;

    private String eCell;

    private String noOfFailures;

    private String impactedSubscribers;

    private String eNodeB;

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getVendor() {
        return vendor;
    }

    public String getHier321Id() {
        return hier321Id;
    }

    public String getECell() {
        return eCell;
    }

    public String getNumFailures() {
        return noOfFailures;
    }

    public String getNumImpactedSubscribers() {
        return impactedSubscribers;
    }

    public String geteNodeB() {
        return eNodeB;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        hier321Id = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        vendor = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eNodeB = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        eCell = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        noOfFailures = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
    }

}
