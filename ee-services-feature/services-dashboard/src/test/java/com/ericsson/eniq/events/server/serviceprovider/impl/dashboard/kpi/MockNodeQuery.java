package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.NodeQuery;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDrillDownDataType;

import javax.xml.datatype.XMLGregorianCalendar;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Desrible MockNodeQuery
 *
 * @author ezhelao
 * @since 11/2011
 */
public class MockNodeQuery extends NodeQuery {
    @Override
    public void init() {
        super.init();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public NodeQueryKPIDataType getNetworkKPI(final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 300);
    }

    @Override
    public NodeQueryKPIDataType getRNCKPI(final List<String> rncNames, final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 301);
    }

    @Override
    public NodeQueryKPIDataType getCellKPI(final List<String> cells, final XMLGregorianCalendar date) throws MalformedURLException {
        return new NodeQueryKPIDataType(1.0, 302);
    }

    @Override
    public List<NodeQueryKPIDrillDownDataType> getNetworkKPIDrillDown(XMLGregorianCalendar date) throws MalformedURLException {
        if (date.getMonth() == 11 && date.getDay() == 27 && date.getHour() == 12) {
            NodeQueryKPIDrillDownDataType dateRow1 = new NodeQueryKPIDrillDownDataType("cellname1", "rncname1", 108, 90.0, 120);
            NodeQueryKPIDrillDownDataType dataRow2 = new NodeQueryKPIDrillDownDataType("cellname2", "rncname2", 84, 70.0, 120);
            List<NodeQueryKPIDrillDownDataType> data = new ArrayList<NodeQueryKPIDrillDownDataType>();
            data.add(dateRow1);
            data.add(dateRow1);
            return data;
        }
        if (date.getMonth() == 11 && date.getDay() == 26 && date.getHour() == 12) {
            NodeQueryKPIDrillDownDataType dateRow1 = new NodeQueryKPIDrillDownDataType("cellname1", "rncname1", 90, 90.0, 100);
            NodeQueryKPIDrillDownDataType dataRow2 = new NodeQueryKPIDrillDownDataType("cellname2", "rncname2", 77, 70.0, 110);
            List<NodeQueryKPIDrillDownDataType> data = new ArrayList<NodeQueryKPIDrillDownDataType>();
            data.add(dateRow1);
            data.add(dateRow1);
            return data;
        }
        return null;
    }


    @Override
    public List<NodeQueryKPIDrillDownDataType> getRNCKPIDrillDown(List<String> rncIDs, XMLGregorianCalendar date) throws MalformedURLException {
        return getNetworkKPIDrillDown(date);
    }

    @Override
    public List<NodeQueryKPIDrillDownDataType> getCellKPIDrillDown(List<String> cellIDs, XMLGregorianCalendar date) throws MalformedURLException {
        return getNetworkKPIDrillDown(date);
    }


}
