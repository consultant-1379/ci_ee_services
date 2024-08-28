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
public class RABDescriptionChartResourceResult implements QueryResult {

	private String rabTypeId;
	private String rabTypeDesc;
	private int noFailures;
    private int subscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
    	setRabTypeId(object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT));
    	rabTypeDesc = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    	noFailures = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
    	subscribers = object.getInt(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
    }


	public String getRabTypeDesc() {
		return rabTypeDesc;
	}



	public void setRabTypeDesc(String rabTypeDesc) {
		this.rabTypeDesc = rabTypeDesc;
	}



	public int getNoFailures() {
		return noFailures;
	}



	public void setNoFailures(int noFailures) {
		this.noFailures = noFailures;
	}



	public int getSubscribers() {
		return subscribers;
	}



	public void setSubscribers(int subscribers) {
		this.subscribers = subscribers;
	}
	public String getRabTypeId() {
		return rabTypeId;
	}

	public void setRabTypeId(String rabTypeId) {
		this.rabTypeId = rabTypeId;
	}

}
