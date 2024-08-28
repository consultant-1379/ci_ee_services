/**
 * L.M.Ericsson
 */
package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDrillDownDataType;
import com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery.EniqEventsServicesNetworkRNCCellQuery;
import com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery.QaaWSHeader;
import com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery.QueryAsAServiceSoap;
import com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery.Row;
import com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery.RunQueryAsAService;
import com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery.RunQueryAsAServiceResponse;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;

/**
 * @author edarbla
 */

/* 
 * Given a Business Intelligence Server host name, and a date, this
 * program generates the Total number of connection requests and the Connection Success
 * rate for all cells and rnc's in a network, upon user input
 */

@Stateless
@Local
public class NodeQuery {

    @EJB
    ApplicationConfigManager applicationConfigManager;

    private static String serviceURL;

    private static String username;

    private static String password;

    private static final String SERVICE_PATH_NAME = "dswsbobje/qaawsservices/biws?WSDL=1&cuid=AVXcrr2OpENFgxXW_5e3S7w";

    private static final QName SELECTION_QUERTY_QNAME = new QName("EniqEventsServicesNetworkRNCCellQuery",
            "EniqEventsServicesNetworkRNCCellQuery");

    private static final NodeQueryKPIDataType DEFAULT_RETURNED_OBJECT = new NodeQueryKPIDataType(0.0, 0);

    private static final List<String> EMPTY_LIST_STRING = new ArrayList<String>();

    private static final int KPI_MULT = 100;

    /**
     * get the serviceURL from glassfish
     */
    @PostConstruct
    public void init() {
        serviceURL = applicationConfigManager.getBISServiceURL() + SERVICE_PATH_NAME;
        username = applicationConfigManager.getBISUsername();
        password = applicationConfigManager.getBISPassword();
    }

    public NodeQueryKPIDataType getNetworkKPI(final XMLGregorianCalendar date) throws MalformedURLException {
        return getKPI(EMPTY_LIST_STRING, EMPTY_LIST_STRING, date);
    }

    public List<NodeQueryKPIDrillDownDataType> getNetworkKPIDrillDown(final XMLGregorianCalendar date)
            throws MalformedURLException {
        return getDrillDownDataType(EMPTY_LIST_STRING, EMPTY_LIST_STRING, date);
    }

    public List<NodeQueryKPIDrillDownDataType> getRNCKPIDrillDown(final List<String> rncIDs,
            final XMLGregorianCalendar date) throws MalformedURLException {
        return getDrillDownDataType(rncIDs, EMPTY_LIST_STRING, date);

    }

    public List<NodeQueryKPIDrillDownDataType> getCellKPIDrillDown(final List<String> cellIDs,
            final XMLGregorianCalendar date) throws MalformedURLException {
        return getDrillDownDataType(EMPTY_LIST_STRING, cellIDs, date);
    }

    private List<NodeQueryKPIDrillDownDataType> getDrillDownDataType(final List<String> rncIDs,
            final List<String> cellIDs, final XMLGregorianCalendar date) throws MalformedURLException {
        final List<Row> rows = getListOfKPIRowFromBIS(rncIDs, cellIDs, date);
        return convertRowsToNodeQueryKPIDrillDownDataType(rows);

    }

    private List<NodeQueryKPIDrillDownDataType> convertRowsToNodeQueryKPIDrillDownDataType(final List<Row> rows) {
        final List<NodeQueryKPIDrillDownDataType> nodeQueryKPIDrillDownDataTypeList = new ArrayList<NodeQueryKPIDrillDownDataType>();
        Double succRate = 0.0;
        for (final Row r : rows) {
            succRate = 0.0;
            if ((int) r.getReq().doubleValue() == 0) {
                succRate = 0.0;
            } else {
                succRate = 100 * r.getReqSuccess() / r.getReq();
            }

            final NodeQueryKPIDrillDownDataType nodeQueryKPIDrillDownDataType = new NodeQueryKPIDrillDownDataType(
                    r.getUCellName(), r.getRNCName(), (int) r.getReqSuccess().doubleValue(), succRate, (int) r.getReq()
                            .doubleValue());
            nodeQueryKPIDrillDownDataTypeList.add(nodeQueryKPIDrillDownDataType);
        }
        return nodeQueryKPIDrillDownDataTypeList;
    }

    public NodeQueryKPIDataType getRNCKPI(final List<String> rncIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        if (null != rncIDs && rncIDs.size() > 0) {
            return getKPI(rncIDs, EMPTY_LIST_STRING, date);
        }
        return DEFAULT_RETURNED_OBJECT;

    }

    public NodeQueryKPIDataType getCellKPI(final List<String> cellIDs, final XMLGregorianCalendar date)
            throws MalformedURLException {
        if (null != cellIDs && cellIDs.size() > 0) // a good param
        {
            return getKPI(EMPTY_LIST_STRING, cellIDs, date);
        }
        return DEFAULT_RETURNED_OBJECT;
    }

    private NodeQueryKPIDataType getKPI(final List<String> rncIDs, final List<String> cellIDs,
            final XMLGregorianCalendar date) throws MalformedURLException {
        final List<Row> kpiRows = getListOfKPIRowFromBIS(rncIDs, cellIDs, date);
        return getRrcConnSuccessRate(kpiRows);
    }

    /**
     * @param rncIDs
     * @param cellIDs
     * @param date
     * @return
     * @throws MalformedURLException
     */

    private List<Row> getListOfKPIRowFromBIS(final List<String> rncIDs, final List<String> cellIDs,
            final XMLGregorianCalendar date) throws MalformedURLException {
        final EniqEventsServicesNetworkRNCCellQuery nodeSelection = new EniqEventsServicesNetworkRNCCellQuery(new URL(
                serviceURL), SELECTION_QUERTY_QNAME);
        final QueryAsAServiceSoap soapClient = nodeSelection.getQueryAsAServiceSoap();

        final RunQueryAsAService runQueryService = new RunQueryAsAService();
        runQueryService.setLogin(username);
        runQueryService.setPassword(password);

        runQueryService.setDate(date);
        runQueryService.getEnterValueSForRNCId().addAll(rncIDs);
        runQueryService.getEnterValueSForUCellId().addAll(cellIDs);
        final RunQueryAsAServiceResponse serviceResponse = soapClient.runQueryAsAService(runQueryService,
                new QaaWSHeader());
        return serviceResponse.getTable().getRow();
    }

    /**
     * Calculate the KPI, RrcConnectionSuccessRate
     *
     * @param rows
     * @return
     */
    private NodeQueryKPIDataType getRrcConnSuccessRate(final List<Row> rows) {
        Double tempReq = 0.0;
        Double tempReqSucc = 0.0;
        Double rate = 0.0;
        /**
         * if the rows size is zero
         */

        if (rows.size() == 0) {
            return new NodeQueryKPIDataType(Double.NaN, (int) tempReq.doubleValue());
        }

        for (final Row row : rows) {
            tempReq += row.getReq();
            tempReqSucc += row.getReqSuccess();
        }
        rate = (KPI_MULT * (tempReqSucc / tempReq));
        final NodeQueryKPIDataType nodeQueryKPIDataType = new NodeQueryKPIDataType(rate, (int) tempReq.doubleValue());
        return nodeQueryKPIDataType;

    }
}