/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author eadrhyn
 *
 */
public class RABTypeChartResourceResult implements QueryResult {

	private String rabType;
	private int noFailures;


    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
    	rabType = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
    	noFailures = object.getInt(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    }

	public int getNoFailures() {
		return noFailures;
	}



	public void setNoFailures(int noFailures) {
		this.noFailures = noFailures;
	}




	public String getRabType() {
		return rabType;
	}

	public void setRabType(String rabType) {
		this.rabType = rabType;
	}

}
