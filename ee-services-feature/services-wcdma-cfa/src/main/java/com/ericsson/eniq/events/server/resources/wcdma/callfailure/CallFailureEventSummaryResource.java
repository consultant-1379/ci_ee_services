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
 * @author eemecoy
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureEventSummaryResource extends AbstractResource {

    private static final String RNC_CALL_FAILURE_EVENT_SUMMARY_SERVICE = "RNCCallFailureEventSummaryService";

    private static final String ACCESS_AREA_CALL_FAILURE_EVENT_SUMMARY_SERVICE = "AccessAreaCallFailureEventSummaryService";

    @EJB(beanName = RNC_CALL_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service rncCallFailureEventSummaryService;

    @EJB(beanName = ACCESS_AREA_CALL_FAILURE_EVENT_SUMMARY_SERVICE)
    private Service accessAreaCallFailureEventSummaryService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(RNC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureEventSummaryRNC() {
        return rncCallFailureEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(RNC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureEventSummaryRNCAsCSV() {
        return rncCallFailureEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureEventSummaryAccessArea() {
        return accessAreaCallFailureEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureEventSummaryAccessAreaAsCSV() {
        return accessAreaCallFailureEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

}
