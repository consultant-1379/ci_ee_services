package com.ericsson.eniq.events.server.integritytests.sbr.popup;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup.SBrowserSgehPopupDetailService.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.popup.SBrowserSgehPopupDetailService;
import com.ericsson.eniq.events.server.test.queryresults.sbr.SBrowserCorePopupDetailResult;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author edarbla
 * @since 2012
 */
public class SBrowserCorePopupDetailAllEventsIntegrityTest extends BaseDataIntegrityTest<SBrowserCorePopupDetailResult> {

    private static final String TEMP_DIM_E_SGEH_CAUSECODE = "#DIM_E_SGEH_CAUSECODE";

    private static final String TEMP_DIM_E_SGEH_SUBCAUSECODE = "#DIM_E_SGEH_SUBCAUSECODE";

    private static final String TEMP_DIM_E_SGEH_RAT = "#DIM_E_SGEH_RAT";

    private static final String TEMP_DIM_E_SGEH_TAC = "#DIM_E_SGEH_TAC";

    private static final String TEMP_DIM_E_SGEH_MCCMNC = "#DIM_E_SGEH_MCCMNC";

    private static final String TEMP_DIM_E_SGEH_EVENTTYPE = "#DIM_E_SGEH_EVENTTYPE";

    private static final String TEMP_DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

    private static final String TEMP_DIM_E_SGEH_ATTACHTYPE = "#DIM_E_SGEH_ATTACHTYPE";

    private static final String TEMP_EVENT_E_SGEH_SUC_RAW = "#EVENT_E_SGEH_SUC_RAW";

    private static final String TEMP_EVENT_E_SGEH_ERR_RAW = "#EVENT_E_SGEH_ERR_RAW";

    private SBrowserSgehPopupDetailService sBrowserSgehPopupDetailService;

    final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();

