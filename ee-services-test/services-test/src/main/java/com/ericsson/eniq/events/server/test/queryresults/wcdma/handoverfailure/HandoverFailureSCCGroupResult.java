/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eromsza
 * @since 2012
 *
 */
public class HandoverFailureSCCGroupResult implements QueryResult {
    private String label;

    private String subCauseCodeDesc;

    private int failures;

    private int impactedSubscribersSource;

    private String impactedSubscribersTarget;

    private int causeCodeId;

    private int subCauseCodeId;

    private int eventId;

    private int categoryId;

    private String handoverType;

    private String groupName;

    private String state;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        label = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        subCauseCodeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribersSource = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribersTarget = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        causeCodeId = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        subCauseCodeId = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        eventId = object.getInt(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        categoryId = object.getInt(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        handoverType = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        groupName = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        state = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
    }

    public String getLabel() {
        return label;
    }

    public String getSubCauseCodeDesc() {
        return subCauseCodeDesc;
    }

    public int getFailures() {
        return failures;
    }

    public int getImpactedSubscribersSource() {
        return impactedSubscribersSource;
    }

    public String getImpactedSubscribersTarget() {
        return impactedSubscribersTarget;
    }

    public int getCauseCodeId() {
        return causeCodeId;
    }

    public int getSubCauseCodeId() {
        return subCauseCodeId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getHandoverType() {
        return handoverType;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getState() {
        return state;
    }
}
