/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ericsson.eniq.events.server.serviceprovider.Service;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.KPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkRanKPIDataType;

/**
 *
 * @author echchik
 * @since 2012
 */
@Stateless
@Local(Service.class)
public class LTENetworkRanKpiService extends AbstractKPIService {

    @Override
    public Response getDataAsCSV(final MultivaluedMap<String, String> serviceProviderParameters,
            final HttpServletResponse response) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getData(final MultivaluedMap<String, String> serviceProviderParameters) {
        try {
            final XMLGregorianCalendar xmlGregorianCalendar = dashboardKpiCalenderUtility
                    .getXMLLocalGregorianCalendarFromParams(serviceProviderParameters);
            final LTENetworkRanKPIDataType nodeQueryKPIDataType = lteNodeQuery.getNetworkRanKPI(xmlGregorianCalendar);
            final KPIDataType kpiDataType = lteKpiUtility.getLteNetworkRanKpiData(nodeQueryKPIDataType);
            return objectMapper.writeValueAsString(kpiDataType);
        } catch (final Exception e) {
            return handleKPIServiceException(e, LTENetworkRanKpiService.class.getName());
        }
    }
}
