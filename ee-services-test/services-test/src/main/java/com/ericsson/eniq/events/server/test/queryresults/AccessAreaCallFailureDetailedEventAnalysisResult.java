/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author etonayr
 * @since 2011
 *
 */
public class AccessAreaCallFailureDetailedEventAnalysisResult implements QueryResult {

    private String eventTime;

    private String imsi;

    private int tac;

    private String terminalMake;

    private String terminalModel;

    private String procedureIndicator;

    private String evaluationCase;

    private String exceptionClass;

    private String causeValue;
    
    private String extendedCauseValue;

    private String severityIndicator;

    private String eventType;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        procedureIndicator = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        evaluationCase = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        exceptionClass = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        causeValue = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        extendedCauseValue = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        severityIndicator = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        

    }

    public String getEventTime() {
        return eventTime;
    }

    public String getImsi() {
        return imsi;
    }

    public int getTac() {
        return tac;
    }

    public String getTerminalMake() {
        return terminalMake;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public String getProcedureIndicator() {
        return procedureIndicator;
    }

    public String getEvaluationCase() {
        return evaluationCase;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public String getCauseValue() {
        return causeValue;
    }

    public String getExtendedCauseValue() {
        return extendedCauseValue;
    }
    
    public String getSeverityIndicator() {
        return severityIndicator;
    }

    public String getEventType() {
        return eventType;
    }
}
