/**
* -----------------------------------------------------------------------
* COPYRIGHT Ericsson 2013 
* The copyright to the computer program(s) herein is the property of 
* Ericsson Inc. The programs may be used and/or copied only with written 
* permission from Ericsson Inc. or in accordance with the terms and 
* conditions stipulated in the agreement/contract under which the 
* program(s) have been supplied.
* -----------------------------------------------------------------------
*/
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

public class CallFailureTerminalAnalysisResult implements QueryResult {

    private String manufacturer;

    private String marketingName;

    private int tac;

    private int numFailures;

    private int rank;

    private int eventId;
    
    private String reestablishmentFailures;

    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        rank = object.getInt(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        tac = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        manufacturer = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        marketingName = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        numFailures = object.getInt(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
        eventId = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
        reestablishmentFailures = object.getString(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public int getTac() {
        return tac;
    }

    public int getNumFailures() {
        return numFailures;
    }

    public int getRank() {
        return rank;
    }

    public int getEventID() {
        return eventId;
    }
    
    public String getReestablishmentFailures() {
        return reestablishmentFailures;
    }
}
