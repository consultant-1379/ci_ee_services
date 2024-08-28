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
 * @author eemecoy
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class WCDMACallFailureRoamingAnalysisResource extends AbstractResource {

    private static final String CALL_FAILURE_OPERATOR_ROAMING_ANALYSIS_SERVICE = "CallFailureOperatorRoamingAnalysisService";

    private static final String CALL_FAILURE_COUNTRY_ROAMING_ANALYSIS_SERVICE = "CallFailureCountryRoamingAnalysisService";

    private static final String CALL_FAILURE_COUNTRY_DRILL_ROAMING_ANALYSIS_SERVICE = "CallFailureCountryDrillRoamingAnalysisService";

    private static final String CALL_FAILURE_OPERATOR_DRILL_ROAMING_ANALYSIS_SERVICE = "CallFailureOperatorDrillRoamingAnalysisService";

    private static final String CALL_FAILURE_COUNTRY_DRILL_DETAIL_ROAMING_ANALYSIS_SERVICE = "CallFailureCountryDrillDetailRoamingService";

    private static final String CALL_FAILURE_OPERATOR_DRILL_DETAIL_ROAMING_ANALYSIS_SERVICE = "CallFailureOperatorDrillDetailRoamingService";

    @EJB(beanName = CALL_FAILURE_OPERATOR_ROAMING_ANALYSIS_SERVICE)
    private Service operatorRoamingService;

    @EJB(beanName = CALL_FAILURE_COUNTRY_ROAMING_ANALYSIS_SERVICE)
    private Service countryRoamingService;

    @EJB(beanName = CALL_FAILURE_COUNTRY_DRILL_ROAMING_ANALYSIS_SERVICE)
    private Service callFailureCountryDrillRoamingAnalysisService;

    @EJB(beanName = CALL_FAILURE_OPERATOR_DRILL_ROAMING_ANALYSIS_SERVICE)
    private Service callFailureOperatorDrillRoamingAnalysisService;

    @EJB(beanName = CALL_FAILURE_COUNTRY_DRILL_DETAIL_ROAMING_ANALYSIS_SERVICE)
    private Service callFailureCountryDrillDetailRoamingService;

    @EJB(beanName = CALL_FAILURE_OPERATOR_DRILL_DETAIL_ROAMING_ANALYSIS_SERVICE)
    private Service callFailureOperatorDrillDetailRoamingService;

    @Path(OPERATOR)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoamingAnalysisByOperator() {
        return operatorRoamingService.getData(mapResourceLayerParameters());
    }

    @Path(COUNTRY)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoamingAnalysisByCountry() {
        return countryRoamingService.getData(mapResourceLayerParameters());
    }

    @Path(ROAMING_COUNTRY_DRILL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoamingDrillByCountry() {
        return callFailureCountryDrillRoamingAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(ROAMING_COUNTRY_DRILL_DETAIL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoamingDrillByCountryDetail() {
        return callFailureCountryDrillDetailRoamingService.getData(mapResourceLayerParameters());
    }

    @Path(ROAMING_COUNTRY_DRILL_DETAIL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getRoamingDrillByCountryDetailAsCSV() {
        return callFailureCountryDrillDetailRoamingService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(ROAMING_OPERATOR_DRILL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoamingDrillByOperator() {
        return callFailureOperatorDrillRoamingAnalysisService.getData(mapResourceLayerParameters());
    }

    @Path(ROAMING_OPERATOR_DRILL_DETAIL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoamingDrillByOperatorDetail() {
        return callFailureOperatorDrillDetailRoamingService.getData(mapResourceLayerParameters());
    }

    @Path(ROAMING_OPERATOR_DRILL_DETAIL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getRoamingDrillByOperatorDetailAsCSV() {
        return callFailureOperatorDrillDetailRoamingService.getDataAsCSV(mapResourceLayerParameters(), response);
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
