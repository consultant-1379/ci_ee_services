/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.jsonformatter;

import java.util.LinkedHashMap;
import java.util.Map;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * For use in test - applies some formatting to a JSON object, mainly maintaining the order of keys in the
 * the inputted json string
 * 
 * @author eemecoy
 *
 */
public class FormattedJSONObject extends JSONObject {

    /**
     * @param json
     * @throws JSONException 
     */
    public FormattedJSONObject(final String json) throws JSONException {
        super(new FormattedJSONTokenizer(json));
    }

    /**
     * @param formattedJSONTokenizer
     * @throws JSONException 
     */
    public FormattedJSONObject(final FormattedJSONTokenizer formattedJSONTokenizer) throws JSONException {
        super(formattedJSONTokenizer);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.json.JSONObject#initializeBackingMap()
     */
    @Override
    protected Map<Object, Object> createEmptyBackingMap() {
        return new LinkedHashMap<Object, Object>();
    }

}
