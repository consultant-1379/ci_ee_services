package com.ericsson.eniq.events.server.test.queryresults;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

@Ignore
public class TACGroupEventAnalysisSummaryResult extends BaseEventAnalysisSummaryResultSortableByEventID implements QueryResult {

    private String manufacturer;

    private int eventId;

    private String eventIdDesc;

    private int errorCount;

    private int successCount;

    private int occurrences;

    private double successRatio;

    private int errorSubscriberCount;

    private String tacGroup;

    public String getManufacturer() {
        return manufacturer;
    }

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

    public String getTACGroup() {
        return tacGroup;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.testswithtemporarytables.TemporaryTableTestResult#parseJSONObject(net.sf.json.JSONObject)
     */
    @Override
    public void parseJSONObject(final JSONObject jsonObject) throws JSONException {
        tacGroup = jsonObject.getString("1");
        eventId = jsonObject.getInt("2");
        eventIdDesc = jsonObject.getString("3");
        errorCount = jsonObject.getInt("4");
        successCount = jsonObject.getInt("5");
        occurrences = jsonObject.getInt("6");
        successRatio = jsonObject.getDouble("7");
        errorSubscriberCount = jsonObject.getInt("8");
    }

    public double getExpectedSuccessRatio() {
        return SuccessRatioCalculator.calculateSuccessRatio(occurrences, successCount);
    }

}
