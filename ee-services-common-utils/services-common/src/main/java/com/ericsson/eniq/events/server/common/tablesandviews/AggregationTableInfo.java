/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.common.tablesandviews;

import java.util.ArrayList;
import java.util.List;
import com.ericsson.eniq.events.server.common.EventDataSourceType;

/**
 * Hold information about the time ranges available for particular aggregations
 * The list of time ranges available for each aggregation is particular/specific to each query - some queries do not use aggregations, even when the
 * aggregations are present (this may be due to performance or to concerns that the aggregation (eg the _1MIN) aggregation may not be processed in time.
 * @author eemecoy
 *
 */
public class AggregationTableInfo {

    private final String aggregationTable;

    private final EventDataSourceType[] timeranges;

    private final List<String> aggregationTableList;
    
    private String secondaryAggregationView;

    /**
     * Constructor for the AggregationTableInfo object
     *
     * @param aggregationTable          key for the aggregation table - eg VEND_HIER3 (note this is stripped of the tech pack prefix, in order
     * to keep it tech pack neutral, and also stripped of the _time range suffix)
     * @param timeranges                sequence of the aggregation time ranges that should be used for this aggregation
     */
    public AggregationTableInfo(final String aggregationTable, final EventDataSourceType... timeranges) {
        this.aggregationTable = aggregationTable;
        this.aggregationTableList = new ArrayList<String>();
        this.aggregationTableList.add(aggregationTable);
        this.timeranges = timeranges;
    }

    /**
     * Constructor for the AggregationTableInfo object to support multiple aggregation sets
     *
     * @param firstAggregationTable    key for the aggregation table - e.g. HIER3_CELL (note this is stripped of the tech pack prefix, in order
     * to keep it tech pack neutral, and also stripped of the _time range suffix)
     * @param secondAggregationTable   key for the alternative aggregation table - e.g. THIER3_CELL (note this is stripped of the tech pack prefix, in order
     * to keep it tech pack neutral, and also stripped of the _time range suffix)
     * @param timeranges                sequence of the aggregation time ranges that are available for this aggregation key
     */
    public AggregationTableInfo(final String firstAggregationTable, final String secondAggregationTable,
            final EventDataSourceType... timeranges) {
        this(firstAggregationTable, timeranges);
        this.aggregationTableList.add(secondAggregationTable);
        this.secondaryAggregationView = secondAggregationTable;
    }

    /**
     *
     * @return aggregationTable the aggregation table
     */
    public String getAggregationView() {
        return aggregationTable;
    }

    /**
     *
     * @return aggregationTable the aggregation table list for multiple aggregation sets
     */
    public List<String> getAggregationViewsList() {
        return aggregationTableList;
    }

    /**
     * @return   sequence of the aggregation time ranges that are available for this aggregation key
     */
    public EventDataSourceType[] getAggregationTimeRanges() {
        return timeranges;
    }

    /**
     * Returns true if the specified timerange has a corresponding aggregation table for this aggregationTable key
     * Eg if the key is APN_EVENTID_EVNTSRC_VEND_HIER3, and the timerange parameter is _15MIN, then false is returned, as
     * there is no such table EVENT_E_XXX_APN_EVENTID_EVNTSRC_VEND_HIER3_15MIN
     * @param timerange one of EventDataSourceType's enums as a string (eg the string value of the _1DAY enum is TR_4)
     */
    public boolean hasAggregationTimeRangeFor(final String timerange) {
        for (final EventDataSourceType agg : timeranges) {
            if (agg.toString().equals(timerange)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * @return the secondary aggregation view
     */
    public String getSecondaryAggregationView() {
    	return secondaryAggregationView;
    }

} 






