/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.LTEKpiUtility;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkCoreKPIDataType;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI.EniqEventsLTENetworkCoreKPI;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI.QaaWSHeader;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI.QueryAsAServiceSoap;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI.Row;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI.RunQueryAsAService;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI.RunQueryAsAServiceResponse;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

/**
*
* @author ejamves
* @since 2012
*/
@Stateless
@Local
public class LTECoreNodeQuery {

    @EJB
    ApplicationConfigManager applicationConfigManager;

    @EJB
    LTEKpiUtility lteKpiUtility;

    private static String serviceURL;

    private static String username;

    private static String password;

    private static final String SERVICE_PATH_NAME = "dswsbobje/qaawsservices/biws?WSDL=1&cuid=AVoNMCPoSC9Cr.Tck0K.xgs";

    private static final QName SELECTION_QUERY_QNAME = new QName("EniqEventsLTENetworkCoreKPI",
            "EniqEventsLTENetworkCoreKPI");

    /**
     * get the serviceURL from glassfish
     */
    @PostConstruct
    public void init() {
        serviceURL = applicationConfigManager.getBISServiceURL() + SERVICE_PATH_NAME;
        username = applicationConfigManager.getBISUsername();
        password = applicationConfigManager.getBISPassword();
    }

    public LTENetworkCoreKPIDataType getNetworkCoreKPI(final XMLGregorianCalendar date) throws MalformedURLException {
        final List<Row> kpiRows = getListOfCoreKPIRowFromBIS(date);
        return lteKpiUtility.calculateCoreKpis(kpiRows);
    }

    /**
     * @param date
     * @return
     * @throws MalformedURLException
     */

    private List<Row> getListOfCoreKPIRowFromBIS(final XMLGregorianCalendar date) throws MalformedURLException {
        final EniqEventsLTENetworkCoreKPI nodeSelection = new EniqEventsLTENetworkCoreKPI(new URL(serviceURL),
                SELECTION_QUERY_QNAME);
        final QueryAsAServiceSoap soapClient = nodeSelection.getQueryAsAServiceSoap();
        final RunQueryAsAService runQueryService = new RunQueryAsAService();
        runQueryService.setLogin(username);
        runQueryService.setPassword(password);
        runQueryService.setDate(date);
        final RunQueryAsAServiceResponse serviceResponse = soapClient.runQueryAsAService(runQueryService,
                new QaaWSHeader());
        return serviceResponse.getTable().getRow();
    }
}
