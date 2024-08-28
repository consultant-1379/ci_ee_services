/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.BaseEventAnalysisSummaryResultSortableByEventID;

/**
 * @author eemecoy
 *
 */
public class RNCCallFailureEventSummaryResult extends BaseEventAnalysisSummaryResultSortableByEventID {

    private String vendor;

    private String rncFDN;

    private int numErrors;

    private String eventDesc;

    private int numSubscribers;

    private int eventId;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        vendor = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        rncFDN = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        eventDesc = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        numErrors = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        numSubscribers = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);

    }

    public String getVendor() {
        return vendor;
    }

    public String getRNCFDN() {
        return rncFDN;
    }

    public int getNumErrors() {
        return numErrors;
    }

    public String getEventDescription() {
        return eventDesc;
    }

    public int getNumImpactedSubscribers() {
        return numSubscribers;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.BaseEventAnalysisSummaryResultSortableByEventID#getEventId()
     */
    @Override
    public int getEventId() {
        return eventId;
    }

}
