/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ewanggu
 * @since 2011
 *
 */
public class GSMCallFailureCellDetailSCCEventAnalysisResult implements QueryResult {

    private String eventTime;

    private String imsi;

    private int tac;

    // private int msisdn;

    private String terminalMake;

    private String terminalModel;

    private String controller;

    private String eventType;

    private String releaseType;

    private String vendor;

    private String vamos;

    private String rsai;

    private String channel;

    private String urgency;

    private String cell;

    private String cause;

    private String exCause;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        eventType = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        vendor = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        controller = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        cell = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        imsi = object.getString(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
        terminalMake = object.getString(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
        terminalModel = object.getString(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
        releaseType = object.getString(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);
        urgency = object.getString(INDEX_OF_ELEVENTH_COLUMN_IN_JSON_RESULT);
        cause = object.getString(INDEX_OF_TWELFTH_COLUMN_IN_JSON_RESULT);
        exCause = object.getString(INDEX_OF_THIRTEENTH_COLUMN_IN_JSON_RESULT);
        channel = object.getString(INDEX_OF_SIXTEENTH_COLUMN_IN_JSON_RESULT);
        vamos = object.getString(INDEX_OF_SEVENTEENTH_COLUMN_IN_JSON_RESULT);
        rsai = object.getString(INDEX_OF_EIGHTEENTH_COLUMN_IN_JSON_RESULT);

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

    /*  public int getMsisdn() {
          return msisdn;
      }
    */
    public String getTerminalMake() {
        return terminalMake;
    }

    public String getTerminalModel() {
        return terminalModel;
    }

    public String getController() {
        return controller;
    }

    public String getEventType() {
        return eventType;
    }

    public String getReleaseType() {
        return releaseType;
    }

    public String getVendor() {
        return vendor;
    }

    public String getVamos() {
        return vamos;
    }

    public String getRsai() {
        return rsai;
    }

    public String getChannel() {
        return channel;
    }

    public String getUrgency() {
        return urgency;
    }

    public String getCell() {
        return cell;
    }

    public String getCause() {
        return cause;
    }

    public String getExCause() {
        return exCause;
    }
}
