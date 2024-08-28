/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ezhelao
 */
public class CallFailurCountryDrilDetaillRoamingQueryResult implements QueryResult {


    private String imsi;
    private String terminalMake;
    private String eventTypeDesc;
    private String procedureIndicator;
    private String evaludationCase;
    private String exceptionClass;
    private String causeValue;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        imsi = object.getString("2");
        terminalMake = object.getString("4");
        eventTypeDesc = object.getString("6");
        procedureIndicator = object.getString("7");
        evaludationCase = object.getString("8");
        exceptionClass = object.getString("9");
        causeValue = object.getString("10");
    }

    public String getImsi() {
        return imsi;
    }

    public String getTerminalMake() {
        return terminalMake;
    }

    public String getEventTypeDesc() {
        return eventTypeDesc;
    }

    public String getProcedureIndicator() {
        return procedureIndicator;
    }

    public String getEvaludationCase() {
        return evaludationCase;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public String getCauseValue() {
        return causeValue;
    }
}
