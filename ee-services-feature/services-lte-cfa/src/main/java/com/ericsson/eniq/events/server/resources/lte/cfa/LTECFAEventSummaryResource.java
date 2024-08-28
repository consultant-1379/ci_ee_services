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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.serviceprovider.Service;

/**
 * @author echchik
 * @since 2011
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class LTECFAEventSummaryResource extends AbstractResource {

    private static final String LTE_CALL_FAILURE_SUBSCRIBER_EVENT_SUMMARY_SERVICE = "LTECFASubscriberEventSummaryService";

    private static final String LTE_CALL_FAILURE_ENODEB_EVENT_SUMMARY_SERVICE = "LTECFAEnodeBEventSummaryService";

    private static final String LTE_CALL_FAILURE_ECELL_EVENT_SUMMARY_SERVICE = "LTECFAECellEventSummaryService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_EVENT_SUMMARY_SERVICE = "LTECFATrackingAreaEventSummaryService";

    private static final String LTE_CALL_FAILURE_ENODEB_CATEGORY_SUMMARY_SERVICE = "LTECFAEnodeBCategorySummaryService";

    private static final String LTE_CALL_FAILURE_ECELL_CATEGORY_SUMMARY_SERVICE = "LTECFAEcellCategorySummaryService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_CATEGORY_SUMMARY_SERVICE = "LTECFATrackingAreaCategorySummaryService";

    private static final String LTE_CALL_FAILURE_SUBSCRIBER_CATEGORY_SUMMARY_SERVICE = "LTECFASubscriberCategorySummaryService";

    private static final String LTE_CALL_FAILURE_TERMINAL_CATEGORY_SUMMARY_SERVICE = "LTECFATerminalCategorySummaryService";

    private static final String LTE_CALL_FAILURE_TERMINAL_EVENT_SUMMARY_SERVICE = "LTECFATerminalEventSummaryService";

    @EJB(beanName = LTE_CALL_FAILURE_SUBSCRIBER_EVENT_SUMMARY_SERVICE)
    private Service lteCallFailureSubscriberEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_EVENT_SUMMARY_SERVICE)
    private Service lteCallFailureEnodeBEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_EVENT_SUMMARY_SERVICE)
    private Service lteCallFailureECellEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_EVENT_SUMMARY_SERVICE)
    private Service lteCallFailureTrackingAreaEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_CATEGORY_SUMMARY_SERVICE)
    private Service lteCallFailureEnodeBCategorySummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_CATEGORY_SUMMARY_SERVICE)
    private Service lteCallFailureEcellCategorySummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_CATEGORY_SUMMARY_SERVICE)
    private Service lteCallFailureTrackingAreaCategorySummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_SUBSCRIBER_CATEGORY_SUMMARY_SERVICE)
    private Service lteCallFailureSubscriberCategorySummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_TERMINAL_CATEGORY_SUMMARY_SERVICE)
    private Service lteCfaTerminalCategorySummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_TERMINAL_EVENT_SUMMARY_SERVICE)
    private Service lteCfaTerminalEventSummaryService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(IMSI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureCategorySummarySubscriber() {
        return lteCallFailureSubscriberCategorySummaryService.getData(mapResourceLayerParameters());
    }

    @Path(IMSI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureCategorySummarySubscriberAsCSV() {
        return lteCallFailureSubscriberCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CATEGORISED_IMSI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEventSummarySubscriber() {
        return lteCallFailureSubscriberEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(CATEGORISED_IMSI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEventSummarySubscriberAsCSV() {
        return lteCallFailureSubscriberEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ENODEB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureCategorySummaryEnodeB() {
        return lteCallFailureEnodeBCategorySummaryService.getData(mapResourceLayerParameters());
    }

    @Path(ENODEB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureCategorySummaryEnodeBAsCSV() {
        return lteCallFailureEnodeBCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CATEGORISED_ENODEB)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEventSummaryEnodeB() {
        return lteCallFailureEnodeBEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(CATEGORISED_ENODEB)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEventSummaryEnodeBAsCSV() {
        return lteCallFailureEnodeBEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEventSummaryECell() {
        return lteCallFailureEcellCategorySummaryService.getData(mapResourceLayerParameters());
    }

    @Path(ACCESS_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEventSummaryECellAsCSV() {
        return lteCallFailureEcellCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CATEGORISED_ACCESS_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEventSummaryEcell() {
        return lteCallFailureECellEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(CATEGORISED_ACCESS_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEventSummaryEcellAsCSV() {
        return lteCallFailureECellEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(NODE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEventSummaryEnodeBAndECell() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBCategorySummaryService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureEcellCategorySummaryService.getData(reqParams);
        } else if (TYPE_TAC.equals(type)) {
            return lteCfaTerminalCategorySummaryService.getData(reqParams);
        }
        return lteCallFailureTrackingAreaCategorySummaryService.getData(reqParams);
    }

    @Path(NODE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEventSummaryEnodeBAndECellAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureEcellCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (TYPE_TAC.equals(type)) {
            return lteCfaTerminalCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        return lteCallFailureTrackingAreaCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TRACKING_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureCategorySummaryTrackingArea() {
        return lteCallFailureTrackingAreaCategorySummaryService.getData(mapResourceLayerParameters());
    }

    @Path(TRACKING_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureCategorySummaryTrackingAreaAsCSV() {
        return lteCallFailureTrackingAreaCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CATEGORISED_TRACKING_AREA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEventSummaryTrackingArea() {
        return lteCallFailureTrackingAreaEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(CATEGORISED_TRACKING_AREA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEventSummaryTrackingAreaAsCSV() {
        return lteCallFailureTrackingAreaEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(TERMINAL_SERVICES)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureCategorySummaryTerminal() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        if (reqParams.containsKey(NODE_PARAM) || reqParams.containsKey(GROUP_NAME_PARAM)
                || reqParams.containsKey(TAC_PARAM) || reqParams.containsKey(TAC_PARAM_UPPER_CASE)) {
            return lteCfaTerminalCategorySummaryService.getData(mapResourceLayerParameters());
        }
        return lteCfaTerminalEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(TERMINAL_SERVICES)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureCategorySummaryTerminalAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        if (reqParams.containsKey(NODE_PARAM) || reqParams.containsKey(GROUP_NAME_PARAM)
                || reqParams.containsKey(TAC_PARAM) || reqParams.containsKey(TAC_PARAM_UPPER_CASE)) {
            return lteCfaTerminalCategorySummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        return lteCfaTerminalEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(CATEGORISED_TERMINAL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEventSummaryTerminal() {
        return lteCfaTerminalEventSummaryService.getData(mapResourceLayerParameters());
    }

    @Path(CATEGORISED_TERMINAL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEventSummaryTerminalAsCSV() {
        return lteCfaTerminalEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
    }
}
