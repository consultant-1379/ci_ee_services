/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.tablesandviews;

import static com.ericsson.eniq.events.server.common.tablesandviews.TableKeys.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.ericsson.eniq.events.server.common.EventDataSourceType;

/**
 * @author eemecoy
 *
 */
public class AggregationTableInfoTest {

    private static final String SOURCE_AGG_VIEW_TYPE = "HIER3_CELL";

    private static final String TARGET_AGG_VIEW_TYPE = "THIER3_CELL";

    @Test
    public void testHasAggregationTimeRangeFor() {
        final AggregationTableInfo aggregationTableInfo = new AggregationTableInfo(MANUF_TAC_EVENTID,
                EventDataSourceType.AGGREGATED_15MIN, EventDataSourceType.AGGREGATED_DAY);
        assertThat(aggregationTableInfo.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_1MIN.toString()),
                is(false));
        assertThat(aggregationTableInfo.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_15MIN.toString()),
                is(true));
        assertThat(aggregationTableInfo.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_DAY.toString()),
                is(true));
    }

    @Test
    public void testHasAggregationTimeRangeForMultipleSets() {
        final AggregationTableInfo aggregationTableInfo = new AggregationTableInfo(SOURCE_AGG_VIEW_TYPE,
                TARGET_AGG_VIEW_TYPE, EventDataSourceType.AGGREGATED_15MIN, EventDataSourceType.AGGREGATED_DAY);
        assertThat(aggregationTableInfo.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_1MIN.toString()),
                is(false));
        assertThat(aggregationTableInfo.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_15MIN.toString()),
                is(true));
        assertThat(aggregationTableInfo.hasAggregationTimeRangeFor(EventDataSourceType.AGGREGATED_DAY.toString()),
                is(true));
    }

    @Test
    public void testIsMultipleAggregationViewsPresent() {
        final AggregationTableInfo aggregationTableInfo = new AggregationTableInfo(SOURCE_AGG_VIEW_TYPE,
                TARGET_AGG_VIEW_TYPE, EventDataSourceType.AGGREGATED_15MIN, EventDataSourceType.AGGREGATED_DAY);
        final List<String> aggregationTableList = aggregationTableInfo.getAggregationViewsList();
        assertEquals(2, aggregationTableList.size());

        // ArrayList is in FIFO order, so check the  aggregationTables in the same order as were inserted in the constructor
        final String firstAggregationTable = aggregationTableList.get(0);
        assertEquals(SOURCE_AGG_VIEW_TYPE, firstAggregationTable);
        final String secondAggregationTable = aggregationTableList.get(1);
        assertEquals(TARGET_AGG_VIEW_TYPE, secondAggregationTable);
    }
}
