/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ebrowpa
 * @since 2011
 *
 */
public class HandoverFailureCellGroupCCListResult implements QueryResult {
    private String causeValueLabel;

    private String causeCodeDesc;

    private String groupId;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        causeValueLabel = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        groupId = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    }

    public String getCauseValueLabel() {
        return causeValueLabel;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    public String getGroupId() {
        return groupId;
    }
}
