/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults.lte.handoverfailure;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT;

import com.ericsson.eniq.events.server.json.*;
import com.ericsson.eniq.events.server.test.queryresults.*;

/**
 * @author ejamves
 * @since 2011
 *
 */

public class LTEHandoverFailureCCListResult implements QueryResult {

    private String causeCodeId;

    private String causeCodeDesc;

    public String getCauseCodeId() {
        return causeCodeId;
    }

    public String getCauseCodeDesc() {
        return causeCodeDesc;
    }

    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        causeCodeId = jsonObject.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        causeCodeDesc = jsonObject.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
    }
}
