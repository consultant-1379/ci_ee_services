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
 * @author eflatib
 * @since 2011
 *
 */
public class SubscriberCFFailureAnalysisEAbyImsiGroupResult implements QueryResult {

    private String eventTime;
    
    private int imsi;

    private int tac;

    private String terminalMake;

    private String terminalModel;

    private String eventType;

    private String procedureIndicator;

    private String evaluationCase;

    private String exceptionClass;

    private String causeValue;

    private String extendedCauseValue;

    private String severityIndicator;

    private String controller;

    private String accessArea;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
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
        controller = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
    }

    /**
     * @return the eventTime
     */
    public String getEventTime() {
        return eventTime;
    }

    /**
     * @return the eventTime
     */
    public int getImsi() {
        return imsi;
    }
    
    /**
     * @return the eventTime
     */
    public int getTac() {
        return tac;
    }

    /**
     * @return the terminalMake
     */
    public String getTerminalMake() {
        return terminalMake;
    }

    /**
     * @return the terminalModel
     */
    public String getTerminalModel() {
        return terminalModel;
    }

    /**
     * @return the eventType
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * @return the procedureIndicator
     */
    public String getProcedureIndicator() {
        return procedureIndicator;
    }

    /**
     * @return the evaluationCase
     */
    public String getEvaluationCase() {
        return evaluationCase;
    }

    /**
     * @return the exceptionClass
     */
    public String getExceptionClass() {
        return exceptionClass;
    }

    /**
     * @return the causeValue
     */
    public String getCauseValue() {
        return causeValue;
    }

    /**
     * @return the extendedCauseValue
     */
    public String getExtendedCauseValue() {
        return extendedCauseValue;
    }

    /**
     * @return the severityIndicator
     */
    public String getSeverityIndicator() {
        return severityIndicator;
    }

    /**
     * @return the controller
     */
    public String getController() {
        return controller;
    }

    /**
     * @return the accessArea
     */
    public String getAccessArea() {
        return accessArea;
    }

}
