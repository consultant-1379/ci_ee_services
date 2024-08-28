/*
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.KPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Desrible SummaryWCDMAService
 *
 * @author ezhelao
 * @since 11/2011
 */
@Stateless
@Local(Service.class)
public class SummaryWCDMAService extends AbstractKPIService {

    @Override
    public Response getDataAsCSV(final MultivaluedMap<String, String> serviceProviderParameters, final HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getData(final MultivaluedMap<String, String> serviceProviderParameters) {
        try {
            final XMLGregorianCalendar xmlGregorianCalendar = dashboardKpiCalenderUtility.getXMLGregorianCalendarFromParams(serviceProviderParameters);
            final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getNetworkKPI(xmlGregorianCalendar);

            final XMLGregorianCalendar xmlGregorianCalendarPreviousDay = dashboardKpiCalenderUtility.getPreviousDayXMLGregorianCalendarFromParams(serviceProviderParameters);

            final NodeQueryKPIDataType nodeQueryKPIDataTypePreviousDay = nodeQuery.getNetworkKPI(xmlGregorianCalendarPreviousDay);
            final KPIDataType kpiDataType = rncCellKPIServiceParamUtil.getKPIDataTypeFromReturnedData(nodeQueryKPIDataType, nodeQueryKPIDataTypePreviousDay);
            return objectMapper.writeValueAsString(kpiDataType);
        } catch (Exception e) {
            return handleKPIServiceException(e, SummaryWCDMAService.class.getName());
        }
    }
}
