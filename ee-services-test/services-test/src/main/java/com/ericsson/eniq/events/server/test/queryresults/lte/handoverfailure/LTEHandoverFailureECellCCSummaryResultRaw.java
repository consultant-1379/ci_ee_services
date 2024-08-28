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

import com.ericsson.eniq.events.server.json.*;
import com.ericsson.eniq.events.server.test.queryresults.*;

/**
 * @author EJAMVES
 * @since 2011
 *
 */

public class LTEHandoverFailureECellCCSummaryResultRaw implements QueryResult {

    private String eventId;

    private String eventType;

    private String hier321ID;

    private String noOfErrors;

    private String impactedSubscribers;

    private String causeCode;

    private String causeCodeDesc;

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getHier321_ID() {
        return hier321ID;
    }

    public String getNoOfErrors() {
        return noOfErrors;
    }

    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public void setImpactedSubscribers(final String impactedSubscribers) {
        this.impactedSubscribers = impactedSubscribers;
    }

    public String getCauseCode() {
        return causeCode;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        hier321ID = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventId = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventType = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        causeCode = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    }
}
