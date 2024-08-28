/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

/**
 * @author epesmit
 * @since 2012
 *
 */
public class CallFailureControllerCauseCodeDEAResult implements QueryResult {

    private String eventTime;

    private long imsi;

    private String terminalMake;

    private String terminalModel;

    private String accessArea;

    private String eventType;

    private String procedureIndicator;

    private String evaluationCase;

    private String exceptionClass;

    private String controller;

    private int lac;

    private int rac;

    private int tac;

    private String vendor;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        imsi = object.getLong(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        procedureIndicator = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        evaluationCase = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        exceptionClass = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        lac = object.getInt(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        rac = object.getInt(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getEventTime() {
        return eventTime;
    }

    public long getImsi() {
        return imsi;
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

    public String getAccessArea() {
        return accessArea;
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

    public String getExceptionClass() {
        return exceptionClass;
    }

    public int getLac() {
        return lac;
    }

    public int getRac() {
        return rac;
    }

    public String getVendor() {
        return vendor;
    }

}
