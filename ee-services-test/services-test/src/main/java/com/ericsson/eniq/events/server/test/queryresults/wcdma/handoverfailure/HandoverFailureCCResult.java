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

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eflatib
 * @since 2012
 *
 */
public class HandoverFailureCCResult implements QueryResult {
    private String label;

    private String causeCodeDesc;

    private int failures;

    private int impactedSubscribersSource;

    private String impactedSubscribersTarget; // due to NULL in RNC CC analysis

    private int causeCode;

    private int eventID;

    private String categoryID;

    private String handoverType;

    private String groupName;

    private String state;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        label = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        failures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        impactedSubscribersSource = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        impactedSubscribersTarget = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        causeCode = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        eventID = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        categoryID = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        handoverType = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        groupName = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        state = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getLabel() {
        return label;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
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

    public int getCauseCode() {
        return causeCode;
    }

    public int getEventID() {
        return eventID;
    }

    public String getCategoryID() {
        return categoryID;
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
