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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author EFLATIB
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureTerminalEventAnalysisResource extends AbstractResource {

    private static final String TERMINAL_EVENT_ANALYSIS_SERVICE = "TerminalEventAnalysisService";

    private static final String TERMINAL_FAILED_EVENT_ANALYSIS_SERVICE = "TerminalFailedEventAnalysisService";

    private static final String TAC_CALL_FAILURE_EVENT_SUMMARY_SERVICE = "TACCFAEventSummaryService";

    @EJB(beanName = TERMINAL_EVENT_ANALYSIS_SERVICE)
    private Service terminalEventAnalysisService;

    @EJB(beanName = TERMINAL_FAILED_EVENT_ANALYSIS_SERVICE)
    private Service terminalFailedEventAnalysisService;

    @EJB(beanName = TAC_CALL_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service tacCallFailureEventSummary;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Path(TERMINAL_CFA_EA_SUMMARY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTerminalEventAnalysisServiceService() {

        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        if (reqParams.containsKey(NODE_PARAM) || reqParams.containsKey(GROUP_NAME_PARAM)) {
            return terminalEventAnalysisService.getData(reqParams);
        }
        return tacCallFailureEventSummary.getData(reqParams);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getDataAsCSV()
     */
    @Path(TERMINAL_CFA_EA_SUMMARY)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getTerminalEventAnalysisServiceAsCSV() throws WebApplicationException {

        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        if (reqParams.containsKey(NODE_PARAM) || reqParams.containsKey(GROUP_NAME_PARAM)) {
            return terminalEventAnalysisService.getDataAsCSV(reqParams, response);
        }
        return tacCallFailureEventSummary.getDataAsCSV(reqParams, response);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Path("TERMINAL/DRILL_DOWN")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTerminalFailedEventAnalysisServiceService() {
        return terminalFailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getDataAsCSV()
     */
    @Path("TERMINAL/DRILL_DOWN")
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getTerminalFailedEventAnalysisServiceAsCSV() throws WebApplicationException {
        return terminalFailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
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
