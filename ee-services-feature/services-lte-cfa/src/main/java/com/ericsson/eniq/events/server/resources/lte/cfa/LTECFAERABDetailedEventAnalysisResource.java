/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.lte.cfa;

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
 * @author echimma
 * @since 2011
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class LTECFAERABDetailedEventAnalysisResource extends AbstractResource {

    private static final String LTE_CALL_FAILURE_SUBSCRIBER_ERAB_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFASubscriberERABDetailedEAService";

    @EJB(beanName = LTE_CALL_FAILURE_SUBSCRIBER_ERAB_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureSubscriberERABDetailedEAService;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(IMSI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureSubscriberERABDetailedEventAnalysisService() {
        return lteCallFailureSubscriberERABDetailedEAService.getData(mapResourceLayerParameters());
    }

    @Path(IMSI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureSubscriberERABDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureSubscriberERABDetailedEAService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(URI_PATH_GROUP + PATH_SEPARATOR + IMSI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureSubscriberGroupERABDetailedEventAnalysisService() {
        return lteCallFailureSubscriberERABDetailedEAService.getData(mapResourceLayerParameters());
    }

    @Path(URI_PATH_GROUP + PATH_SEPARATOR + IMSI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureSubscriberGroupERABDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureSubscriberERABDetailedEAService.getDataAsCSV(mapResourceLayerParameters(), response);
    }
}
