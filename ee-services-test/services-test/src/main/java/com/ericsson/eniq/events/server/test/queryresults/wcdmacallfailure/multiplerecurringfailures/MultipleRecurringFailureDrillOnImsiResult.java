/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdmacallfailure.multiplerecurringfailures;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eemecoy
 *
 */
public class MultipleRecurringFailureDrillOnImsiResult implements QueryResult {

    private String eventTime;

    private String tac;

    private String terminalMake;

    private String imsi;

    private String terminalModel;

    private String eventType;

    private String procedureIndicator;

    private String evaluationCase;

    private String exceptionCase;

    private String causeValue;

    private String extendedCauseValue;

    private String severityIndicator;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        tac = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        procedureIndicator = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        evaluationCase = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        exceptionCase = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        causeValue = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        extendedCauseValue = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        severityIndicator = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getTac() {
        return tac;
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

    public String getProcedureIndicator() {
        return procedureIndicator;
    }

    public String getEvaluationCase() {
        return evaluationCase;
    }

    public String getExceptionCase() {
        return exceptionCase;
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

    /**
     * @return the imsi
     */
    public String getImsi() {
        return imsi;
    }
}
