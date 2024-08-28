/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.subsessionbi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.resources.SubsessionBIResource;
import com.ericsson.eniq.events.server.resources.TestsWithTemporaryTablesBaseTestCase;
import com.ericsson.eniq.events.server.test.queryresults.SubBIResult;
import com.ericsson.eniq.events.server.test.stubs.DummyUriInfoImpl;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.APN;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CHART_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DISPLAY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.MAX_ROWS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.common.EventIDConstants.ACTIVATE_IN_2G_AND_3G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.ATTACH_IN_4G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.DEACTIVATE_IN_2G_AND_3G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.DEDICATED_BEARER_ACTIVATE_IN_4G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.DEDICATED_BEARER_DEACTIVATE_IN_4G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.DETACH_IN_2G_AND_3G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.DETACH_IN_4G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.HANDOVER_IN_4G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.PDN_CONNECT_IN_4G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.PDN_DISCONNECT_IN_4G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.SERVICE_REQUEST_IN_2G_AND_3G;
import static com.ericsson.eniq.events.server.common.EventIDConstants.TAU_IN_4G;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ONE_WEEK;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_APN;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.SAMPLE_APN2;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TZ_OFFSET_OF_ZERO;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_ERR_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_LTE_SUC_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_SGEH_ERR_RAW;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_EVENT_E_SGEH_SUC_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author eemecoy
 * @since 2011
 *
 */
public class SubBIAPNUsageTest extends TestsWithTemporaryTablesBaseTestCase<SubBIResult> {

    private static final String TEST_IMSI = "312030410000004";

    SubsessionBIResource subsessionBIResource;

    private final static List<String> tempDataTables = new ArrayList<String>();

    private final static Map<String,String> rawTableColumns = new HashMap<String, String>();

    private final long testIMSI = Long.valueOf(TEST_IMSI);

    static {
        tempDataTables.add(TEMP_EVENT_E_SGEH_ERR_RAW);
        tempDataTables.add(TEMP_EVENT_E_SGEH_SUC_RAW);
        tempDataTables.add(TEMP_EVENT_E_LTE_ERR_RAW);
        tempDataTables.add(TEMP_EVENT_E_LTE_SUC_RAW);

        rawTableColumns.put("IMSI", "unsigned bigint");
        rawTableColumns.put("EVENT_ID", "unsigned bigint");
        rawTableColumns.put("DATETIME_ID", "timestamp");
        rawTableColumns.put("APN", "varchar(127)");
   }

    @Override
    public void onSetUp() throws Exception {
        super.onSetUp();
        subsessionBIResource = new SubsessionBIResource();

        for (final String tempTable : tempDataTables) {
            createTemporaryTableWithColumnTypes(tempTable, rawTableColumns);
        }

        populateTemporaryTables();

        attachDependencies(subsessionBIResource);
    }

    @Test
    public void testGetSUBBIAPNUsage_OneWeek() throws Exception {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(DISPLAY_PARAM, CHART_PARAM);
        map.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        map.putSingle(TYPE_PARAM, TYPE_IMSI);
        map.putSingle(IMSI_PARAM, TEST_IMSI);
        map.putSingle(TZ_OFFSET, TZ_OFFSET_OF_ZERO);
        map.putSingle(MAX_ROWS, "20");

        DummyUriInfoImpl.setUriInfo(map, subsessionBIResource);
        final String json = subsessionBIResource.getSubBIAPNData();
        System.out.println(json);

        final List<SubBIResult> results = getTranslator().translateResult(json, SubBIResult.class);
        validateResults(results);
    }

    private void validateResults(final List<SubBIResult> results) {
        assertThat(results.size(), is(2));
        final SubBIResult firstSubBIResult = results.get(0);
        assertThat(firstSubBIResult.getXAxisLabel(), is(SAMPLE_APN2));
        assertThat(firstSubBIResult.getSuccessCount(), is("5"));
        assertThat(firstSubBIResult.getFailureCount(), is("5"));

        final SubBIResult secondSubBIResult = results.get(1);
        assertThat(secondSubBIResult.getXAxisLabel(), is(SAMPLE_APN));
        assertThat(secondSubBIResult.getSuccessCount(), is("3"));
        assertThat(secondSubBIResult.getFailureCount(), is("3"));

    }

    private void populateTemporaryTables() throws SQLException {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN);
        values.put(EVENT_ID, ACTIVATE_IN_2G_AND_3G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(1));
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_SGEH_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN);
        values.put(EVENT_ID, DEACTIVATE_IN_2G_AND_3G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(2));
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_SGEH_SUC_RAW, values);
        insertRow(TEMP_EVENT_E_SGEH_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN2);
        values.put(EVENT_ID, ATTACH_IN_4G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(3));
        insertRow(TEMP_EVENT_E_LTE_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_LTE_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN2);
        values.put(EVENT_ID, DEDICATED_BEARER_ACTIVATE_IN_4G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(3));
        insertRow(TEMP_EVENT_E_LTE_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_LTE_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN2);
        values.put(EVENT_ID, DEDICATED_BEARER_DEACTIVATE_IN_4G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(3));
        insertRow(TEMP_EVENT_E_LTE_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_LTE_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN2);
        values.put(EVENT_ID, PDN_CONNECT_IN_4G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(3));
        insertRow(TEMP_EVENT_E_LTE_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_LTE_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN2);
        values.put(EVENT_ID, PDN_DISCONNECT_IN_4G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(4));
        insertRow(TEMP_EVENT_E_LTE_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_LTE_SUC_RAW, values);

        /* these are included here and should be excluded by the sql query */
        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN2);
        values.put(EVENT_ID, DETACH_IN_4G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(4));
        insertRow(TEMP_EVENT_E_LTE_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_LTE_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN2);
        values.put(EVENT_ID, HANDOVER_IN_4G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(4));
        insertRow(TEMP_EVENT_E_LTE_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_LTE_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN2);
        values.put(EVENT_ID, TAU_IN_4G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(4));
        insertRow(TEMP_EVENT_E_LTE_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_LTE_SUC_RAW, values);

        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN);
        values.put(EVENT_ID, DETACH_IN_2G_AND_3G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(1));
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_SGEH_SUC_RAW, values);

        values = new HashMap<String, Object>();
        values.put(IMSI, testIMSI);
        values.put(APN, SAMPLE_APN);
        values.put(EVENT_ID, SERVICE_REQUEST_IN_2G_AND_3G);
        values.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinusDay(2));
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, values);
        insertRow(TEMP_EVENT_E_SGEH_SUC_RAW, values);

    }
}
