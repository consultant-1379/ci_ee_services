package com.ericsson.eniq.events.server.resources.wcdma.handoverfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.MessageConstants.*;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.ericsson.eniq.events.server.common.MediaTypeConstants;
import com.ericsson.eniq.events.server.resources.AbstractResource;
import com.ericsson.eniq.events.server.resources.wcdma.handoverfailure.beans.CCHttpPostRequest;
import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.utils.json.JSONUtils;

/**
 *
 * @author eromsza
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class WCDMAHandoverFailureCauseCodeAnalysisResource extends AbstractResource {

    private static final String CONTROLLER_CAUSE_CODE_ANALYSIS_LIST_SERVICE = "WCDMAHandoverFailureCauseCodeListService";

    private static final String CONTROLLER_CAUSE_CODE_ANALYSIS_SERVICE = "WCDMAHandoverFailureCauseCodeService";

    private static final String CAUSE_CODE_TABLE = "WCDMAHandoverFailureCauseCodeTableService";

    private static final String SUB_CAUSE_CODE_TABLE = "WCDMAHandoverFailureSubCauseCodeTableService";

    private static final String CONTROLLER_SUB_CAUSE_CODE_ANALYSIS_SERVICE = "WCDMAHandoverFailureSubCauseCodeService";

    private static final String CC_RNC_SOHO_HANDOVER_FAILURE_DEA_SERVICE = "CCRNCSOHOHandoverFailureDEAService";

    private static final String CC_RNC_IFHO_HANDOVER_FAILURE_DEA_SERVICE = "CCRNCIFHOHandoverFailureDEAService";

    private static final String CC_RNC_IRAT_HANDOVER_FAILURE_DEA_SERVICE = "CCRNCIRATHandoverFailureDEAService";

    private static final String CC_RNC_HSDSCH_HANDOVER_FAILURE_DEA_SERVICE = "CCRNCHSDSCHHandoverFailureDEAService";

    private static final String CC_CELL_SOHO_HANDOVER_FAILURE_DEA_SERVICE = "CCCellSOHOHandoverFailureDEAService";

    private static final String CC_CELL_IFHO_HANDOVER_FAILURE_DEA_SERVICE = "CCCellIFHOHandoverFailureDEAService";

    private static final String CC_CELL_IRAT_HANDOVER_FAILURE_DEA_SERVICE = "CCCellIRATHandoverFailureDEAService";

    private static final String CC_CELL_HSDSCH_HANDOVER_FAILURE_DEA_SERVICE = "CCCellHSDSCHHandoverFailureDEAService";

    @EJB(beanName = CONTROLLER_CAUSE_CODE_ANALYSIS_LIST_SERVICE)
    private Service wcdmaHandoverFailureControllerCauseCodeListService;

    @EJB(beanName = CONTROLLER_CAUSE_CODE_ANALYSIS_SERVICE)
    private Service wcdmaHandoverFailureControllerCauseCodeService;

    @EJB(beanName = CONTROLLER_SUB_CAUSE_CODE_ANALYSIS_SERVICE)
    private Service wcdmaHandoverFailureSubCauseCodeService;

    @EJB(beanName = CC_RNC_SOHO_HANDOVER_FAILURE_DEA_SERVICE)
    private Service rncSOHOHandoverFailureDEAService;

    @EJB(beanName = CC_RNC_IFHO_HANDOVER_FAILURE_DEA_SERVICE)
    private Service rncIFHOHandoverFailureDEAService;

    @EJB(beanName = CC_RNC_IRAT_HANDOVER_FAILURE_DEA_SERVICE)
    private Service rncIRATHandoverFailureDEAService;

    @EJB(beanName = CC_RNC_HSDSCH_HANDOVER_FAILURE_DEA_SERVICE)
    private Service rncHSDSCHHandoverFailureDEAService;

    @EJB(beanName = CC_CELL_SOHO_HANDOVER_FAILURE_DEA_SERVICE)
    private Service cellSOHOHandoverFailureDEAService;

    @EJB(beanName = CC_CELL_IFHO_HANDOVER_FAILURE_DEA_SERVICE)
    private Service cellIFHOHandoverFailureDEAService;

    @EJB(beanName = CC_CELL_IRAT_HANDOVER_FAILURE_DEA_SERVICE)
    private Service cellIRATHandoverFailureDEAService;

    @EJB(beanName = CC_CELL_HSDSCH_HANDOVER_FAILURE_DEA_SERVICE)
    private Service cellHSDSCHHandoverFailureDEAService;

    @EJB(beanName = CAUSE_CODE_TABLE)
    private Service wcdmaHandoverFailureCauseCodeTableService;

    @EJB(beanName = SUB_CAUSE_CODE_TABLE)
    private Service wcdmaHandoverFailureSubCauseCodeTableService;

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(CC_LIST)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCauseCodeList() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaHandoverFailureControllerCauseCodeListService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return wcdmaHandoverFailureControllerCauseCodeListService.getData(reqParams);
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(CC_LIST)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCauseCodeListAsCSV() {
        throw new UnsupportedOperationException();
    }

    @Path(CAUSE_CODE_GRID)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCauseCodeGrid() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaHandoverFailureControllerCauseCodeService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return wcdmaHandoverFailureControllerCauseCodeService.getData(reqParams);
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(CAUSE_CODE_GRID)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCauseCodeGridAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);

        if (TYPE_BSC.equals(type)) {
            return wcdmaHandoverFailureControllerCauseCodeService.getDataAsCSV(reqParams, response);
        } else if (TYPE_CELL.equals(type)) {
            return wcdmaHandoverFailureControllerCauseCodeService.getDataAsCSV(reqParams, response);
        }
        throw new UnsupportedOperationException();
    }

    @Path(CAUSE_CODE_PIE_CHART)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureCauseCodeFromPieChart(final CCHttpPostRequest causeCodeBean) {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();

        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            reqParams.add(CAUSE_CODE_LABEL_LIST_SOURCE,
                    createCauseCodeLabelList(causeCodeBean, WCDMA_HFA_SOURCE_GROUP_ID));
            return wcdmaHandoverFailureControllerCauseCodeService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            reqParams.add(CAUSE_CODE_LABEL_LIST_SOURCE,
                    createCauseCodeLabelList(causeCodeBean, WCDMA_HFA_SOURCE_GROUP_ID));
            reqParams.add(CAUSE_CODE_LABEL_LIST_TARGET,
                    createCauseCodeLabelList(causeCodeBean, WCDMA_HFA_TARGET_GROUP_ID));
            return wcdmaHandoverFailureControllerCauseCodeService.getData(reqParams);
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(CAUSE_CODE_PIE_CHART)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureCauseCodePieChartAsCSV() {
        throw new UnsupportedOperationException();
    }

    @Path(SUB_CAUSE_CODE_GRID)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureSubCauseCodeAnalysisGrid() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaHandoverFailureSubCauseCodeService.getData(mapResourceLayerParameters());

        } else if (TYPE_CELL.equals(type)) {
            return wcdmaHandoverFailureSubCauseCodeService.getData(mapResourceLayerParameters());
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(SUB_CAUSE_CODE_GRID)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureSubCauseCodeAnalysisGridAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaHandoverFailureSubCauseCodeService.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (TYPE_CELL.equals(type)) {

            return wcdmaHandoverFailureSubCauseCodeService.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        throw new UnsupportedOperationException();
    }

    @Path(SUB_CAUSE_CODE_PIE_CHART)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureSubCauseCodePieChart() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaHandoverFailureSubCauseCodeService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return wcdmaHandoverFailureSubCauseCodeService.getData(reqParams);
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(SUB_CAUSE_CODE_PIE_CHART)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureSubCauseCodePieChartAsCSV() {
        throw new UnsupportedOperationException();
    }

    @Path(CAUSE_CODE_TABLE_CC_WCDMA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCauseCodeTable() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        return wcdmaHandoverFailureCauseCodeTableService.getData(reqParams);
    }

    @Path(CAUSE_CODE_TABLE_CC_WCDMA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCauseCodeTableAsCSV() {
        throw new UnsupportedOperationException();
    }

    @Path(CAUSE_CODE_TABLE_SCC_WCDMA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSubCauseCodeTable() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        return wcdmaHandoverFailureSubCauseCodeTableService.getData(reqParams);
    }

    @Path(CAUSE_CODE_TABLE_SCC_WCDMA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSubCauseCodeTableAsCSV() {
        throw new UnsupportedOperationException();
    }

    @Path(DETAIL_SUB_CAUSE_CODE_GRID)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureSubCauseCodeDetailedGrid() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
            if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncSOHOHandoverFailureDEAService.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncIFHOHandoverFailureDEAService.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncIRATHandoverFailureDEAService.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncHSDSCHHandoverFailureDEAService.getData(mapResourceLayerParameters());
            }
        } else if (TYPE_CELL.equals(type)) {
            final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
            if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return cellSOHOHandoverFailureDEAService.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return cellIFHOHandoverFailureDEAService.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                return cellIRATHandoverFailureDEAService.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                return cellHSDSCHHandoverFailureDEAService.getData(mapResourceLayerParameters());
            }
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(DETAIL_SUB_CAUSE_CODE_GRID)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureSubCauseCodeDetailedGridAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
            if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncSOHOHandoverFailureDEAService.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncIFHOHandoverFailureDEAService.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncIRATHandoverFailureDEAService.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncHSDSCHHandoverFailureDEAService.getDataAsCSV(mapResourceLayerParameters(), response);
            }
        } else if (TYPE_CELL.equals(type)) {
            final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
            if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return cellSOHOHandoverFailureDEAService.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return cellIFHOHandoverFailureDEAService.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                return cellIRATHandoverFailureDEAService.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                return cellHSDSCHHandoverFailureDEAService.getDataAsCSV(mapResourceLayerParameters(), response);
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Generate comma separated string of cause code labels from JSON HTTP POST request for WCDMA HFA feature.
     *
     * @param CCHttpPostRequest causeCodeBean JSON HTTP POST request for WCDMA HFA feature in the bean form.
     * @param String groupId String representation of groupId for Cell Cause Code analysis or null for RNC Cause Code analysis.
     *
     * @return String Comma separated string of single quoted cause code labels.
     */
    private final String createCauseCodeLabelList(final CCHttpPostRequest causeCodeBean, final String groupId) {
        if (causeCodeBean != null) {
            final StringBuilder requestParameterBuilder = new StringBuilder();
            for (int elementIndex = 0; elementIndex < causeCodeBean.getSelected().length - 1; elementIndex++) {
                if (groupId.equals(causeCodeBean.getSelected()[elementIndex].get3())) {
                    if (requestParameterBuilder.length() > 0) {
                        requestParameterBuilder.append(COMMA);
                    }
                    requestParameterBuilder.append(QUOTE_SINGLE)
                            .append(causeCodeBean.getSelected()[elementIndex].get1()).append(QUOTE_SINGLE);
                }
            }
            if (groupId.equals(causeCodeBean.getSelected()[causeCodeBean.getSelected().length - 1].get3())) {
                if (requestParameterBuilder.length() > 0) {
                    requestParameterBuilder.append(COMMA);
                }
                requestParameterBuilder.append(QUOTE_SINGLE)
                        .append(causeCodeBean.getSelected()[causeCodeBean.getSelected().length - 1].get1())
                        .append(QUOTE_SINGLE);
            }
            if (requestParameterBuilder.length() == 0) {
                requestParameterBuilder.append(QUOTE_SINGLE).append(QUOTE_SINGLE);
            }

            return requestParameterBuilder.toString();
        }

        throw new UnsupportedOperationException();
    }

}