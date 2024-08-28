/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.summary;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;

import com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicy;
import com.ericsson.eniq.events.server.query.QueryParameter;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.GenericSimpleService;

/**
 * @author eemecoy
 * @since 2012
 *
 */
@Stateless
@Local(Service.class)
public class SBrowserRABThresholdService extends GenericSimpleService {

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericServiceInterface#getTemplatePath()
     */
    @Override
    public String getTemplatePath() {
        return SESSION_SUMMARY + PATH_SEPARATOR + RAB + PATH_SEPARATOR + THRESHOLD;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.serviceprovider.impl.GenericSimpleService#runSimpleQuery(java.lang.String, java.lang.String, java.util.Map, com.ericsson.eniq.events.server.datasource.loadbalancing.LoadBalancingPolicy)
     */
    @Override
    protected String runSimpleQuery(final String query, final String requestId,
            final Map<String, QueryParameter> queryParameters, final LoadBalancingPolicy loadBalancingPolicy) {
        return getDataService().getRABThresholds(query);
    }

}
