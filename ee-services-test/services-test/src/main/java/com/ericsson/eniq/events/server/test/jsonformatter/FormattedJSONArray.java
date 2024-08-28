/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.jsonformatter;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;

/**
 * For use in test - applies some formatting to a JSON array mainly putting each array element on a new line
 * 
 * @author eemecoy
 *
 */
public class FormattedJSONArray extends JSONArray {

    private static final String NEW_LINE = "\n";

    private static final String OPEN_ARRAY = "[";

    private static final String CLOSE_ARRAY = "]";

    /**
     * @param formattedJSONTokenizer
     * @throws JSONException 
     */
    public FormattedJSONArray(final FormattedJSONTokenizer formattedJSONTokenizer) throws JSONException {
        super(formattedJSONTokenizer);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.json.JSONArray#toString()
     */
    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(OPEN_ARRAY);
        if (length() > 1) {
            stringBuilder.append(NEW_LINE);
        }
        for (int i = 0; i < length(); i++) {
            try {
                stringBuilder.append(get(i));
                if (isNotLastElement(i)) {
                    stringBuilder.append(NEW_LINE);
                }
            } catch (final JSONException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append(CLOSE_ARRAY);
        return stringBuilder.toString();
    }

    private boolean isNotLastElement(final int i) {
        return i < length() - 1;
    }

}
