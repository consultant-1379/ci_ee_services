package com.ericsson.eniq.events.server.test.queryresults.wcdma.callfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.QueryResult;

public class ControllerGroupCallFailureEventSummaryResult implements
		QueryResult {

	private String eventType;

	private String noOfErrors;

	private String impactedSubscribers;

	private String eventId;

	private String groupName;

	public String getEventType() {
		return eventType;
	}

	public String getNumOfFailures() {
		return noOfErrors;
	}

	public String getImpactedSubscribers() {
		return impactedSubscribers;
	}

	public String getEventId() {
		return eventId;
	}

	public String getGroupName() {
		return groupName;
	}

	@Override
	public void parseJSONObject(final JSONObject jsonObject)
			throws JSONException {

		groupName = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
		eventType = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
		noOfErrors = jsonObject
				.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
		impactedSubscribers = jsonObject
				.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
		eventId = jsonObject.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);
	}

}
