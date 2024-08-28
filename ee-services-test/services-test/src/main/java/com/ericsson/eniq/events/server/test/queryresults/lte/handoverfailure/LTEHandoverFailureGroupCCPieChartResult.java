/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.*;
import com.ericsson.eniq.events.server.test.queryresults.*;

/**
 * @author ejamves
 * @since 2011
 *
 */

public class LTEHandoverFailureGroupCCPieChartResult implements QueryResult {

    private String causeCodeId;

    private String causeCodeDesc;

    private String impactedSubscribers;

    private String noOfErrors;

    private String groupName;

    public String getNoOfErrors() {
        return noOfErrors;
    }

    public String getImpactedSubscribers() {
        return impactedSubscribers;
    }

    public String getCauseCodeId() {
        return causeCodeId;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        causeCodeId = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        noOfErrors = jsonObject.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribers = jsonObject.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }
}
