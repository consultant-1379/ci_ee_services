/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.jsonformatter;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.json.JSONTokenizer;

/**
 * For use in test - applies some formatting to a JSON array mainly putting each array element on a new line
 * This class enables the existing JSON parser to be intercepted to use a different formatter when printing results
 * 
 * @author eemecoy
 *
 */
public class FormattedJSONTokenizer extends JSONTokenizer {

    public FormattedJSONTokenizer(final String json) {
        super(json);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.json.JSONTokenizer#createJSONObject()
     */
    @Override
    protected JSONObject createJSONObject() throws JSONException {
        return new FormattedJSONObject(this);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.json.JSONTokenizer#createJSONArray()
     */
    @Override
    protected JSONArray createJSONArray() throws JSONException {
        return new FormattedJSONArray(this);
    }

}
