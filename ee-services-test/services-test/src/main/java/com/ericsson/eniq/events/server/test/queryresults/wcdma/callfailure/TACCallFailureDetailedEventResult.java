/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eriwals
 *
 */
public class TACCallFailureDetailedEventResult implements QueryResult {

    private long imsi;

    private int tac;

    private String manufacturer;

    private String marketingName;

    private String procedureIndicatorDescrption;

    private String eventType;

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
        imsi = object.getLong(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        manufacturer = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        marketingName = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        procedureIndicatorDescrption = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        evaluationCase = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        exceptionClass = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        causeValue = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        extendedCauseValue = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        severityIndicator = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        accessArea = object.getString(INDEX_OF_FOURTEENTH_COLUMN_IN_JSON_RESULT);

    }

    public long getIMSI() {
        return imsi;
    }

    public int getTAC() {
        return tac;
    }

    public String getManufacturer() {
        return manufacturer;

    }

    public String getMarketingName() {
        return marketingName;
    }

    public String getProcedureIndicatorDescription() {
        return procedureIndicatorDescrption;
    }

    public String getSeverityIndicator() {
        return severityIndicator;
    }

    public String getExtendedCaluseValue() {
        return extendedCauseValue;
    }

    public String getCauseValue() {
        return causeValue;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public String getEvaluationCase() {
        return evaluationCase;
    }

    public String getEventType() {
        return eventType;
    }

    public String getController() {
        return controller;
    }

    public String getAccessArea() {
        return accessArea;
    }
}
