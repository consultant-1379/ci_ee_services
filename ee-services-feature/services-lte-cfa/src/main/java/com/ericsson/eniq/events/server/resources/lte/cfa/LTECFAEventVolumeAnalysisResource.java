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
public class LTECFAEventVolumeAnalysisResource extends AbstractResource {

    private static final String LTE_CALL_FAILURE_NETWORK_EVENT_VOLUME_SERVICE = "LTECFANetworkEventVolumeService";

    private static final String LTE_CALL_FAILURE_ECELL_EVENT_VOLUME_SERVICE = "LTECFAEcellEventVolumeService";

    private static final String LTE_CALL_FAILURE_ENODEB_EVENT_VOLUME_SERVICE = "LTECFAEnodeBEventVolumeService";

    private static final String LTE_CALL_FAILURE_TRACKING_AREA_EVENT_VOLUME_SERVICE = "LTECFATrackingAreaEventVolumeService";

    @EJB(beanName = LTE_CALL_FAILURE_NETWORK_EVENT_VOLUME_SERVICE)
    private Service lteCFANetworkEventVolumeService;

    @EJB(beanName = LTE_CALL_FAILURE_ECELL_EVENT_VOLUME_SERVICE)
    private Service lteCFAEcellEventVolumeService;

    @EJB(beanName = LTE_CALL_FAILURE_ENODEB_EVENT_VOLUME_SERVICE)
    private Service lteCFAEnodeBEventVolumeService;

    @EJB(beanName = LTE_CALL_FAILURE_TRACKING_AREA_EVENT_VOLUME_SERVICE)
    private Service lteCFATrackingAreaEventVolumeService;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.AbstractResource#getService()
     */
    @Override
    protected Service getService() {
        throw new UnsupportedOperationException();
    }

    @Path(NETWORK_EVENT_VOLUME)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureNetworkEventVolume() {
        return lteCFANetworkEventVolumeService.getData(mapResourceLayerParameters());
    }

    @Path(NETWORK_EVENT_VOLUME)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureNetworkEventVolumeAsCSV() {
        return lteCFANetworkEventVolumeService.getDataAsCSV(mapResourceLayerParameters(), response);
    }

    @Path(EVENT_VOLUME)
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getLteCallFailureEventVolume() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCFAEnodeBEventVolumeService.getData(reqParams);
        } else if (TYPE_CELL.equals(type)) {
            return lteCFAEcellEventVolumeService.getData(reqParams);
        }
        return lteCFATrackingAreaEventVolumeService.getData(reqParams);
    }

    @Path(EVENT_VOLUME)
    @GET
    @Produces(MediaTypeConstants.APPLICATION_CSV)
    public Response getLteCallFailureEventVolumeAsCSV() {
        final MultivaluedMap<String, String> reqParams = mapResourceLayerParameters();
        final String type = reqParams.getFirst(TYPE_PARAM);
        if (TYPE_BSC.equals(type)) {
            return lteCFAEnodeBEventVolumeService.getDataAsCSV(mapResourceLayerParameters(), response);
        } else if (TYPE_CELL.equals(type)) {
            return lteCFAEcellEventVolumeService.getDataAsCSV(mapResourceLayerParameters(), response);
        }
        return lteCFATrackingAreaEventVolumeService.getDataAsCSV(mapResourceLayerParameters(), response);
    }
}
