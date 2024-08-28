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
import javax.ws.rs.core.MultivaluedMap;
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
public class WCDMAHandoverFailureTerminalGroupAnalysisResource extends AbstractResource {

    private static final String TERMINAL_GROUP_ANALYSIS_SOHO_FAILURE_SERVICE = "TerminalGASOHOFailureService";

    private static final String TERMINAL_GROUP_ANALYSIS_SOHO_DRILLDOWN_SERVICE = "TerminalGroupAnalysisSOHODrilldownService";

    private static final String TERMINAL_GROUP_ANALYSIS_IRAT_FAILURE_SERVICE = "TerminalGAIRATFailureService";

    private static final String TERMINAL_GROUP_ANALYSIS_IRAT_DRILLDOWN_SERVICE = "TerminalGroupAnalysisIRATDrilldownService";

    private static final String TERMINAL_GROUP_ANALYSIS_HSDSCH_FAILURE_SERVICE = "TerminalGAHSDSCHFailureService";

    private static final String TERMINAL_GROUP_ANALYSIS_HSDSCH_DRILLDOWN_SERVICE = "TerminalGroupAnalysisHSDSCHDrilldownService";

    private static final String TERMINAL_GROUP_ANALYSIS_IFHO_FAILURE_SERVICE = "TerminalGAIFHOFailureService";

    private static final String TERMINAL_GROUP_ANALYSIS_IFHO_DRILLDOWN_SERVICE = "TerminalGroupAnalysisIFHODrilldownService";

    @EJB(beanName = TERMINAL_GROUP_ANALYSIS_SOHO_FAILURE_SERVICE)
    private Service terminalGroupAnalysisSOHOFailureService;

    @EJB(beanName = TERMINAL_GROUP_ANALYSIS_SOHO_DRILLDOWN_SERVICE)
    private Service terminalGroupAnalysisSOHODrilldownService;

    @EJB(beanName = TERMINAL_GROUP_ANALYSIS_IRAT_FAILURE_SERVICE)
    private Service terminalGroupAnalysisIRATFailureService;

    @EJB(beanName = TERMINAL_GROUP_ANALYSIS_IRAT_DRILLDOWN_SERVICE)
    private Service terminalGroupAnalysisIRATDrilldownService;

    @EJB(beanName = TERMINAL_GROUP_ANALYSIS_HSDSCH_FAILURE_SERVICE)
    private Service terminalGroupAnalysisHSDSCHFailureService;

    @EJB(beanName = TERMINAL_GROUP_ANALYSIS_HSDSCH_DRILLDOWN_SERVICE)
    private Service terminalGroupAnalysisHSDSCHDrilldownService;

    @EJB(beanName = TERMINAL_GROUP_ANALYSIS_IFHO_FAILURE_SERVICE)
    private Service terminalGroupAnalysisIFHOFailureService;

    @EJB(beanName = TERMINAL_GROUP_ANALYSIS_IFHO_DRILLDOWN_SERVICE)
    private Service terminalGroupAnalysisIFHODrilldownService;

    @Path(MOST_SOHO_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSOHOFailrueAnalysis() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return terminalGroupAnalysisSOHODrilldownService.getData(requestParameters);
        }
        return terminalGroupAnalysisSOHOFailureService.getData(requestParameters);
    }

    @Path(MOST_SOHO_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSOHOFailureAnalysisAsCSV() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return terminalGroupAnalysisSOHODrilldownService.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        return terminalGroupAnalysisSOHOFailureService.getDataAsCSV(requestParameters, response);
    }

    @Path(MOST_IRAT_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIRATFailrueAnalysis() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return terminalGroupAnalysisIRATDrilldownService.getData(requestParameters);
        }
        return terminalGroupAnalysisIRATFailureService.getData(requestParameters);
    }

    @Path(MOST_IRAT_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getIRATFailureAnalysisAsCSV() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return terminalGroupAnalysisIRATDrilldownService.getDataAsCSV(requestParameters, response);
        }
        return terminalGroupAnalysisIRATFailureService.getDataAsCSV(requestParameters, response);
    }

    @Path(MOST_HSDSCH_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHSDSCHFailrueAnalysis() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return terminalGroupAnalysisHSDSCHDrilldownService.getData(requestParameters);
        }
        return terminalGroupAnalysisHSDSCHFailureService.getData(requestParameters);
    }

    @Path(MOST_HSDSCH_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHSDSCHFailureAnalysisAsCSV() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return terminalGroupAnalysisHSDSCHDrilldownService.getDataAsCSV(requestParameters, response);
        }
        return terminalGroupAnalysisHSDSCHFailureService.getDataAsCSV(requestParameters, response);
    }

    @Path(MOST_IFHO_FAILURES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getIFHOFailrueAnalysis() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return terminalGroupAnalysisIFHODrilldownService.getData(requestParameters);
        }
        return terminalGroupAnalysisIFHOFailureService.getData(requestParameters);
    }

    @Path(MOST_IFHO_FAILURES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getIFHOFailureAnalysisAsCSV() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        if (requestParameters.containsKey(GROUP_NAME_PARAM)) {
            return terminalGroupAnalysisIFHODrilldownService.getDataAsCSV(requestParameters, response);
        }
        return terminalGroupAnalysisIFHOFailureService.getDataAsCSV(requestParameters, response);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        // TODO Auto-generated method stub
        return null;
    }

}
