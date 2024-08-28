/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.sbr;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserRanCFADetailListResult implements QueryResult {

    private String eventTime;

    private String eventType;

    private String eventClassType;
    
    private String cId;
    
    private String cellId;
    
    private String tac;
    
    public String getEventTime() {
        return eventTime;
    }

    public String getEventClassType() {
        return eventClassType;
    }

    public String getEventType() {
        return eventType;
    }

    public String getcId() {
        return cId;
    }

    public String getCellId() {
        return cellId;
    }

    public String getTac() {
        return tac;
    }

    /* (non-Javadoc)
    * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
    */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        eventTime = object.getString("eventTime");
        eventType = object.getString("eventType");
        eventClassType = object.getString("eventClassType");
        cId = object.getString("cId");
        cellId = object.getString("cellId");
        tac = object.getString("tac");
    }
}
