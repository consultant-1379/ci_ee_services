/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
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
 * @author ehaoswa
 * @since 2011
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class WCDMACallFailureDetailedEventAnalysisResource extends AbstractResource {

    private static final String WCDMA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "NetworkDetailedEventAnalysisService";

    @EJB(beanName = WCDMA_CALL_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service callFailureDetailedEventAnalysis;

    @Path(NODE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDEAByNode() {
        return callFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
    }

    @Path(NODE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDEAByNodeAsCSV() {
        return callFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }
}