    @Before
    public void onSetUp() throws Exception {
        sBrowserSgehPopupDetailService = new SBrowserSgehPopupDetailService();
        attachDependencies(sBrowserSgehPopupDetailService);

        requestParameters.putSingle(IMSI_PARAM, "1234567");
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1340198820000");
        requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1340199120000");
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "1027");
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, "1532");
        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "20112001");
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, "20122012");

        createTables(TEMP_DIM_E_SGEH_CAUSECODE, CAUSE_CODE_COLUMN, CAUSE_CODE_DESC_COLUMN);
        createTables(TEMP_DIM_E_SGEH_RAT, RAT, RAT_DESC);
        createTables(TEMP_DIM_E_SGEH_TAC, TAC, VENDOR_NAME, MARKETING_NAME);
        createTables(TEMP_DIM_E_SGEH_MCCMNC, MCC, MNC, OPERATOR, COUNTRY);
        createTables(TEMP_DIM_E_SGEH_EVENTTYPE, EVENT_ID, EVENT_ID_DESC);
        createTables(TEMP_DIM_E_SGEH_HIER321, ACCESS_AREA_ID);
        createTables(TEMP_DIM_E_SGEH_ATTACHTYPE, ATTACH_TYPE, ATTACH_TYPE_DESC);
        createTables(TEMP_DIM_E_SGEH_SUBCAUSECODE, SUBCAUSE_CODE_COLUMN, SUB_CAUSE_CODE_DESC_COLUMN);

        createTables(TEMP_EVENT_E_SGEH_SUC_RAW, EVENT_SOURCE_NAME, VENDOR, IMSI_MCC, IMSI_MNC, EVENT_ID, MSISDN, IMSI,
                TAC, ROAMING, EVENT_TIME, DURATION, RAT, ATTACH_TYPE, HIERARCHY_3, MCC, MNC, LAC, RAC, SAC, HLR,
                REQUEST_RETRIES, "CAUSE_CODE", SUBCAUSE_CODE, "GGSN_NAME", "APN", "PDP_MS_IPADDRESS_1",
                "ACTIVATION_TYPE", "DEACTIVATION_TRIGGER", "DATETIME_ID", "INTRA_RAU_TYPE", "TRANSFERRED_PDP",
                "DROPPED_PDP", "UPDATE_TYPE", "OLD_MCC", "OLD_MNC", DETACH_TYPE, DETACH_TRIGGER, DEACTIVATION_TRIGGER,
                OLD_LAC, OLD_RAC, "SERVICE_TYPE", SERVICE_REQ_TRIGGER, PAGING_ATTEMPTS, OLD_SGSN_IPADDRESS);

        createTables(TEMP_EVENT_E_SGEH_ERR_RAW, EVENT_SOURCE_NAME, VENDOR, IMSI_MCC, IMSI_MNC, EVENT_ID, MSISDN, IMSI,
                TAC, ROAMING, EVENT_TIME, DURATION, RAT, ATTACH_TYPE, HIERARCHY_3, MCC, MNC, LAC, RAC, SAC, HLR,
                REQUEST_RETRIES, "CAUSE_CODE", SUBCAUSE_CODE, "GGSN_NAME", "APN", "PDP_MS_IPADDRESS_1",
                "ACTIVATION_TYPE", "DEACTIVATION_TRIGGER", "DATETIME_ID", "INTRA_RAU_TYPE", "TRANSFERRED_PDP",
                "DROPPED_PDP", "UPDATE_TYPE", "OLD_MCC", "OLD_MNC", DETACH_TYPE, DETACH_TRIGGER, DEACTIVATION_TRIGGER,
                OLD_LAC, OLD_RAC, "SERVICE_TYPE", SERVICE_REQ_TRIGGER, PAGING_ATTEMPTS, OLD_SGSN_IPADDRESS);

        insertData();
    }

    @Test
    public void testCauseCodeDescription() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "0");
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");
        assertThat(eventOutcome.getString("Cause Code Description"), is("Test Description"));
    }

    @Test
    public void testSubCauseCodeDescription() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "0");
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");
        assertThat(eventOutcome.getString("Sub Cause Code Description"), is("Test Sub Description"));
    }

    @Ignore
    @Test
    public void testTerminalVendorMarketName() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "0");
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");
        final JSONObject details = data.getJSONObject("details");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        assertThat(subscriberIdentity.getString("Terminal"), is("BA"));

    }

    @Test
    public void testRunQueryForEventAttach() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "0"); // ATTACH
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject details = data.getJSONObject("details");
        final JSONObject networkLocation = details.getJSONObject("Network Location");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");
        final JSONObject eventDetails = details.getJSONObject("Event Details");

        assertThat(networkLocation.getInt("SGSN"), is(1));
        assertThat(subscriberIdentity.getString("IMSI"), is("1234567"));
        assertThat(eventOutcome.getString("Cause Code"), is("123"));
        assertThat(eventDetails.getString("Request Retries"), is("2"));
    }

    @Test
    public void testRunQueryForEventActivate() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "1"); // Activate
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject details = data.getJSONObject("details");
        final JSONObject networkLocation = details.getJSONObject("Network Location");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");
        final JSONObject eventDetails = details.getJSONObject("Event Details");

        assertThat(networkLocation.getInt("SGSN"), is(1));
        assertThat(subscriberIdentity.getString("IMSI"), is("1234567"));
        assertThat(eventOutcome.getString("Cause Code"), is("123"));
        assertThat(eventDetails.getString("Request Retries"), is("2"));
    }

    @Test
    public void testRunQueryForEventRau() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "2"); // Rau
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject details = data.getJSONObject("details");
        final JSONObject networkLocation = details.getJSONObject("Network Location");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");

        assertThat(networkLocation.getInt("SGSN"), is(1));
        assertThat(subscriberIdentity.getString("IMSI"), is("1234567"));
        assertThat(eventOutcome.getString("Cause Code"), is("123"));
    }

    @Test
    public void testRunQueryForEventIsrau() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "3"); // Israu
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject details = data.getJSONObject("details");
        final JSONObject networkLocation = details.getJSONObject("Network Location");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");

        assertThat(networkLocation.getString("SGSN"), is("Old: 53.0.0.0. New: 1"));
        assertThat(subscriberIdentity.getString("IMSI"), is("1234567"));
        assertThat(eventOutcome.getString("Cause Code"), is("123"));
    }

    @Test
    public void testRunQueryForEventDeactivate() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "4"); // Deactivate
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject details = data.getJSONObject("details");
        final JSONObject networkLocation = details.getJSONObject("Network Location");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");
        final JSONObject eventDetails = details.getJSONObject("Event Details");

        assertThat(networkLocation.getInt("SGSN"), is(1));
        assertThat(subscriberIdentity.getString("IMSI"), is("1234567"));
        assertThat(eventOutcome.getString("Cause Code"), is("123"));
    }

    @Test
    public void testRunQueryForEventDetach() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "14"); // Detach
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject details = data.getJSONObject("details");
        final JSONObject networkLocation = details.getJSONObject("Network Location");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");
        final JSONObject eventDetails = details.getJSONObject("Event Details");

        assertThat(networkLocation.getInt("SGSN"), is(1));
        assertThat(subscriberIdentity.getString("IMSI"), is("1234567"));
        assertThat(eventOutcome.getString("Cause Code"), is("123"));
    }

    @Test
    public void testRunQueryForEventServiceRequest() throws Exception {
        requestParameters.putSingle(EVENT_ID_PARAM, "15"); // ServiceRequest
        final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);

        final JSONObject jsonResult = new JSONObject(json);
        final JSONObject data = jsonResult.getJSONObject("data");

        final JSONObject details = data.getJSONObject("details");
        final JSONObject networkLocation = details.getJSONObject("Network Location");
        final JSONObject subscriberIdentity = details.getJSONObject("Subscriber Identity");
        final JSONObject eventOutcome = details.getJSONObject("Event Outcome");

        assertThat(networkLocation.getInt("SGSN"), is(1));
        assertThat(subscriberIdentity.getString("IMSI"), is("1234567"));
        assertThat(eventOutcome.getString("Cause Code"), is("123"));
    }

    private void createTables(final String temporaryTable, final String... columns) throws Exception {
        final Collection<String> columnsForTempTable = new ArrayList<String>();
        Collections.addAll(columnsForTempTable, columns);
        createTemporaryTable(temporaryTable, columnsForTempTable);
    }

    private void insertData() throws Exception {
        //setup DIM_E_SGEH_EVENTTYPE table
        final Map<String, Object> columns1For_DIM_E_SGEH_EVENTTYPE = new HashMap<String, Object>();
        columns1For_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID, "0");
        columns1For_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID_DESC, "ATTACH");
        insertRow(TEMP_DIM_E_SGEH_EVENTTYPE, columns1For_DIM_E_SGEH_EVENTTYPE);

        final Map<String, Object> columnsFor_DIM_E_SGEH_EVENTTYPE = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID, "1");
        columnsFor_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID_DESC, "ACTIVATE");
        insertRow(TEMP_DIM_E_SGEH_EVENTTYPE, columnsFor_DIM_E_SGEH_EVENTTYPE);

        final Map<String, Object> columns2For_DIM_E_SGEH_EVENTTYPE = new HashMap<String, Object>();
        columns2For_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID, "4");
        columns2For_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID_DESC, "DEACTIVATE");
        insertRow(TEMP_DIM_E_SGEH_EVENTTYPE, columns2For_DIM_E_SGEH_EVENTTYPE);

        //setup DIM_E_SGEH_DIM_E_SGEH_HIER321 table
        final Map<String, Object> columnsFor_DIM_E_SGEH_HIER321 = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_HIER321.put(ACCESS_AREA_ID, "2222");
        insertRow(TEMP_DIM_E_SGEH_HIER321, columnsFor_DIM_E_SGEH_HIER321);

        final Map<String, Object> columns2For_DIM_E_SGEH_HIER321 = new HashMap<String, Object>();
        columns2For_DIM_E_SGEH_HIER321.put(ACCESS_AREA_ID, "2223");
        insertRow(TEMP_DIM_E_SGEH_HIER321, columns2For_DIM_E_SGEH_HIER321);

        final Map<String, Object> columns1For_DIM_E_SGEH_TAC = new HashMap<String, Object>();
        columns1For_DIM_E_SGEH_TAC.put(TAC, 123);
        columns1For_DIM_E_SGEH_TAC.put(VENDOR_NAME, "A");
        columns1For_DIM_E_SGEH_TAC.put(MARKETING_NAME, "B");
        insertRow(TEMP_DIM_E_SGEH_TAC, columns1For_DIM_E_SGEH_TAC);

        final Map<String, Object> columns1For_DIM_E_SGEH_CAUSECODE = new HashMap<String, Object>();
        columns1For_DIM_E_SGEH_CAUSECODE.put(CAUSE_CODE_COLUMN, "123");
        columns1For_DIM_E_SGEH_CAUSECODE.put(CAUSE_CODE_DESC_COLUMN, "Test Description");
        insertRow(TEMP_DIM_E_SGEH_CAUSECODE, columns1For_DIM_E_SGEH_CAUSECODE);

        final Map<String, Object> columns1For_DIM_E_SGEH_SUBCAUSECODE = new HashMap<String, Object>();
        columns1For_DIM_E_SGEH_SUBCAUSECODE.put(SUBCAUSE_CODE_COLUMN, "5");
        columns1For_DIM_E_SGEH_SUBCAUSECODE.put(SUB_CAUSE_CODE_DESC_COLUMN, "Test Sub Description");
        insertRow(TEMP_DIM_E_SGEH_SUBCAUSECODE, columns1For_DIM_E_SGEH_SUBCAUSECODE);

        // Generate fake data for each event type in temp RAW tables
        prePopulateRawTables(String.valueOf(ATTACH_ID));
        prePopulateRawTables(String.valueOf(ACTIVATE_ID));
        prePopulateRawTables(String.valueOf(RAU_ID));
        prePopulateRawTables(String.valueOf(ISRAU_ID));
        prePopulateRawTables(String.valueOf(DEACTIVATE_ID));
        prePopulateRawTables(String.valueOf(DETACH_ID));
        prePopulateRawTables(String.valueOf(SERVICE_REQUEST_ID));
    }

    // Populate 2 RAW tables for running integrity test
    private void prePopulateRawTables(final String eventId) throws SQLException {
        final Map<String, Object> data1For_EVENT_E_SGEH_SUC_RAW = new HashMap<String, Object>();
        data1For_EVENT_E_SGEH_SUC_RAW.put(IMSI, "1234567");
        data1For_EVENT_E_SGEH_SUC_RAW.put(EVENT_ID, eventId);
        data1For_EVENT_E_SGEH_SUC_RAW.put(EVENT_TIME, "2012-06-20 13:28:00.000");
        data1For_EVENT_E_SGEH_SUC_RAW.put(DATETIME_ID, "2012-07-10 13:16");
        data1For_EVENT_E_SGEH_SUC_RAW.put(TAC, 300001);
        data1For_EVENT_E_SGEH_SUC_RAW.put(RAT, "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put(SAC, "2222");
        data1For_EVENT_E_SGEH_SUC_RAW.put(DURATION, "123");
        data1For_EVENT_E_SGEH_SUC_RAW.put(VENDOR, "Ericsson");
        data1For_EVENT_E_SGEH_SUC_RAW.put(MSISDN, "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put(MCC, "5");
        data1For_EVENT_E_SGEH_SUC_RAW.put(MNC, "6");
        data1For_EVENT_E_SGEH_SUC_RAW.put(REQUEST_RETRIES, "2");
        data1For_EVENT_E_SGEH_SUC_RAW.put(IMSI_MNC, "8");
        data1For_EVENT_E_SGEH_SUC_RAW.put(IMSI_MCC, "7");
        data1For_EVENT_E_SGEH_SUC_RAW.put(RAC, "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put(ROAMING, "10");
        data1For_EVENT_E_SGEH_SUC_RAW.put(GGSN_NAME, "Test event");
        data1For_EVENT_E_SGEH_SUC_RAW.put("CAUSE_CODE", "123");
        data1For_EVENT_E_SGEH_SUC_RAW.put(LAC, "123");
        data1For_EVENT_E_SGEH_SUC_RAW.put(PDP_MS_IPADDRESS_1, "5");
        data1For_EVENT_E_SGEH_SUC_RAW.put(OLD_SGSN_IPADDRESS, "5");
        data1For_EVENT_E_SGEH_SUC_RAW.put(SUBCAUSE_CODE, "5");
        data1For_EVENT_E_SGEH_SUC_RAW.put(APN, "APN");
        data1For_EVENT_E_SGEH_SUC_RAW.put(ATTACH_TYPE, "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put(EVENT_SOURCE_NAME, "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put(DEACTIVATION_TRIGGER, "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put(HLR, "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put(HIERARCHY_3, "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put("ACTIVATION_TYPE", "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put("DROPPED_PDP", "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put("INTRA_RAU_TYPE", "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put("TRANSFERRED_PDP", "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put("UPDATE_TYPE", "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put("OLD_MCC", "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put("OLD_MNC", "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put(DETACH_TYPE, "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put(DETACH_TRIGGER, "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put(OLD_LAC, "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put(OLD_RAC, "0");
        data1For_EVENT_E_SGEH_SUC_RAW.put("SERVICE_TYPE", 0);
        data1For_EVENT_E_SGEH_SUC_RAW.put(SERVICE_REQ_TRIGGER, 0);
        data1For_EVENT_E_SGEH_SUC_RAW.put(PAGING_ATTEMPTS, 10);
        insertRow(TEMP_EVENT_E_SGEH_SUC_RAW, data1For_EVENT_E_SGEH_SUC_RAW);

        final Map<String, Object> data1For_EVENT_E_SGEH_ERR_RAW = new HashMap<String, Object>();
        data1For_EVENT_E_SGEH_ERR_RAW.put(IMSI, "1234567");
        data1For_EVENT_E_SGEH_ERR_RAW.put(EVENT_ID, eventId);
        data1For_EVENT_E_SGEH_ERR_RAW.put(EVENT_TIME, "2012-06-20 13:28:00.000");
        data1For_EVENT_E_SGEH_ERR_RAW.put(DATETIME_ID, "2012-07-10 13:16");
        data1For_EVENT_E_SGEH_ERR_RAW.put(TAC, 300001);
        data1For_EVENT_E_SGEH_ERR_RAW.put(RAT, "1");
        data1For_EVENT_E_SGEH_ERR_RAW.put(SAC, "2222");
        data1For_EVENT_E_SGEH_ERR_RAW.put(DURATION, "123");
        data1For_EVENT_E_SGEH_ERR_RAW.put(VENDOR, "Ericsson");
        data1For_EVENT_E_SGEH_ERR_RAW.put(MSISDN, "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put(MCC, "5");
        data1For_EVENT_E_SGEH_ERR_RAW.put(MNC, "6");
        data1For_EVENT_E_SGEH_ERR_RAW.put(REQUEST_RETRIES, "2");
        data1For_EVENT_E_SGEH_ERR_RAW.put(IMSI_MNC, "8");
        data1For_EVENT_E_SGEH_ERR_RAW.put(IMSI_MCC, "7");
        data1For_EVENT_E_SGEH_ERR_RAW.put(RAC, "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put(ROAMING, "10");
        data1For_EVENT_E_SGEH_ERR_RAW.put(GGSN_NAME, "Test ATTACH");
        data1For_EVENT_E_SGEH_ERR_RAW.put("CAUSE_CODE", "123");
        data1For_EVENT_E_SGEH_ERR_RAW.put(LAC, "123");
        data1For_EVENT_E_SGEH_ERR_RAW.put(PDP_MS_IPADDRESS_1, "5");
        data1For_EVENT_E_SGEH_ERR_RAW.put(OLD_SGSN_IPADDRESS, "5");
        data1For_EVENT_E_SGEH_ERR_RAW.put(SUBCAUSE_CODE, "5");
        data1For_EVENT_E_SGEH_ERR_RAW.put(APN, "APN");
        data1For_EVENT_E_SGEH_ERR_RAW.put(ATTACH_TYPE, "1");
        data1For_EVENT_E_SGEH_ERR_RAW.put(EVENT_SOURCE_NAME, "1");
        data1For_EVENT_E_SGEH_ERR_RAW.put(DEACTIVATION_TRIGGER, "1");
        data1For_EVENT_E_SGEH_ERR_RAW.put(HLR, "1");
        data1For_EVENT_E_SGEH_ERR_RAW.put(HIERARCHY_3, "1");
        data1For_EVENT_E_SGEH_ERR_RAW.put("ACTIVATION_TYPE", "1");
        data1For_EVENT_E_SGEH_ERR_RAW.put("DROPPED_PDP", "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put("INTRA_RAU_TYPE", "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put("TRANSFERRED_PDP", "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put("UPDATE_TYPE", "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put("OLD_MCC", "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put("OLD_MNC", "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put(DETACH_TYPE, "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put(DETACH_TRIGGER, "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put(OLD_LAC, "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put(OLD_RAC, "0");
        data1For_EVENT_E_SGEH_ERR_RAW.put("SERVICE_TYPE", 0);
        data1For_EVENT_E_SGEH_ERR_RAW.put(SERVICE_REQ_TRIGGER, 0);
        data1For_EVENT_E_SGEH_ERR_RAW.put(PAGING_ATTEMPTS, 10);
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, data1For_EVENT_E_SGEH_ERR_RAW);
    }
}