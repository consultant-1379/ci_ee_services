/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources.sbr;

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
 * @author ehaoswa
 * @since 2012
 *
 */
@Stateless
//@TransactionManagement(TransactionManagementType.BEAN)
@LocalBean
public class SBrowserUserPlaneKpiResource extends AbstractResource {

    private static final String SESSION_BROWSER_RNC_USER_PLANE_KPI_SERVICE = "SBrowserRncUkpiService";

    private static final String SESSION_BROWSER_RNC_USER_PLANE_KPI_DRILL_SERVICE = "SBrowserRncUkpiDrillService";

    private static final String SESSION_BROWSER_CELL_USER_PLANE_KPI_SERVICE = "SBrowserCellUkpiService";

    private static final String SESSION_BROWSER_CELL_USER_PLANE_KPI_DRILL_SERVICE = "SBrowserCellUkpiDrillService";

    private static final String SESSION_BROWSER_NETWORK_USER_PLANE_KPI_SERVICE = "SBrowserNetworkUkpiService";

    private static final String SESSION_BROWSER_NETWORK_USER_PLANE_KPI_DRILL_SERVICE = "SBrowserNetworkUkpiDrillService";

    private static final String SESSION_BROWSER_USER_PLANE_KPI_MAP_SERVICE = "SBrowserUKpiMapService";

    @EJB(beanName = SESSION_BROWSER_RNC_USER_PLANE_KPI_SERVICE)
    private Service sBrowserRncUkpiService;

    @EJB(beanName = SESSION_BROWSER_RNC_USER_PLANE_KPI_DRILL_SERVICE)
    private Service sBrowserRncUkpiDrillService;

    @EJB(beanName = SESSION_BROWSER_CELL_USER_PLANE_KPI_SERVICE)
    private Service sBrowserCellUkpiService;

    @EJB(beanName = SESSION_BROWSER_CELL_USER_PLANE_KPI_DRILL_SERVICE)
    private Service sBrowserCellUkpiDrillService;

    @EJB(beanName = SESSION_BROWSER_NETWORK_USER_PLANE_KPI_SERVICE)
    private Service sessionBrowserNetworkUkpiService;

    @EJB(beanName = SESSION_BROWSER_NETWORK_USER_PLANE_KPI_DRILL_SERVICE)
    private Service sessionBrowserNetworkDrillUkpiService;

    @EJB(beanName = SESSION_BROWSER_USER_PLANE_KPI_MAP_SERVICE)
    private Service sBrowserUKpiMapService;

    @Path(KPI_CHART)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserKpiChartService() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        final Service summaryService = getSummaryService(type);
        if (summaryService != null) {
            return summaryService.getData(reqParams);
        }
        return null;
    }

    private Service getSummaryService(final String type) {
        if (BSC.equals(type)) {
            return sBrowserRncUkpiService;
        }
        if (CELL.equals(type)) {
            return sBrowserCellUkpiService;
        }
        return sessionBrowserNetworkUkpiService;

    }

    @Path(KPI_CHART)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserKpiChartServiceAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        final Service summaryService = getSummaryService(type);
        if (summaryService != null) {
            return summaryService.getDataAsCSV(reqParams, response);
        }
        return null;
    }

    @Path(KPI_CHART + PATH_SEPARATOR + KPI_DRILL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserKpiDrillService() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        final Service drillService = getDrillService(type);
        if (drillService != null) {
            return drillService.getData(reqParams);
        }
        return null;
    }

    private Service getDrillService(final String type) {
        if (BSC.equals(type)) {
            return sBrowserRncUkpiDrillService;
        }
        if (CELL.equals(type)) {
            return sBrowserCellUkpiDrillService;
        }
        return sessionBrowserNetworkDrillUkpiService;
    }

    @Path(KPI_CHART + PATH_SEPARATOR + KPI_DRILL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserKpiDrillServiceAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        final Service drillService = getDrillService(type);
        if (drillService != null) {
            return drillService.getDataAsCSV(reqParams, response);
        }
        return null;
    }

    @Path(KPI_MAP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserKpiMapService() {
        return sBrowserUKpiMapService.getData(mapResourceLayerParameters());
    }

    @Path(KPI_MAP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserKpiMapServiceAsCSV() {
        return sBrowserUKpiMapService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getData()
     */
    @Override
    public String getData() throws WebApplicationException {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * @see
     * com.ericsson.eniq.events.server.resources.AbstractResource#getDataAsCSV()
     */
    @Override
    public Response getDataAsCSV() throws WebApplicationException {
        throw new UnsupportedOperationException();
    }
}
