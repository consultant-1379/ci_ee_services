/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author eadrhyn
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureNetworkFilterAnalysisResource extends AbstractResource {

    private static final String WCDMA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_BY_FILTER_SERVICE = "NetworkDetailedEventAnalysisByFilterService";

    @EJB(beanName = WCDMA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_BY_FILTER_SERVICE)
    private Service callFailureDetailedEventByFilterAnalysis;

    @Path(NODE_BY_FILTER)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDEAByNodeFilter() {
        return callFailureDetailedEventByFilterAnalysis.getData(mapResourceLayerParameters());
    }

    @Path(NODE_BY_FILTER)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDEAByNodeFilterAsCSV() {
        return callFailureDetailedEventByFilterAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }
}