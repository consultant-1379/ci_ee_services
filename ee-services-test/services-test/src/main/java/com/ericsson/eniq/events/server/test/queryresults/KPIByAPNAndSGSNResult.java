/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author EEMECOY
 *
 */
public class KPIByAPNAndSGSNResult implements QueryResult {

    private int rat;

    private String vendor;

    private String controller;

    private String cell;

    private String ratDesc;

    private int eventId;

    private String eventIdDesc;

    private int noErrors;

    private int noSuccesses;

    private int occurrences;

    private int noTotalErrSubscribers;

    private String apn;

    private String sgsn;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rat = object.getInt("1");
        apn = object.getString("2");
        sgsn = object.getString("3");
        vendor = object.getString("4");
        controller = object.getString("5");
        ratDesc = object.getString("6");
        eventId = object.getInt("7");
        eventIdDesc = object.getString("8");
        noErrors = object.getInt("9");
        noSuccesses = object.getInt("10");
        occurrences = object.getInt("11");
        noTotalErrSubscribers = object.getInt("13");
    }

    public int getRAT() {
        return rat;
    }

    public String getVendor() {
        return vendor;
    }

    public String getController() {
        return controller;
    }

    public String getCell() {
        return cell;
    }

    public String getRATDesc() {
        return ratDesc;
    }

    public int getEvendID() {
        return eventId;
    }

    public String getEventDesc() {
        return eventIdDesc;
    }

    public int getNoErrors() {
        return noErrors;
    }

    public int getNoSuccesses() {
        return noSuccesses;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public int getNoTotalErrSubscribers() {
        return noTotalErrSubscribers;
    }

    public String getAPN() {
        return apn;
    }

    public String getSGSN() {
        return sgsn;
    }

}
