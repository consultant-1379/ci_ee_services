package com.ericsson.eniq.events.server.resources.wcdma.callfailure;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.MessageConstants.*;

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
import com.ericsson.eniq.events.server.utils.json.JSONUtils;

/**
 *
 * @author epesmit
 * @since 2011
 *
 */

@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class WCDMACallFailureCauseCodeAnalysisResource extends AbstractResource {

    private static final String CONTROLLER_CAUSE_CODE_ANALYSIS_LIST_SERVICE = "ControllerCauseCodeListService";

    private static final String CONTROLLER_CAUSE_CODE_ANALYSIS_SERVICE = "ControllerCauseCodeService";

    private static final String CAUSE_CODE_TABLE = "ControllerCauseCodeTableService";

    private static final String SUB_CAUSE_CODE_TABLE = "ControllerSubCauseCodeTableService";

    private static final String CONTROLLER_SUB_CAUSE_CODE_ANALYSIS_SERVICE = "ControllerSubCauseCodeService";

    private static final String CONTROLLER_SUB_CAUSE_CODE_DEA_SERVICE = "ControllerSubCauseCodeDEAService";

    private static final String ACCESS_AREA_CAUSE_CODE_ANALYSIS_LIST_SERVICE = "AccessAreaCauseCodeListService";

    private static final String ACCESS_AREA_CAUSE_CODE_GROUP_ANALYSIS_LIST_SERVICE = "AccessAreaGroupCauseCodeListService";

    private static final String ACCESS_AREA_CAUSE_CODE_ANALYSIS_SERVICE = "AccessAreaCauseCodeService";

    private static final String ACCESS_AREA_SUB_CAUSE_CODE_ANALYSIS_SERVICE = "AccessAreaSubCauseCodeService";

    private static final String ACCESS_AREA_CAUSE_CODE_GROUP_ANALYSIS_SERVICE = "AccessAreaGroupCauseCodeService";

    private static final String ACCESS_AREA_SUB_CAUSE_CODE_DEA_SERVICE = "AccessAreaSubCauseCodeDEAService";

    private static final String ACCESS_AREA_SUB_CAUSE_CODE_GROUP_SERVICE = "AccessAreaGroupSubCauseCodeService";

    private static final String ACCESS_AREA_GROUP_SUB_CAUSE_CODE_DEA_SERVICE = "AccessAreaGroupSubCauseCodeDEAService";

    @EJB(beanName = CONTROLLER_CAUSE_CODE_ANALYSIS_LIST_SERVICE)
    private Service wcdmaCallFailureControllerCauseCodeListService;

    @EJB(beanName = CONTROLLER_CAUSE_CODE_ANALYSIS_SERVICE)
    private Service wcdmaCallFailureControllerCauseCodeService;

    @EJB(beanName = CONTROLLER_SUB_CAUSE_CODE_ANALYSIS_SERVICE)
    private Service wcdmaCallFailureSubCauseCodeService;

    @EJB(beanName = CONTROLLER_SUB_CAUSE_CODE_DEA_SERVICE)
    private Service wcdmaCallFailureControllerSubCauseCodeDEAService;

    @EJB(beanName = CAUSE_CODE_TABLE)
    private Service wcdmaCallFailureCauseCodeTableService;

    @EJB(beanName = SUB_CAUSE_CODE_TABLE)
    private Service wcdmaCallFailureSubCauseCodeTableService;

    @EJB(beanName = ACCESS_AREA_CAUSE_CODE_ANALYSIS_LIST_SERVICE)
    private Service accessAreaCauseCodeListService;

    @EJB(beanName = ACCESS_AREA_CAUSE_CODE_GROUP_ANALYSIS_LIST_SERVICE)
    private Service accessAreaGroupCauseCodeListService;

    @EJB(beanName = ACCESS_AREA_CAUSE_CODE_ANALYSIS_SERVICE)
    private Service wcdmaCallFailureAccessAreaCauseCodeService;

    @EJB(beanName = ACCESS_AREA_SUB_CAUSE_CODE_ANALYSIS_SERVICE)
    private Service wcdmaCallFailureAccessAreaSubCauseCodeService;

    @EJB(beanName = ACCESS_AREA_SUB_CAUSE_CODE_DEA_SERVICE)
    private Service wcdmaCallFailureAccessAreaSubCauseCodeDEAService;

    @EJB(beanName = ACCESS_AREA_GROUP_SUB_CAUSE_CODE_DEA_SERVICE)
    private Service accessAreaGroupSubCauseCodeDEAService;

    @EJB(beanName = ACCESS_AREA_CAUSE_CODE_GROUP_ANALYSIS_SERVICE)
    private Service accessAreaGroupCauseCodeService;

    @EJB(beanName = ACCESS_AREA_SUB_CAUSE_CODE_GROUP_SERVICE)
    private Service accessAreaGroupSubCauseCodeService;

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
            return wcdmaCallFailureControllerCauseCodeListService.getData(reqParams);
        }
        if (TYPE_CELL.equals(type)) {
            if (isAccessAreaGroup(reqParams)) {
                return accessAreaGroupCauseCodeListService.getData(reqParams);
            }
            return accessAreaCauseCodeListService.getData(reqParams);
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(CC_LIST)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCauseCodeListAsCSV() {
        throw new UnsupportedOperationException();
    }

    @Path(CAUSE_CODE_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCauseCode() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaCallFailureControllerCauseCodeService.getData(reqParams);
        }
        if (TYPE_CELL.equals(type)) {
            if (isAccessAreaGroup(reqParams)) {
                return accessAreaGroupCauseCodeService.getData(reqParams);
            }
            return wcdmaCallFailureAccessAreaCauseCodeService.getData(reqParams);
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(CAUSE_CODE_PATH)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCauseCodeGridAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaCallFailureControllerCauseCodeService.getDataAsCSV(reqParams, response);
        }
        if (TYPE_CELL.equals(type)) {
            if (isAccessAreaGroup(reqParams)) {
                return accessAreaGroupCauseCodeService.getDataAsCSV(reqParams, response);
            }
            return wcdmaCallFailureAccessAreaCauseCodeService.getDataAsCSV(reqParams, response);
        }
        throw new UnsupportedOperationException("Method not supported for type " + type);
    }

    @Path(SUB_CAUSE_CODE_PATH)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureSubCauseCodeAnalysis() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaCallFailureSubCauseCodeService.getData(mapResourceLayerParameters());
        }
        if (TYPE_CELL.equals(type)) {
            if (isAccessAreaGroup(reqParams)) {
                return accessAreaGroupSubCauseCodeService.getData(reqParams);
            }
            return wcdmaCallFailureAccessAreaSubCauseCodeService.getData(mapResourceLayerParameters());
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(SUB_CAUSE_CODE_PATH)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureSubCauseCodeAnalysisAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaCallFailureSubCauseCodeService.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        if (TYPE_CELL.equals(type)) {
            if (isAccessAreaGroup(reqParams)) {
                return accessAreaGroupSubCauseCodeService.getDataAsCSV(mapResourceLayerParameters(), response);
            }
            return wcdmaCallFailureAccessAreaSubCauseCodeService.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        throw new UnsupportedOperationException();
    }

    @Path(DETAIL_SUB_CAUSE_CODE_GRID)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCallFailureSubCauseCodeDetailedGrid() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaCallFailureControllerSubCauseCodeDEAService.getData(reqParams);
        }
        if (TYPE_CELL.equals(type)) {
            if (isAccessAreaGroup(reqParams)) {
                return accessAreaGroupSubCauseCodeDEAService.getData(reqParams);
            }
            return wcdmaCallFailureAccessAreaSubCauseCodeDEAService.getData(reqParams);
        }
        return JSONUtils.createJSONErrorResult(E_INVALID_OR_MISSING_PARAMS);
    }

    @Path(DETAIL_SUB_CAUSE_CODE_GRID)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getCallFailureSubCauseCodeDetailedGridAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return wcdmaCallFailureControllerSubCauseCodeDEAService
                    .getDataAsCSV(mapResourceLayerParameters(), response);
        }
        if (TYPE_CELL.equals(type)) {
            if (isAccessAreaGroup(reqParams)) {
                return accessAreaGroupSubCauseCodeDEAService.getDataAsCSV(reqParams, response);
            }
            return wcdmaCallFailureAccessAreaSubCauseCodeDEAService.getDataAsCSV(reqParams, response);
        }
        throw new UnsupportedOperationException();
    }

    @Path(CAUSE_CODE_TABLE_CC_WCDMA)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCauseCodeTable() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        return wcdmaCallFailureCauseCodeTableService.getData(reqParams);
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
        return wcdmaCallFailureSubCauseCodeTableService.getData(reqParams);
    }

    @Path(CAUSE_CODE_TABLE_SCC_WCDMA)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSubCauseCodeTableAsCSV() {
        throw new UnsupportedOperationException();
    }

    private boolean isAccessAreaGroup(final MultivaluedMap<String, String> reqParams) {
        final String groupname = reqParams.getFirst(GROUP_NAME_PARAM);
        if (groupname != null) {
            return true;
        }
        return false;
    }

}
