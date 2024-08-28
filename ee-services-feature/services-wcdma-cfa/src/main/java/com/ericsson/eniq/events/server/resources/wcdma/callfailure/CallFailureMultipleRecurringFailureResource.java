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
 * Provide Data about Multiple Recurring Failures, ie failures that have occured more than once in the last 30 minutes
 * @author eeikonl
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureMultipleRecurringFailureResource extends AbstractResource {

    private static final String CALL_FAILURE_MULTIPLE_RECURRING_FAILURES = "CallFailureMultipleRecurringFailuresService";

    @EJB(beanName = CALL_FAILURE_MULTIPLE_RECURRING_FAILURES)
    private Service callFailureMultipleRecurringFailuresService;

    private static final String CALL_FAILURE_MULTIPLE_RECURRING_FAILURES_DRILLDOWN_SERVICE = "CallFailureMultipleRecurringFailuresDrilldownService";

    @EJB(beanName = CALL_FAILURE_MULTIPLE_RECURRING_FAILURES_DRILLDOWN_SERVICE)
    private Service callFailureMultipleRecurringFailuresServiceDrillOnImsiService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(CALLFAILURE_MULTIPLE_RECURRING_FAILURES_SUMMARY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureSummaryData() {
        return callFailureMultipleRecurringFailuresService.getData(mapResourceLayerParameters());
    }

    @Path(CALLFAILURE_MULTIPLE_RECURRING_FAILURES_SUMMARY)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureSummaryDataAsCSV() {
        return callFailureMultipleRecurringFailuresService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CALLFAILURE_MULTIPLE_RECURRING_FAILURES_DRILL_ON_IMSI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureDrilldown() {
        return callFailureMultipleRecurringFailuresServiceDrillOnImsiService.getData(mapResourceLayerParameters());
    }

    @Path(CALLFAILURE_MULTIPLE_RECURRING_FAILURES_DRILL_ON_IMSI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureDrilldownAsCSV() {
        return callFailureMultipleRecurringFailuresServiceDrillOnImsiService.getDataAsCSV(mapResourceLayerParameters(),
                response);
    }
}
