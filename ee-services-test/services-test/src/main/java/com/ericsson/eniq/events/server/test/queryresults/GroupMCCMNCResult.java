package com.ericsson.eniq.events.server.test.queryresults;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import org.junit.Ignore;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.JSON_ID;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.JSON_NAME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.JSON_VALUES;

@Ignore
public class GroupMCCMNCResult implements QueryResult {

	private String groupName;
	
	private final List<List<String>> groupMembers = new ArrayList<List<String>>();
	
	public String getGroupName() {
		return groupName;
	}

	public List<List<String>> getGroupMembers() {
		return groupMembers;
	}

	@Override
	public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
		groupName = jsonObject.getString(JSON_NAME);
		final JSONArray jsonValuesArray = jsonObject.getJSONArray(JSON_VALUES);
		
		for (int i = 0; i < jsonValuesArray.length();i++) {
			final JSONObject arrayItem = jsonValuesArray.getJSONObject(i);
			final JSONObject idObject = arrayItem.getJSONObject(JSON_ID);
            final List<String> members  = new ArrayList<String>();
            members.add(idObject.getString("1"));
            members.add(idObject.getString("2"));
            members.add(idObject.getString("3"));
            members.add(idObject.getString("4"));
            groupMembers.add(members);
		}

	}

}
