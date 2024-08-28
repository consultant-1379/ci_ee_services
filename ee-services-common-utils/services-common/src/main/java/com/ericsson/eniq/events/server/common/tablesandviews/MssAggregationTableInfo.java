/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.common.tablesandviews;

import java.util.HashMap;
import java.util.Map;

import com.ericsson.eniq.events.server.common.EventDataSourceType;

/**
 * Hold information about the time ranges available for MSS particular aggregations
 * 
 * MssAggregationTableInfo extends from AggregationTableInfo, but not use AggregationTableInfo's variables
 * 
 * @author ezhibhe
 * @since Jun 2011
 */
public class MssAggregationTableInfo extends AggregationTableInfo {

    /**
     * MSSAggregationType is enum for voice, sms and loc_service
     * @author ezhibhe
     * @since 2011
     *
     */
    public enum MSSAggregationType {
        VOICE, SMS, LOC_SERVICE
    };

    private final Map<MSSAggregationType, String> aggregationTables;

    /**
     * @param aggregationTable          key for the aggregation table - eg VEND_HIER3 (note this is stripped of the tech pack prefix, in order
     * to keep it tech pack neutral, and also stripped of the _time range suffix)
     * @param timeranges                sequence of the aggregation time ranges that are available for this aggregation key
     */
    public MssAggregationTableInfo(final String aggregationTable, final EventDataSourceType... timeranges) {
        super(aggregationTable, timeranges);
        this.aggregationTables = new HashMap<MSSAggregationType, String>();
        this.aggregationTables.put(MSSAggregationType.VOICE, aggregationTable);
    }

    /**
     * @param aggregationTable          key for the aggregation table - eg VEND_HIER3 (note this is stripped of the tech pack prefix, in order
     * to keep it tech pack neutral, and also stripped of the _time range suffix)
     * @param timeranges                sequence of the aggregation time ranges that are available for this aggregation key
     */
    public MssAggregationTableInfo(final Map<MSSAggregationType, String> aAggregationTables,
            final EventDataSourceType... timeranges) {
        super(aAggregationTables.get(MSSAggregationType.VOICE), timeranges);
        this.aggregationTables = aAggregationTables;
    }

    /**
     * getAggregationView is used to return aggregation view for voice
     */
    @Override
    public String getAggregationView() {
        return getAggregationViewVoice();
    }

    /**
     * getAggregationView is used to return aggregation view for voice
     */
    public String getAggregationViewVoice() {
        return aggregationTables.get(MSSAggregationType.VOICE);
    }

    /**
     * getAggregationView is used to return aggregation view for sms
     */
    public String getAggregationViewSMS() {
        return aggregationTables.get(MSSAggregationType.SMS);
    }

    /**
     * getAggregationView is used to return aggregation view for sms
     */
    public String getAggregationViewLosService() {
        return aggregationTables.get(MSSAggregationType.LOC_SERVICE);
    }
}
