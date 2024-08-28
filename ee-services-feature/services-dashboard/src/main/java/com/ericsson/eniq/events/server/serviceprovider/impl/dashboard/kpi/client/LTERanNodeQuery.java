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
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.LTENetworkRanKPIDataType;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.EniqEventsLTENetworkRanKpi;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.QaaWSHeader;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.QueryAsAServiceSoap;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.Row;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAService;
import com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi.RunQueryAsAServiceResponse;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

/**
*
* @author echchik
* @since 2012
*/
@Stateless
@Local
public class LTERanNodeQuery {

    @EJB
    ApplicationConfigManager applicationConfigManager;

    @EJB
    LTEKpiUtility lteKpiUtility;

    private static String serviceURL;

    private static String username;

    private static String password;

    private static final String SERVICE_RAN_PATH_NAME = "dswsbobje/qaawsservices/biws?WSDL=1&cuid=AZDb2pDkEGFMutVcNYOiFuk";

    private static final QName SELECTION_RAN_QUERY_QNAME = new QName("EniqEventsLTENetworkRanKpi",
            "EniqEventsLTENetworkRanKpi");

    /**
     * get the serviceURL from glassfish
     */
    @PostConstruct
    public void init() {
        serviceURL = applicationConfigManager.getBISServiceURL() + SERVICE_RAN_PATH_NAME;
        username = applicationConfigManager.getBISUsername();
        password = applicationConfigManager.getBISPassword();
    }

    public LTENetworkRanKPIDataType getNetworkRanKPI(final XMLGregorianCalendar date) throws MalformedURLException {
        final List<Row> kpiRows = getListOfRanKPIRowFromBIS(date);
        return lteKpiUtility.calculateRanKpis(kpiRows);
    }

    /**
     * @param date
     * @return
     * @throws MalformedURLException
     */

    private List<Row> getListOfRanKPIRowFromBIS(final XMLGregorianCalendar date) throws MalformedURLException {
        final EniqEventsLTENetworkRanKpi nodeSelection = new EniqEventsLTENetworkRanKpi(new URL(serviceURL),
                SELECTION_RAN_QUERY_QNAME);
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
