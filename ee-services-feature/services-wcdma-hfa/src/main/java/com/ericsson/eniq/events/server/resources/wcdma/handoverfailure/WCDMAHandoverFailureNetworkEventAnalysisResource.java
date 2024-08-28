/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.wcdma.handoverfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.MessageConstants.*;

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
import com.ericsson.eniq.events.server.utils.json.JSONUtils;

/**
 * @author eromsza
 * @since 2011
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class WCDMAHandoverFailureNetworkEventAnalysisResource extends AbstractResource {

    private static final String CELL_HANDOVER_FAILURE_NETWORK_EVENT_ANALYSIS_SERVICE = "CellHandoverFailureNetworkEAService";

    private static final String RNC_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE = "RNCHandoverFailureEventSummaryService";

    @EJB(beanName = RNC_HANDOVER_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service rncHandoverFailureEventSummary;

    @EJB(beanName = CELL_HANDOVER_FAILURE_NETWORK_EVENT_ANALYSIS_SERVICE)
    private Service cellHandoverFailureNetworkEventAnalysisService;

    @Path(NODE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureNetworkEventAnalysis() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        final String type = requestParameters.getFirst(TYPE_PARAM);

        if (TYPE_BSC.equals(type)) {
            return rncHandoverFailureEventSummary.getData(requestParameters);
        }

        if (TYPE_CELL.equals(type)) {
            return cellHandoverFailureNetworkEventAnalysisService.getData(requestParameters);
        }

        return getErrorResponse(E_INVALID_OR_MISSING_PARAMS, "Unknown");
    }

    @Path(NODE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureNetworkEventAnalysisAsCSV() {
        final MultivaluedMap<String, String> requestParameters = mapResourceLayerParameters();
        final String type = requestParameters.getFirst(TYPE_PARAM);

        if (TYPE_BSC.equals(type)) {
            return rncHandoverFailureEventSummary.getDataAsCSV(requestParameters, response);
        }

        if (TYPE_CELL.equals(type)) {
            return cellHandoverFailureNetworkEventAnalysisService.getDataAsCSV(requestParameters, response);
        }

        throw new UnsupportedOperationException();
    }

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getData()
     */
    @Override
    public String getData() throws WebApplicationException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.ericsson.eniq.events.server.resources.AbstractResource#getDataAsCSV()
     */
    @Override
    public Response getDataAsCSV() throws WebApplicationException {
        throw new UnsupportedOperationException();
    }

    /**
     * General error response handler.
     *
     * @param message
     *          general error description
     * @param error
     *          detailed error
     * @return JSON error result object
     */
    protected String getErrorResponse(final String message, final String error) {
        return JSONUtils.createJSONErrorResult(message + (error == null || "".equals(error) ? "" : " : " + error));
    }

}
