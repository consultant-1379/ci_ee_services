/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.wcdma.handoverfailure;

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
 * @author ehaoswa
 * @since 2011
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class WCDMAHandoverFailureDetailedEventAnalysisResource extends AbstractResource {

    private static final String RNC_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "RNCSOHOHandoverFailureDetailedEAService";

    private static final String RNC_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "RNCIFHOHandoverFailureDetailedEAService";

    private static final String RNC_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "RNCIRATHandoverFailureDetailedEAService";

    private static final String RNC_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "RNCHSDSCHHandoverFailureDetailedEAService";

    private static final String TAC_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TACSOHOHandoverFailureDetailedEAService";

    private static final String TAC_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TACIFHOHandoverFailureDetailedEAService";

    private static final String TAC_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TACIRATHandoverFailureDetailedEAService";

    private static final String TAC_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TACHSDSCHHandoverFailureDetailedEAService";

    private static final String SOURCE_CELL_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "SourceCellSOHOHandoverFailureDEAService";

    private static final String SOURCE_CELL_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "SourceCellIFHOHandoverFailureDEAService";

    private static final String SOURCE_CELL_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "SourceCellIRATHandoverFailureDEAService";

    private static final String SOURCE_CELL_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "SourceCellHSDSCHHandoverFailureDEAService";

    private static final String TARGET_CELL_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TargetCellSOHOHandoverFailureDEAService";

    private static final String TARGET_CELL_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TargetCellIFHOHandoverFailureDEAService";

    private static final String TARGET_CELL_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TargetCellIRATHandoverFailureDEAService";

    private static final String TARGET_CELL_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "TargetCellHSDSCHHandoverFailureDEAService";

    private static final String IMSI_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "IMSISOHOHandoverFailureDetailedEAService";

    private static final String IMSI_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "IMSIIFHOHandoverFailureDetailedEAService";

    private static final String IMSI_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "IMSIIRATHandoverFailureDetailedEAService";

    private static final String IMSI_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE = "IMSIHSDSCHHandoverFailureDetailedEAService";

    @EJB(beanName = RNC_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service rncSOHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = RNC_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service rncIFHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = RNC_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service rncIRATHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = RNC_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service rncHSDSCHHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = SOURCE_CELL_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service sourceCellSOHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = SOURCE_CELL_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service sourceCellIFHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = SOURCE_CELL_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service sourceCellIRATHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = SOURCE_CELL_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service sourceCellHSDSCHHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = TARGET_CELL_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service targetCellSOHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = TARGET_CELL_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service targetCellIFHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = TARGET_CELL_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service targetCellIRATHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = TARGET_CELL_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service targetCellHSDSCHHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = TAC_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service tacSOHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = TAC_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service tacIFHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = TAC_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service tacIRATHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = TAC_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service tacHSDSCHHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = IMSI_SOHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service imsiSOHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = IMSI_IFHO_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service imsiIFHOHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = IMSI_IRAT_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service imsiIRATHandoverFailureDetailedEventAnalysis;

    @EJB(beanName = IMSI_HSDSCH_HANDOVER_FAILURE_DETAILED_EVENT_ANALYSIS_SERVICE)
    private Service imsiHSDSCHHandoverFailureDetailedEventAnalysis;

    @Path(NODE)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureDEAByNode() {
        final String type = mapResourceLayerParameters().getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
            if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncSOHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncIFHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncIRATHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncHSDSCHHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
            }
        } else if (TYPE_CELL.equals(type)) {
            final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
            final String cellType = mapResourceLayerParameters().getFirst(CELL_TYPE_FIXED_PARAM);
            if ("TARGET".equals(cellType)) {
                if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                    return targetCellSOHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
                } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                    return targetCellIFHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
                } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                    return targetCellIRATHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
                } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                    return targetCellHSDSCHHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
                }
            }
            if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return sourceCellSOHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return sourceCellIFHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                return sourceCellIRATHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
            } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                return sourceCellHSDSCHHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
            }
        }

        throw new UnsupportedOperationException();
    }

    @Path(NODE)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureDEAByNodeAsCSV() {
        final String type = mapResourceLayerParameters().getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
            if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncSOHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncIFHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncIRATHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
            } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                return rncHSDSCHHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(),
                        response);
            }
        } else if (TYPE_CELL.equals(type)) {
            final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
            final String cellType = mapResourceLayerParameters().getFirst(CELL_TYPE_FIXED_PARAM);
            if ("TARGET".equals(cellType)) {
                if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                    return targetCellSOHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(
                            mapResourceLayerParameters(), response);
                } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                    return targetCellIFHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(
                            mapResourceLayerParameters(), response);
                } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                    return targetCellIRATHandoverFailureDetailedEventAnalysis.getDataAsCSV(
                            mapResourceLayerParameters(), response);
                } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                    return targetCellHSDSCHHandoverFailureDetailedEventAnalysis.getDataAsCSV(
                            mapResourceLayerParameters(), response);
                }
            }
            if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return sourceCellSOHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(),
                        response);
            } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
                return sourceCellIFHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(),
                        response);
            } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
                return sourceCellIRATHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(),
                        response);
            } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
                return sourceCellHSDSCHHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(),
                        response);
            }
        }

        throw new UnsupportedOperationException();
    }

    @Path(TAC)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureDEAByTAC() {
        final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
        if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
            return tacSOHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
        } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
            return tacIFHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
        } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
            return tacIRATHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
        } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
            return tacHSDSCHHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Path(TAC)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureDEAByTACAsCSV() {
        final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
        if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
            return tacSOHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
            return tacIFHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
            return tacIRATHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
            return tacHSDSCHHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Path(IMSI)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getHandoverFailureDEAByIMSI() {
        final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
        if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
            return imsiSOHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
        } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
            return imsiIFHOHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
        } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
            return imsiIRATHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
        } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
            return imsiHSDSCHHandoverFailureDetailedEventAnalysis.getData(mapResourceLayerParameters());
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Path(IMSI)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getHandoverFailureDEAByIMSIAsCSV() {
        final String categoryIdAsString = mapResourceLayerParameters().getFirst(CATEGORY_ID_PARAM);
        if (WCDMA_HFA_SOHO_CATEGORY_ID.equals(categoryIdAsString)) {
            return imsiSOHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (WCDMA_HFA_IFHO_CATEGORY_ID.equals(categoryIdAsString)) {
            return imsiIFHOHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (WCDMA_HFA_IRAT_CATEGORY_ID.equals(categoryIdAsString)) {
            return imsiIRATHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (WCDMA_HFA_HSDSCH_CATEGORY_ID.equals(categoryIdAsString)) {
            return imsiHSDSCHHandoverFailureDetailedEventAnalysis.getDataAsCSV(mapResourceLayerParameters(), response);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

}
