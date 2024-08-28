/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.EventIDConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * Resource for the WCDMA CFA terminal group query
 * Due to limitations in the UI, the same URI handles both the main terminal group query, and the subsequent drilldown
 * 
 * @author EEMECOY
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureTerminalGroupAnalysisResource extends AbstractResource {

    private static final String CALL_FAILURE_TERMINAL_GROUP_ANALYSIS_SERVICE = "CallFailureTerminalGroupAnalysisService";

    @EJB(beanName = CALL_FAILURE_TERMINAL_GROUP_ANALYSIS_SERVICE)
    private Service service;

    @Path(MOST_CALL_DROPS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMostCallDrops() {
        final MultivaluedMap<String, String> serviceProviderParameters = mapResourceLayerParameters();
        serviceProviderParameters.putSingle(EVENT_ID, CALL_DROP_EVENT_ID);
        return service.getData(serviceProviderParameters);
    }

    @Path(MOST_CALL_DROPS)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getMostCallDropsCSV() {
        final MultivaluedMap<String, String> serviceProviderParameters = mapResourceLayerParameters();
        serviceProviderParameters.putSingle(EVENT_ID, CALL_DROP_EVENT_ID);
        return service.getDataAsCSV(serviceProviderParameters, response);
    }

    @Path(MOST_CALL_SETUP_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMostCallSetupFailures() {
        final MultivaluedMap<String, String> serviceProviderParameters = mapResourceLayerParameters();
        serviceProviderParameters.putSingle(EVENT_ID, CALL_SETUP_FAILURE_EVENT_ID);
        return service.getData(serviceProviderParameters);
    }

    @Path(MOST_CALL_SETUP_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getMostCallSetupFailuresCSV() {
        final MultivaluedMap<String, String> serviceProviderParameters = mapResourceLayerParameters();
        serviceProviderParameters.putSingle(EVENT_ID, CALL_SETUP_FAILURE_EVENT_ID);
        return service.getDataAsCSV(serviceProviderParameters, response);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getDataAsCSV()
     */
    @Override
    public Response getDataAsCSV() throws WebApplicationException {
        throw new UnsupportedOperationException();
    }

}
