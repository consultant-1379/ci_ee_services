/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
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
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class LTECFAQoSDetailedEventAnalysisResource extends AbstractResource {
    private static final String LTE_CALL_FAILURE_ECELL_QCI_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFAECellQCIDetailedEventAnalysisService";

    private static final String LTE_CALL_FAILURE_ENODEB_QCI_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFAEnodeBQCIDetailedEventAnalysisService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_QCI_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFATrackingAreaQCIDetailedEventAnalysisService";

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_QCI_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureECellQCIDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_QCI_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureEnodeBQCIDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_QCI_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureTrackingAreaQCIDetailedEventAnalysisService;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(QCI_ECELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureECellQCIDetailedEventAnalysisService() {
        return lteCallFailureECellQCIDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(QCI_ECELL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureECellQCIDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureECellQCIDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(QCI_ENODEB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEnodeBQCIDetailedEventAnalysisService() {
        return lteCallFailureEnodeBQCIDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(QCI_ENODEB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEnodeBQCIDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureEnodeBQCIDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(QCI_TRAC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureTrackingAreaQCIDetailedEventAnalysisService() {
        return lteCallFailureTrackingAreaQCIDetailedEventAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(QCI_TRAC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureTrackingAreaQCIDetailedEventAnalysisServiceAsCSV() {
        return lteCallFailureTrackingAreaQCIDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(),
                response);
    }
}
