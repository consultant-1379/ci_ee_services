package com.ericsson.eniq.events.server.common.tablesandviews;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.common.tablesandviews.MssAggregationTableInfo.MSSAggregationType;

public class MssAggregationTableInfoTest {

    private MssAggregationTableInfo testObj;

    private final Map<MSSAggregationType, String> mapAll;

    public MssAggregationTableInfoTest() {
        mapAll = new HashMap<MSSAggregationType, String>();
        mapAll.put(MSSAggregationType.VOICE, "VOICE_1");
        mapAll.put(MSSAggregationType.SMS, "SMS_1");
        mapAll.put(MSSAggregationType.LOC_SERVICE, "LOC_SERVICE_1");
    }

    @Test
    public void testGetAggregationView() {
        testObj = new MssAggregationTableInfo(mapAll, EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertEquals("VOICE_1", testObj.getAggregationView());
        testObj = new MssAggregationTableInfo("VOICE_1", EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertEquals("VOICE_1", testObj.getAggregationView());
    }

    @Test
    public void testGetAggregationViewVoice() {
        testObj = new MssAggregationTableInfo(mapAll, EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertEquals("VOICE_1", testObj.getAggregationViewVoice());
        testObj = new MssAggregationTableInfo("VOICE_1", EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertEquals("VOICE_1", testObj.getAggregationViewVoice());
    }

    @Test
    public void testGetAggregationViewSMS() {
        testObj = new MssAggregationTableInfo(mapAll, EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertEquals("SMS_1", testObj.getAggregationViewSMS());
        testObj = new MssAggregationTableInfo("VOICE_1", EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertNull(testObj.getAggregationViewSMS());
    }

    @Test
    public void testGetAggregationViewLocService() {
        testObj = new MssAggregationTableInfo(mapAll, EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertEquals("LOC_SERVICE_1", testObj.getAggregationViewLosService());
        testObj = new MssAggregationTableInfo("VOICE_1", EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertNull(testObj.getAggregationViewLosService());
    }

    @Test
    public void testGetAggregationTimeRanges() {
        testObj = new MssAggregationTableInfo(mapAll, EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        final EventDataSourceType[] actuals = { EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY };
        assertArrayEquals(testObj.getAggregationTimeRanges(), actuals);
    }

    @Test
    public void testHasAggregationTimeRangeFor() {
        testObj = new MssAggregationTableInfo(mapAll, EventDataSourceType.AGGREGATED_15MIN,
                EventDataSourceType.AGGREGATED_DAY);
        assertTrue(testObj.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_15MIN.getValue()));
        assertTrue(testObj.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_DAY.getValue()));
        assertFalse(testObj.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_1MIN.getValue()));
    }

    @Test
    public void testMSSAggregationType() {
        // This test case is only used for corverage
        MSSAggregationType.values();
        assertTrue(MSSAggregationType.VOICE == MSSAggregationType.valueOf("VOICE"));
    }
}
