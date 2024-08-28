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
 * Service for Call Failure Analysis Subscriber Overview
 * @author EEMECOY
 *
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class CallFailureSubsessionBIResource extends AbstractResource {

    @EJB(beanName = "CallFailureSubsessionBIService")
    private Service callFailureSubsessionFailureService;

    @EJB(beanName = "CallFailureSubsessionBIDrilldownService")
    private Service callFailureSubsessionDetailedFailureService;

    @EJB(beanName = "CallFailureSubscriberDetailsService")
    private Service callFailureSubscriberDetailsService;

    @Path(SUBBI_FAILURE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSubBIFailureData() {
        final MultivaluedMap<String, String> parameters = mapResourceLayerParameters();
        if (parameters.getFirst(EVENT_PARAM) != null) {
            return callFailureSubsessionDetailedFailureService.getData(parameters);
        }
        return callFailureSubsessionFailureService.getData(parameters);
    }

    @Path(SUBBI_FAILURE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSubBIFailureDataAsCSV() {
        final MultivaluedMap<String, String> parameters = mapResourceLayerParameters();
        if (parameters.getFirst(EVENT_PARAM) != null) {
            return callFailureSubsessionDetailedFailureService.getDataAsCSV(parameters, response);
        }
        return callFailureSubsessionFailureService.getDataAsCSV(parameters, response);
    }

    @Path(SUBBI_SUBSCRIBER_DETAILS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSubBISubscriberDetails() {
        return callFailureSubscriberDetailsService.getData(mapResourceLayerParameters());
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
