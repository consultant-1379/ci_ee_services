/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.wcdma.callfailure.subbi;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * Class that extends the functionality of the CallFailureSubsessionBIService to include indicating the time columns
 * This is needed becuase CallFailureSubsessionBIService is used for the chart and the drilldown urls, and only the 
 * drilldown contains a time column.
 * 
 * @author eeikonl
 * @since 2011
 *
 */
@Stateless
@Local(Service.class)
public class CallFailureSubsessionBIDrilldownService extends CallFailureSubsessionBIService {

    /*
     * (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericService#getTimeColumnIndices()
     */
    @Override
    public List<Integer> getTimeColumnIndices() {
        final List<Integer> columnIndices = new ArrayList<Integer>();
        columnIndices.add(1);
        return columnIndices;
    }
}
