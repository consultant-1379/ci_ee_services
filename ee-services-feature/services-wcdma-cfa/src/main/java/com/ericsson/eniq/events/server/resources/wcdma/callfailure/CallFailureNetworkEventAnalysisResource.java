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
 * Provide event summary results for WCDMA Call Failure data
 * @author eflatib
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureNetworkEventAnalysisResource extends AbstractResource {

    private static final String NETWORK_EVENT_ANALYSIS_SERVICE = "NetworkEventAnalysisService";

    @EJB(beanName = NETWORK_EVENT_ANALYSIS_SERVICE)
    private Service networkEventAnalysisService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(NODE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNetworkEventAnalysisService() {
        return networkEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(NODE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getNetworkEventAnalysisServiceAsCSV() {
        return networkEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

}
