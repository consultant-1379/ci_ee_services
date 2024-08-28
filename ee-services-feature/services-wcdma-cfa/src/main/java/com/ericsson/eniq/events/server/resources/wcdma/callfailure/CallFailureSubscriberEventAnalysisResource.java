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
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * Service for Call Failure Analysis Subscriber Event Analysis
 * @author EBROWPA
 *
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureSubscriberEventAnalysisResource extends AbstractResource {

    @EJB(beanName = "SubscriberEventAnalysisService")
    private Service SubscriberEventAnalysisService;

    @EJB(beanName = "SubscriberCallFailureFAforEAByImsiGroupService")
    private Service subscriberCallFailureFAforEAByImsiGroupService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Path(SUBSCRIBER_CFA_EA_SUMMARY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSubscriberCallFailureService() {
        return SubscriberEventAnalysisService.getData(mapResourceLayerParameters());
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getDataAsCSV()
     */
    @Path(SUBSCRIBER_CFA_EA_SUMMARY)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSubscriberCallFailureServiceAsCSV() throws WebApplicationException {
        return SubscriberEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Path(SUBSCRIBER_CFA_EA_DRILL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSubscriberCallFailureFAforEAByImsiGroupService() {
        return subscriberCallFailureFAforEAByImsiGroupService.getData(mapResourceLayerParameters());
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getDataAsCSV()
     */
    @Path(SUBSCRIBER_CFA_EA_DRILL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSubscriberCallFailureFAforEAByImsiGroupServiceAsCSV() throws WebApplicationException {
        return subscriberCallFailureFAforEAByImsiGroupService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

}
