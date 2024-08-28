/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author echchik
 *
 */
public class MssTacAndAllGroupsEventAnalysisSummaryResult implements QueryResult {

    private String tacOrGroupName;

    private String eventId;

    private String eventIdDesc;

    private String errorCount;

    private String successCount;

    private String occurrences;

    private String successRatio;

    private String errorSubscriberCount;

    public String getTacOrGroupName() {
        return tacOrGroupName;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventIdDesc() {
        return eventIdDesc;
    }

    public String getErrorCount() {
        return errorCount;
    }

    public String getSuccessCount() {
        return successCount;
    }

    public String getOccurrences() {
        return occurrences;
    }

    public String getSuccessRatio() {
        return successRatio;
    }

    public String getErrorSubscriberCount() {
        return errorSubscriberCount;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        tacOrGroupName = jsonObject.getString("1");
        eventId = jsonObject.getString("2");
        eventIdDesc = jsonObject.getString("3");
        errorCount = jsonObject.getString("4");
        successCount = jsonObject.getString("5");
        occurrences = jsonObject.getString("6");
        successRatio = jsonObject.getString("7");
        errorSubscriberCount = jsonObject.getString("8");
    }
}
