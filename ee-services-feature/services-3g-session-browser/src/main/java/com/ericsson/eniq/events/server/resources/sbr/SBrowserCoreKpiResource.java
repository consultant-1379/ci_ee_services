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
public class SBrowserCoreKpiResource extends AbstractResource {
    private static final String SESSION_BROWSER_CORE_CELL_KPI_SERVICE = "SBrowserCoreCellKpiService";

    private static final String SESSION_BROWSER_CORE_RNC_KPI_DRILL_SERVICE = "SBrowserCoreRncKpiDrillService";

    private static final String SESSION_BROWSER_CORE_CELL_KPI_DRILL_SERVICE = "SBrowserCoreCellKpiDrillService";

    private static final String SESSION_BROWSER_CORE_RNC_KPI_SERVICE = "SBrowserCoreRncKpiService";

    private static final String SESSION_BROWSER_CORE_NETWORK_KPI_SERVICE = "SBrowserCoreNetwkKpiService";

    private static final String SESSION_BROWSER_CORE_KPI_MAP_SERVICE = "SBrowserCoreKpiMapService";

    private static final String SESSION_BROWSER_CORE_NETWORK_KPI_DRILL_SERVICE = "SBrowserCoreNetwkKpiDrillService";

    @EJB(beanName = SESSION_BROWSER_CORE_CELL_KPI_SERVICE)
    private Service sBrowserCoreCellKpiService;

    @EJB(beanName = SESSION_BROWSER_CORE_RNC_KPI_SERVICE)
    private Service sBrowserCoreRncKpiService;

    @EJB(beanName = SESSION_BROWSER_CORE_RNC_KPI_DRILL_SERVICE)
    private Service sBrowserCoreRncKpiDrillService;

    @EJB(beanName = SESSION_BROWSER_CORE_CELL_KPI_DRILL_SERVICE)
    private Service sBrowserCoreCellKpiDrillService;

    @EJB(beanName = SESSION_BROWSER_CORE_NETWORK_KPI_SERVICE)
    private Service sBrowserCoreNetwkKpiService;

    @EJB(beanName = SESSION_BROWSER_CORE_KPI_MAP_SERVICE)
    private Service sBrowserCoreKpiMapService;

    @EJB(beanName = SESSION_BROWSER_CORE_NETWORK_KPI_DRILL_SERVICE)
    private Service sBrowserCoreNetwkKpiDrillService;

    @Path(KPI_CHART)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserKpiChartService() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (BSC.equals(type)) {
            return sBrowserCoreRncKpiService.getData(reqParams);
        } else if (CELL.equals(type)) {
            return sBrowserCoreCellKpiService.getData(reqParams);
        }
        return sBrowserCoreNetwkKpiService.getData(reqParams);
    }

    @Path(KPI_CHART)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserKpiChartServiceAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (BSC.equals(type)) {
            return sBrowserCoreRncKpiService.getDataAsCSV(reqParams, response);
        } else if (CELL.equals(type)) {
            return sBrowserCoreCellKpiService.getDataAsCSV(reqParams, response);
        }
        return sBrowserCoreNetwkKpiService.getDataAsCSV(reqParams, response);
    }

    @Path(KPI_CHART + PATH_SEPARATOR + KPI_DRILL)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserKpiDrillByTacService() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (BSC.equals(type)) {
            return sBrowserCoreRncKpiDrillService.getData(reqParams);
        } else if (CELL.equals(type)) {
            return sBrowserCoreCellKpiDrillService.getData(reqParams);
        }
        return sBrowserCoreNetwkKpiDrillService.getData(reqParams);
    }

    @Path(KPI_CHART + PATH_SEPARATOR + KPI_DRILL)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserKpiDrillByTacServiceAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (BSC.equals(type)) {
            return sBrowserCoreRncKpiDrillService.getDataAsCSV(reqParams, response);
        } else if (CELL.equals(type)) {
            return sBrowserCoreCellKpiDrillService.getDataAsCSV(reqParams, response);
        }
        return sBrowserCoreNetwkKpiDrillService.getDataAsCSV(reqParams, response);
    }

    @Path(KPI_MAP)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSBrowserKpiMapService() {
        return sBrowserCoreKpiMapService.getData(mapResourceLayerParameters());
    }

    @Path(KPI_MAP)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getSBrowserKpiMapServiceAsCSV() {
        return sBrowserCoreKpiMapService.getDataAsCSV(mapResourceLayerParameters(), response);
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
