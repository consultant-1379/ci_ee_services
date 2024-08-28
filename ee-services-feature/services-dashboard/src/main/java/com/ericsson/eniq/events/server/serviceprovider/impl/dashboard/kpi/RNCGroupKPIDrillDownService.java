/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.KPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDrillDownDataType;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.List;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.SEARCH_PARAM;

/**
 * Desrible RNCKPIDrillDownService
 *
 * @author ezhelao
 * @since 11/2011
 */
@Stateless
@Local(Service.class)
public class RNCGroupKPIDrillDownService extends AbstractKPIService {

    @Override
    public Response getDataAsCSV(final MultivaluedMap<String, String> serviceProviderParameters, final HttpServletResponse response) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getData(final MultivaluedMap<String, String> serviceProviderParameters) {
        final List<String> rncNames;
        try {
            rncNames = rncCellKPIServiceParamUtil.convertCommaSeparatedStringToList(serviceProviderParameters.getFirst(SEARCH_PARAM));
            final XMLGregorianCalendar xmlGregorianCalendar = dashboardKpiCalenderUtility.getXMLGregorianCalendarFromParams(serviceProviderParameters);
            final List<NodeQueryKPIDrillDownDataType> nodeQueryKPIDataTypes = nodeQuery.getRNCKPIDrillDown(rncNames, xmlGregorianCalendar);
            final KPIDataType kpiDataType = rncCellKPIServiceParamUtil.getKPIDataTypeFromReturnedData(nodeQueryKPIDataTypes);
            return objectMapper.writeValueAsString(kpiDataType);
        } catch (Exception e) {
            return handleKPIServiceException(e, RNCGroupKPIDrillDownService.class.getName());
        }
    }
}
