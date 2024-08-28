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
public class MssAccessAreaEventAnalysisSummaryResult implements QueryResult {

    private String controller;

    private String vendor;

    private String accessArea;

    private String hier321Id;

    private String eventId;

    private String eventIdDesc;

    private String errorCount;

    private String successCount;

    private String occurrences;

    private String successRatio;

    private String errorSubscriberCount;

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

    public String getController() {
        return controller;
    }

    public String getVendor() {
        return vendor;
    }

    public String getAccessArea() {
        return accessArea;
    }

    public String getHier321Id() {
        return hier321Id;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        vendor = jsonObject.getString("1");
        controller = jsonObject.getString("2");
        accessArea = jsonObject.getString("3");
        hier321Id = jsonObject.getString("4");
        eventId = jsonObject.getString("5");
        eventIdDesc = jsonObject.getString("6");
        errorCount = jsonObject.getString("7");
        successCount = jsonObject.getString("8");
        occurrences = jsonObject.getString("9");
        successRatio = jsonObject.getString("10");
        errorSubscriberCount = jsonObject.getString("11");
    }
}
