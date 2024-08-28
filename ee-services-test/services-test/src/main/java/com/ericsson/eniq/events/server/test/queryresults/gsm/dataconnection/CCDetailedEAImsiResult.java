/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.dataconnection;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FORTY_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FORTY_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TWENTY_EIGHTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TWENTY_NINHTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eramiye
 * @since 2012
 *
 */
public class CCDetailedEAImsiResult implements QueryResult {

    private String eventTime;

    private int tac;

    private String terminalMake;

    private String terminalModel;

    private String eventType;

    private String controller;

    private String accessArea;

    private String tbfReleaseCause;

    private String tbfDataVolume;

    private String tbfDuration;

    private String gprsMeasReportRxqualDlDesc;

    private String gprsMeasReportSignVarDesc;

    private String gprsMeasReportMeanBepDesc;

    private String gprsMeasReportCvBepDesc;

    private String gprsMeasReportCvalueDesc;

    private String channelRelatedReleaseCauseGroupDesc;
    
    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        tbfReleaseCause = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        tbfDataVolume = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        tbfDuration = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        channelRelatedReleaseCauseGroupDesc = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        gprsMeasReportRxqualDlDesc = object.getString(INDEX_OF_TWENTY_FIFTH_COLUMN_IN_JSON_RESULT);
        gprsMeasReportSignVarDesc = object.getString(INDEX_OF_TWENTY_SIXTH_COLUMN_IN_JSON_RESULT);
        gprsMeasReportMeanBepDesc = object.getString(INDEX_OF_TWENTY_SEVENTH_COLUMN_IN_JSON_RESULT);
        gprsMeasReportCvBepDesc = object.getString(INDEX_OF_TWENTY_EIGHTH_COLUMN_IN_JSON_RESULT);
        gprsMeasReportCvalueDesc = object.getString(INDEX_OF_TWENTY_NINHTH_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_FORTY_FOURTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_FORTY_FIFTH_COLUMN_IN_JSON_RESULT);
    }

    public String getEventTime() {
        return eventTime;
    }

    public int getTac() {
        return tac;
    }

    public String getController() {
        return controller;
    }

    public String getTerminalMake() {
        return terminalMake;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public String getEventType() {
        return eventType;
    }

    public String getAccessArea() {
        return accessArea;
    }

    public String getTbfReleaseCause() {
        return tbfReleaseCause;
    }

    public String getTbfDataVolume() {
        return tbfDataVolume;
    }

    public String getTbfDuration() {
        return tbfDuration;
    }

    public String getGprsMeasReportRxqualDlDesc() {
        return gprsMeasReportRxqualDlDesc;
    }

    public String getGprsMeasReportSignVarDesc() {
        return gprsMeasReportSignVarDesc;
    }

    public String getGprsMeasReportMeanBepDesc() {
        return gprsMeasReportMeanBepDesc;
    }

    public String getGprsMeasReportCvBepDesc() {
        return gprsMeasReportCvBepDesc;
    }

    public String getGprsMeasReportCvalueDesc() {
        return gprsMeasReportCvalueDesc;
    }

    public String getChannelRelatedReleaseCauseGroupDesc() {
        return channelRelatedReleaseCauseGroupDesc;
    }

}
