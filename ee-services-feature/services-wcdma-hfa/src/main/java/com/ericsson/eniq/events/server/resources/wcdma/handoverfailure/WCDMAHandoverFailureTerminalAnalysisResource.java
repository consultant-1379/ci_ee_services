/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.wcdma.handoverfailure;

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
 * @author eseuhon
 * @since 2011
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class WCDMAHandoverFailureTerminalAnalysisResource extends AbstractResource {

    private static final String TERMINAL_ANALYSIS_SOHO_FAILURE_SERVICE = "TerminalAnalysisSOHOFailureService";

    private static final String TERMINAL_ANALYSIS_IRAT_FAILURE_SERVICE = "TerminalAnalysisIRATFailureService";

    private static final String TERMINAL_ANALYSIS_HSDSCH_FAILURE_SERVICE = "TerminalAnalysisHSDSCHFailureService";

    private static final String TERMINAL_ANALYSIS_IFHO_FAILURE_SERVICE = "TerminalAnalysisIFHOFailureService";

    @EJB(beanName = TERMINAL_ANALYSIS_SOHO_FAILURE_SERVICE)
    private Service terminalAnalysisSOHOFailureService;

    @EJB(beanName = TERMINAL_ANALYSIS_IRAT_FAILURE_SERVICE)
    private Service terminalAnalysisIRATFailureService;

    @EJB(beanName = TERMINAL_ANALYSIS_HSDSCH_FAILURE_SERVICE)
    private Service terminalAnalysisHSDSCHFailureService;

    @EJB(beanName = TERMINAL_ANALYSIS_IFHO_FAILURE_SERVICE)
    private Service terminalAnalysisIFHOFailureService;

    @Path(MOST_SOHO_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSOHOFailrueAnalysis() {
        return terminalAnalysisSOHOFailureService.getData(mapResourceLayerParameters());
    }

    @Path(MOST_SOHO_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSOHOFailureAnalysisAsCSV() {
        return terminalAnalysisSOHOFailureService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(MOST_IRAT_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIRATHOFailrueAnalysis() {
        return terminalAnalysisIRATFailureService.getData(mapResourceLayerParameters());
    }

    @Path(MOST_IRAT_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getIRATHOFailureAnalysisAsCSV() {
        return terminalAnalysisIRATFailureService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(MOST_HSDSCH_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHSDSCHFailrueAnalysis() {
        return terminalAnalysisHSDSCHFailureService.getData(mapResourceLayerParameters());
    }

    @Path(MOST_HSDSCH_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHSDSCHFailureAnalysisAsCSV() {
        return terminalAnalysisHSDSCHFailureService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(MOST_IFHO_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIFHOFailrueAnalysis() {
        return terminalAnalysisIFHOFailureService.getData(mapResourceLayerParameters());
    }

    @Path(MOST_IFHO_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getIFHOFailureAnalysisAsCSV() {
        return terminalAnalysisIFHOFailureService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

}
