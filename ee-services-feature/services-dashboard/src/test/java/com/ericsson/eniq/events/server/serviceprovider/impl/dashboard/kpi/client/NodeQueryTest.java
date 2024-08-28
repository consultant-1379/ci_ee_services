package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client;

import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.RNCCellKPIServiceParamUtil;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.KPIDataType;
import com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.kpi.client.datatype.NodeQueryKPIDataType;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Desrible NodeQueryTest
 *
 * @author ezhelao
 * @since 11/2011
 */
public class NodeQueryTest extends BaseJMockUnitTest {


    NodeQuery nodeQuery;
    ApplicationConfigManager mockedApplicationConfigManager;
    RNCCellKPIServiceParamUtil rncCellKPIServiceParamUtil;
    ObjectMapper objectMapper;


    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        nodeQuery = new NodeQuery();
        mockedApplicationConfigManager = mockery.mock(ApplicationConfigManager.class);
        allowOtherCallsForBISConfigurationSettings();

        rncCellKPIServiceParamUtil = new RNCCellKPIServiceParamUtil();
    }

    private void allowOtherCallsForBISConfigurationSettings() {
        mockery.checking(new Expectations() {
            {
                atMost(1).of(mockedApplicationConfigManager).getBISServiceOpenDocCompleteURLWithFormatter();
                allowing(mockedApplicationConfigManager).getBISReportsRootObjectName();
            }
        });

    }


    private void mockBISServiceURL(final String url) {
        mockery.checking(new Expectations() {

            {
                one(mockedApplicationConfigManager).getBISServiceURL();
                will(returnValue(url));
                one(mockedApplicationConfigManager).getBISUsername();
                will(returnValue("administrator"));
                one(mockedApplicationConfigManager).getBISPassword();
                will(returnValue(""));
            }

        });
        ReflectionTestUtils.setField(nodeQuery, "applicationConfigManager", mockedApplicationConfigManager);
        nodeQuery.init();

    }


    @Test
    @Ignore
    public void testGetNodeInformationRNCSuccess() throws Exception {
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/");
        final List<String> RNCs = new ArrayList<String>();
        RNCs.add("RNC1");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2011, 10, 14, 10, 10);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getRNCKPI(RNCs, xmlGregorianCalendar);
        System.out.println(nodeQueryKPIDataType);


    }

    @Test
    @Ignore
    public void testGetNodeInformationCellSuccess() throws Exception {
        System.out.println(TimeZone.getTimeZone("GMT + 5"));
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/");
        final List<String> cells = new ArrayList<String>();
        cells.add("RNC1-1-1");
        cells.add("RNC2-1-1");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(2011, 10, 14, 14, 12, 37);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getCellKPI(cells, xmlGregorianCalendar);
        System.out.println(nodeQueryKPIDataType);

    }

    @Test
    @Ignore
    public void testGetNodeInformationNetworkSuccess() throws Exception {
        mockBISServiceURL("http://atdl785esxvm21.athtem.eei.ericsson.se:8080/");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.clear();
        calendar.set(2011, 10, 14, 15, 00, 00);
        final XMLGregorianCalendar xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        System.out.println(xmlGregorianCalendar.toString());
        System.out.println(xmlGregorianCalendar.getTimezone());

        final NodeQueryKPIDataType nodeQueryKPIDataType = nodeQuery.getNetworkKPI(xmlGregorianCalendar);

        System.out.println(nodeQueryKPIDataType);

    }

    @Test
    public void objectMapperTest() throws IOException {
        KPIDataType kpiDataType = new KPIDataType();

        System.out.println(objectMapper.writeValueAsString(kpiDataType));
    }


}
