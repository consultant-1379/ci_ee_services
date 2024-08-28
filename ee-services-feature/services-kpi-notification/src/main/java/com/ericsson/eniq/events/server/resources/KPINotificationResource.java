/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

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
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * 
 * This service returns kpi notifications data for the UI widgets from Sybase IQ. 
 * 
 * @author echavch
 * @since 2011
 *
 */

@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class KPINotificationResource extends AbstractResource {

    @EJB(beanName = "KPINotificationCountService")
    private Service kpiNotificationCountService;

    @EJB(beanName = "KPINotificationDataService")
    private Service kpiNotificationDataService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(NOTIFICATION_DATA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNotificationData() {
        return kpiNotificationDataService.getData(mapResourceLayerParameters());
    }

    @Path(NOTIFICATION_DATA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getNotificationDataAsCSV() {
        return kpiNotificationDataService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(NOTIFICATION_COUNT)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNotificationCount() {
        return kpiNotificationCountService.getData(mapResourceLayerParameters());
    }
}
