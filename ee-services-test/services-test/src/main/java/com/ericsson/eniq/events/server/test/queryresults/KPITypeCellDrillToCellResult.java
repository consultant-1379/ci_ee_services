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
public class KPITypeCellDrillToCellResult implements QueryResult {

    private int rat;

    private String vendor;

    private String controller;

    private String cell;

    private String ratDesc;

    private int eventId;

    private String eventIdDesc;

    private int noErrors;

    private int noTotalErrSubscribers;

    private int causeCode;

    private int subCauseCode;

    private String causeCodeDesc;

    private String subCauseCodeDesc;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rat = object.getInt("1");
        vendor = object.getString("2");
        controller = object.getString("3");
        cell = object.getString("4");
        causeCode = object.getInt("5");
        subCauseCode = object.getInt("6");
        causeCodeDesc = object.getString("7");
        subCauseCodeDesc = object.getString("8");
        ratDesc = object.getString("9");
        eventId = object.getInt("10");
        eventIdDesc = object.getString("11");
        noErrors = object.getInt("12");
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

    public int getNoTotalErrSubscribers() {
        return noTotalErrSubscribers;
    }

    public int getCauseCode() {
        return causeCode;
    }

    public int getSubCauseCode() {
        return subCauseCode;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    public String getSubCauseCodeDesc() {
        return subCauseCodeDesc;
    }

}
