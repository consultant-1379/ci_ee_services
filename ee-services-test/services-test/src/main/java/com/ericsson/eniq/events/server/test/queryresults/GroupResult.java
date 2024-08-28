package com.ericsson.eniq.events.server.test.queryresults;

import java.util.ArrayList;
import java.util.List;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

@Ignore
public class GroupResult implements QueryResult {

	private String groupName;
	
	private final List<String> groupMembers = new ArrayList<String>();
	
	public String getGroupName() {
		return groupName;
	}

	public List<String> getGroupMembers() {
		return groupMembers;
	}

	@Override
	public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
		groupName = jsonObject.getString(JSON_NAME);
		final JSONArray jsonValuesArray = jsonObject.getJSONArray(JSON_VALUES);
		
		for (int i = 0; i < jsonValuesArray.length();i++) {
			final JSONObject arrayItem = jsonValuesArray.getJSONObject(i);
			final String groupMember = arrayItem.getString(JSON_ID);
			groupMembers.add(groupMember);
		}

	}

}
