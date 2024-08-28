package com.ericsson.eniq.events.server.test.queryresults.dashboard.terminal;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.queryresults.BaseEventAnalysisSummaryResultSortableByEventID;

import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;

/**
 * Created by IntelliJ IDEA.
 * User: eeikbe
 * Date: 23/11/11
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class SummaryTerminalDDResult extends BaseEventAnalysisSummaryResultSortableByEventID {

    //The make ('Sony Ericsson'|'Apple'|'Nokia' etc...) of the terminal.
    private String terminalMake;

    //The model ('Xperia X10'|'iPhone 4'|'1800' etc...)of the terminal.
    private String terminalModel;

    private String terminalTac;

    //The number of failures for the terminal.
    private String terminalFailures;

    //The number of subscribers experiencing the fault.
    private String terminalSubscribers;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.QueryResult#parseJSONObject(com.ericsson.eniq.events.server.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject object) throws JSONException {
        this.terminalMake = object.getString(INDEX_OF_FIRST_COLUMN_IN_JSON_RESULT);
        this.terminalModel = object.getString(INDEX_OF_SECOND_COLUMN_IN_JSON_RESULT);
        this.terminalTac = object.getString(INDEX_OF_THIRD_COLUMN_IN_JSON_RESULT);
        this.terminalFailures = object.getString(INDEX_OF_FOURTH_COLUMN_IN_JSON_RESULT);
        this.terminalSubscribers = object.getString(INDEX_OF_FIFTH_COLUMN_IN_JSON_RESULT);

    }

    public String getTerminalTac() {
        return this.terminalTac;
    }

    public String getTerminalMake() {
        return this.terminalMake;
    }

    public String getTerminalModel() {
        return this.terminalModel;
    }

    public String getTerminalFailures() {
        return this.terminalFailures;
    }

    public String getTerminalSubscribers() {
        return this.terminalSubscribers;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.queryresults.BaseEventAnalysisSummaryResultSortableByEventID#getEventId()
     */
    @Override
    public int getEventId() {
        return 0;
    }
}
