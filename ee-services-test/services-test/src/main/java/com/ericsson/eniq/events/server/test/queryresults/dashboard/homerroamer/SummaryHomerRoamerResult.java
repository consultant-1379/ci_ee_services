/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.dashboard.homerroamer;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.BaseEventAnalysisSummaryResultSortableByEventID;

/**
 * @author eeikbe
 * @since 2011
 *
 */
public class SummaryHomerRoamerResult extends BaseEventAnalysisSummaryResultSortableByEventID {

    private String rank;

    private String country;

    private String roamers;

    private final int eventId = 0;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        country = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        roamers = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    }

    public String getRank() {
        return rank;
    }

    public String getCountry() {
        return country;
    }

    public String getRoamers() {
        return roamers;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.BaseEventAnalysisSummaryResultSortableByEventID#getEventId()
     */
    @Override
    public int getEventId() {
        return eventId;
    }

}
