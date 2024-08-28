/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eemecoy
 *
 */
@Ignore
public class EventAnalysisSummaryResult implements QueryResult {

    private String manufacturer;

    private String eventId;

    private String eventIdDesc;

    private String ratDesc;

    private String errorCount;

    private String successCount;

    private String occurrences;

    private String successRatio;

    private String errorSubscriberCount;

    public String getManufacturer() {
        return manufacturer;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventIdDesc() {
        return eventIdDesc;
    }

    public String getRatDesc() {
        return ratDesc;
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
        manufacturer = jsonObject.getString("1");
        eventId = jsonObject.getString("2");
        eventIdDesc = jsonObject.getString("3");
        ratDesc = jsonObject.getString("4");
        errorCount = jsonObject.getString("5");
        successCount = jsonObject.getString("6");
        occurrences = jsonObject.getString("7");
        successRatio = jsonObject.getString("8");
        errorSubscriberCount = jsonObject.getString("9");
    }
}
