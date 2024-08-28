package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

@Ignore
public class TACEventAnalysisSummaryResult extends BaseEventAnalysisSummaryResultSortableByEventID implements
        QueryResult {

    private String manufacturer;

    private int eventId;

    private String eventIdDesc;

    private int errorCount;

    private int successCount;

    private int occurrences;

    private double successRatio;

    private int errorSubscriberCount;

    private int tac;

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public int getEventId() {
        return eventId;
    }

    public String getEventIdDesc() {
        return eventIdDesc;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public double getSuccessRatio() {
        return successRatio;

    }

    public int getErrorSubscriberCount() {
        return errorSubscriberCount;
    }

    public int getTAC() {
        return tac;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        tac = jsonObject.getInt("3");
        eventId = jsonObject.getInt("4");
        eventIdDesc = jsonObject.getString("5");
        errorCount = jsonObject.getInt("6");
        successCount = jsonObject.getInt("7");
        occurrences = jsonObject.getInt("8");
        successRatio = jsonObject.getDouble("9");
        errorSubscriberCount = jsonObject.getInt("10");
    }

    public double getExpectedSuccessRatio() {
        return SuccessRatioCalculator.calculateSuccessRatio(occurrences, successCount);
    }

}
