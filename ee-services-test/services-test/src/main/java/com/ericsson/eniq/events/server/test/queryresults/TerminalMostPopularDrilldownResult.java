/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * @author eemecoy
 *
 */
public class TerminalMostPopularDrilldownResult implements QueryResult {

    private String manufacturer;

    private String marketingName;

    private int tac;

    private int noTotalEvents;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        manufacturer = jsonObject.getString("1");
        marketingName = jsonObject.getString("2");
        tac = jsonObject.getInt("3");
        noTotalEvents = jsonObject.getInt("4");
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public int getTAC() {
        return tac;
    }

    public int getTotalEvents() {
        return noTotalEvents;
    }

}
