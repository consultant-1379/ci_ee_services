/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * Having the test class know which index an event id's data is at isn't ideal, but the entire event volume solution
 * is due to change, may revisit this afterwards
 * @author eemecoy
 *
 */
public class NetworkEventVolumeResult implements QueryResult {

    private final List<Integer> values = new ArrayList<Integer>();

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        for (int i = 1; i <= 32; i++) {
            final int value = object.getInt(Integer.toString(i));
            values.add(value);
        }

    }

    public int getDataAtIndex(final int index) {
        return values.get(index - 1);
    }

}
