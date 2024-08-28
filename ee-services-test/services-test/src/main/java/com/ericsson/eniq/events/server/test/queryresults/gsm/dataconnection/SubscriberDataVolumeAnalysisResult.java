/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.gsm.dataconnection;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

/**
 * @author ETHOMIT
 * @since 2012
 * 
 */
public class SubscriberDataVolumeAnalysisResult implements QueryResult {

	private String imsi;

	private double downLoadDataVol;

	private int downLoadDuration;

	private double downLoadThroughput;

	private double upLoadDataVol;

	private int upLoadDuration;

	private double upLoadThroughput;

	private double totalDataVol;

	private int totalDuration;

	private double totalThroughput;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject
	 * (com.ericsson.eniq.events.server.json.JSONObject)
	 */
	@Override
	public void parseJSONObject(final JSONObject object) throws JSONException {
		imsi = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
		downLoadDataVol = object
				.getDouble(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
		downLoadDuration = object.getInt(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
		downLoadThroughput = object
				.getDouble(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
		upLoadDataVol = object.getDouble(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
		upLoadDuration = object.getInt(INDEX_OF_SIXTH_COLUMN_IN_JSON_RESULT);
		upLoadThroughput = object
				.getDouble(INDEX_OF_SEVENTH_COLUMN_IN_JSON_RESULT);
		totalDataVol = object.getDouble(INDEX_OF_EIGHTH_COLUMN_IN_JSON_RESULT);
		totalDuration = object.getInt(INDEX_OF_NINTH_COLUMN_IN_JSON_RESULT);
		totalThroughput = object
				.getDouble(INDEX_OF_TENTH_COLUMN_IN_JSON_RESULT);

	}

	public String getImsi() {
		return imsi;
	}

	public double getDownLoadDataVol() {
		return downLoadDataVol;
	}

	public int getDownLoadDuration() {
		return downLoadDuration;
	}

	public double getDownLoadThroughput() {
		return downLoadThroughput;
	}

	public double getUpLoadDataVol() {
		return upLoadDataVol;
	}

	public int getUpLoadDuration() {
		return upLoadDuration;
	}

	public double getUpLoadThroughput() {
		return upLoadThroughput;
	}

	public double getTotalDataVol() {
		return totalDataVol;
	}

	public int getTotalDuration() {
		return totalDuration;
	}

	public double getTotalThroughput() {
		return totalThroughput;
	}

}