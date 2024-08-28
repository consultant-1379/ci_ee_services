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
public class LTECFACauseCodeAnalysisResource extends AbstractResource {

    private static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_PIE_CHART_SERVICE = "LTECFAEnodeBCauseCodePieChartService";

    private static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_SUMMARY_SERVICE = "LTECFAEnodeBCauseCodeEventSummaryService";

    private static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_LIST_SERVICE = "LTECFAEnodeBCauseCodeListService";

    private static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_PIE_CHART_SERVICE = "LTECFAEcellCauseCodePieChartService";

    private static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_SUMMARY_SERVICE = "LTECFAEcellCauseCodeEventSummaryService";

    private static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_LIST_SERVICE = "LTECFAEcellCauseCodeListService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_PIE_CHART_SERVICE = "LTECFATrackingAreaCCPieChartService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_SUMMARY_SERVICE = "LTECFATrackingAreaCCEventSummaryService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_LIST_SERVICE = "LTECFATrackingAreaCauseCodeListService";

    private static final String LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFAEnodeBCauseCodeDetailedEAService";

    private static final String LTE_CALL_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFAEcellCauseCodeDetailedEAService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE = "LTECFATrackingAreaCCDetailedEAService";

    private static final String LTE_CALL_FAILURE_CAUSE_CODE_LIST_SERVICE = "LTECFACauseCodeListService";

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_PIE_CHART_SERVICE)
    private Service lteCallFailureEnodeBCauseCodePieChartService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_SUMMARY_SERVICE)
    private Service lteCallFailureEnodeBCauseCodeEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_LIST_SERVICE)
    private Service lteCallFailureEnodeBCauseCodeListService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_CAUSE_CODE_PIE_CHART_SERVICE)
    private Service lteCallFailureEcellCauseCodePieChartService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_CAUSE_CODE_SUMMARY_SERVICE)
    private Service lteCallFailureEcellCauseCodeEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_CAUSE_CODE_LIST_SERVICE)
    private Service lteCallFailureEcellCauseCodeListService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_PIE_CHART_SERVICE)
    private Service lteCallFailureTrackingAreaCauseCodePieChartService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_SUMMARY_SERVICE)
    private Service lteCallFailureTrackingAreaCauseCodeEventSummaryService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_LIST_SERVICE)
    private Service lteCallFailureTrackingAreaCauseCodeListService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureEnodeBCauseCodeDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureEcellCauseCodeDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_CAUSE_CODE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService;

    @EJB(beanName = LTE_CALL_FAILURE_CAUSE_CODE_LIST_SERVICE)
    private Service lteCallFailureCauseCodeListService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(PIE_CHART_CC_LIST)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureCauseCodeAnalysos() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBCauseCodeListService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureEcellCauseCodeListService.getData(reqParams);
        } else {
            return lteCallFailureTrackingAreaCauseCodeListService.getData(reqParams);
        }
    }

    @Path(CAUSE_CODE_ANALYSIS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureCauseCodeAnalysis() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBCauseCodeEventSummaryService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureEcellCauseCodeEventSummaryService.getData(reqParams);
        } else {
            return lteCallFailureTrackingAreaCauseCodeEventSummaryService.getData(reqParams);
        }
    }

    @Path(CAUSE_CODE_ANALYSIS)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureCauseCodeAnalysisAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBCauseCodeEventSummaryService
                    .getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureEcellCauseCodeEventSummaryService.getDataAsCSV(mapResourceLayerParameters(), response);
        } else {
            return lteCallFailureTrackingAreaCauseCodeEventSummaryService.getDataAsCSV(mapResourceLayerParameters(),
                    response);
        }
    }

    @Path(PIE_CHART_CAUSE_CODE_ANALYSIS)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureCauseCodePieChart() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBCauseCodePieChartService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureEcellCauseCodePieChartService.getData(reqParams);
        } else {
            return lteCallFailureTrackingAreaCauseCodePieChartService.getData(reqParams);
        }
    }

    @Path(PIE_CHART_CAUSE_CODE_ANALYSIS_DRILLDOWN)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureCauseCodePieChartDrilldown() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBCauseCodeDetailedEventAnalysisService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureEcellCauseCodeDetailedEventAnalysisService.getData(reqParams);
        } else {
            return lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService.getData(reqParams);
        }
    }

    @Path(PIE_CHART_CAUSE_CODE_ANALYSIS_DRILLDOWN)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureCauseCodePieChartDrilldownAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCallFailureEnodeBCauseCodeDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(),
                    response);
        } else if (TYPE_CELL.equals(type)) {
            return lteCallFailureEcellCauseCodeDetailedEventAnalysisService.getDataAsCSV(mapResourceLayerParameters(),
                    response);
        } else {
            return lteCallFailureTrackingAreaCauseCodeDetailedEventAnalysisService.getDataAsCSV(
                    mapResourceLayerParameters(), response);
        }
    }

    @Path(LTE_CALL_FAILURE_CAUSE_CODE_TABLE_CC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCFACauseCodeList() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        return lteCallFailureCauseCodeListService.getData(reqParams);
    }
}
