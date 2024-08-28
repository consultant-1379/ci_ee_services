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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author EADRHYN
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureTerminalFilterAnalysisResource extends AbstractResource {

    private static final String CALL_FAILURE_TERMINAL_ANALYSIS_BY_FILTER_SERVICE = "TerminalDetailedEventAnalysisByFilterService";

    @EJB(beanName = CALL_FAILURE_TERMINAL_ANALYSIS_BY_FILTER_SERVICE)
    private Service callFailureDetailedEventByFilterAnalysis;

    @Path(TERMINAL_BY_FILTER)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDEAByNodeFilter() {
        return callFailureDetailedEventByFilterAnalysis.getData(mapResourceLayerParameters());
    }

    @Path(TERMINAL_BY_FILTER)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDEAByNodeFilterAsCSV() {
        return callFailureDetailedEventByFilterAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getData()
     */
    @Override
    public String getData() throws WebApplicationException {
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
