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
import javax.ws.rs.core.MultivaluedMap;
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
public class LTECFAQosSummaryAnalysisResource extends AbstractResource {

    private static final String LTE_CALL_FAILURE_ECELL_QCI_CATEGORY_SUMMARY_SERVICE = "LTECFAECellQCICategorySummaryService";

    private static final String LTE_CALL_FAILURE_ENODEB_QCI_CATEGORY_SUMMARY_SERVICE = "LTECFAEnodeBQCICategorySummaryService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_QCI_CATEGORY_SUMMARY_SERVICE = "LTECFATrackingAreaQCICategorySummaryService";

    private static final String LTE_CALL_FAILURE_ECELL_QCI_EVENT_SUMMARY_SERVICE = "LTECFAECellQCIEventSummaryService";

    private static final String LTE_CALL_FAILURE_ENODEB_QCI_EVENT_SUMMARY_SERVICE = "LTECFAEnodeBQCIEventSummaryService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_QCI_EVENT_SUMMARY_SERVICE = "LTECFATrackingAreaQCIEventSummaryService";

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_QCI_CATEGORY_SUMMARY_SERVICE)
    private Service lteCallFailureECellQCICategorySummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_QCI_CATEGORY_SUMMARY_SERVICE)
    private Service lteCallFailureEnodeBQCICategorySummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_QCI_CATEGORY_SUMMARY_SERVICE)
    private Service lteCallFailureTrackingAreaQCICategorySummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_QCI_EVENT_SUMMARY_SERVICE)
    private Service lteCallFailureECellQCIEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_QCI_EVENT_SUMMARY_SERVICE)
    private Service lteCallFailureEnodeBQCIEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_QCI_EVENT_SUMMARY_SERVICE)
    private Service lteCallFailureTrackingAreaQCIEventSummaryService;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(ENODEB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureQCICategorySummaryEnodeB() {
        return lteCallFailureEnodeBQCICategorySummaryService.getData(mapResourceLayerParameters());
    }

    @Path(ENODEB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureQCICategorySummaryEnodeBAsCSV() {
        return lteCallFailureEnodeBQCICategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureQCICategorySummaryEcell() {
        return lteCallFailureECellQCICategorySummaryService.getData(mapResourceLayerParameters());
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureQCICategorySummaryEcellAsCSV() {
        return lteCallFailureECellQCICategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(QCI_CATEGARY_SUMMARY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureQCICategorySummaryNode() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBQCICategorySummaryService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureECellQCICategorySummaryService.getData(reqParams);
        }
        return lteCallFailureTrackingAreaQCICategorySummaryService.getData(reqParams);
    }

    @Path(QCI_CATEGARY_SUMMARY)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureQCICategorySummaryNodeAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBQCICategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureECellQCICategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        return lteCallFailureTrackingAreaQCICategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(QCI_EVENT_SUMMARY_ECELL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureECellQCIEventSummaryService() {
        return lteCallFailureECellQCIEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(QCI_EVENT_SUMMARY_ECELL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureECellQCIEventSummaryServiceAsCSV() {
        return lteCallFailureECellQCIEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(QCI_EVENT_SUMMARY_ENODEB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEnodeBQCIEventSummaryService() {
        return lteCallFailureEnodeBQCIEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(QCI_EVENT_SUMMARY_ENODEB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEnodeBQCIEventSummaryServiceAsCSV() {
        return lteCallFailureEnodeBQCIEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(QCI_EVENT_SUMMARY_TRAC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureTrackingAreaQCIEventSummaryService() {
        return lteCallFailureTrackingAreaQCIEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(QCI_EVENT_SUMMARY_TRAC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureTrackingAreaQCIEventSummaryServiceAsCSV() {
        return lteCallFailureTrackingAreaQCIEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }
}
