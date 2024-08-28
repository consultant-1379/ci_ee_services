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
public class MssMscEventAnalysisSummaryResult implements QueryResult {

    private String msc;

    private String evntSrcId;

    private String eventId;

    private String eventIdDesc;

    private String errorCount;

    private String successCount;

    private String occurrences;

    private String successRatio;

    private String errorSubscriberCount;

    public String getMsc() {
        return msc;
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

    public String getEvntSrcId() {
        return evntSrcId;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        msc = jsonObject.getString("1");
        evntSrcId = jsonObject.getString("2");
        eventId = jsonObject.getString("3");
        eventIdDesc = jsonObject.getString("4");
        errorCount = jsonObject.getString("5");
        successCount = jsonObject.getString("6");
        occurrences = jsonObject.getString("7");
        successRatio = jsonObject.getString("8");
        errorSubscriberCount = jsonObject.getString("9");
    }
}
