/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

/**
 * This class should be the base class for any of the event analysis summary results
 * that need to be sorted by event id. Very convenient when reusing a result set 
 * verification method on the same data from different queries where the data comes back in a different order
 * 
 * @author ejoegaf
 *
 */
public abstract class BaseEventAnalysisSummaryResultSortableByEventID implements QueryResult,
        Comparable<BaseEventAnalysisSummaryResultSortableByEventID> {

    public abstract int getEventId();

    @Override
    public int compareTo(final BaseEventAnalysisSummaryResultSortableByEventID otherEventAnalaysisSummaryResult) {
        final int thisEventID = this.getEventId();
        final int otherEventID = otherEventAnalaysisSummaryResult.getEventId();
        return thisEventID - otherEventID;
    }
}
