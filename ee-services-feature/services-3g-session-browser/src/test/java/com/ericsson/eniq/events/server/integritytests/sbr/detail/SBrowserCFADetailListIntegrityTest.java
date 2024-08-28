/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.sbr.detail;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATETIME_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.EVENT_ID_DESC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TAC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.C_ID_1;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.EVENT_TIME;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.FIFTEEN_MINUTES_TEST;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.ONE_DAY;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.detail.SBrowserRanCfaDetailService;
import com.ericsson.eniq.events.server.test.queryresults.sbr.SBrowserRanCFADetailListResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserCFADetailListIntegrityTest extends BaseDataIntegrityTest<SBrowserRanCFADetailListResult> {

    //define table names for test
    private static final String TEMP_DIM_E_RAN_CFA_EVENTTYPE = "#DIM_E_RAN_CFA_EVENTTYPE";

    private static final String TEMP_DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String TEMP_EVENT_E_RAN_CFA_ERR_RAW = "#EVENT_E_RAN_CFA_ERR_RAW";

    //define the column names not in ApplicationConstants
    private static final String HIER321_ID = "HIER321_ID";

    private static final String CID = "CID";

    private static final String CELL_ID = "CELL_ID";

    private SBrowserRanCfaDetailService sBrowserRanCfaDetailService;

    @Before
    public void onSetUp() throws Exception {
        sBrowserRanCfaDetailService = new SBrowserRanCfaDetailService();
        attachDependencies(sBrowserRanCfaDetailService);
        createTables();
        insertData();
    }

    @Test
    public void testGet_Ran_CFA_Err_Raw_Data() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_DAY);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234567");
        final String json = runQuery(sBrowserRanCfaDetailService, requestParameters);
        verifyResultErrData(json);
    }

    @Test
    /**
     * Test that no data is returned for wrong time selection
     */
    public void testGet_Ran_CFA_Err_Raw_NoDataWrongTime() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, FIFTEEN_MINUTES_TEST);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234567");
        final String json = runQuery(sBrowserRanCfaDetailService, requestParameters);

        final List<SBrowserRanCFADetailListResult> result = getTranslator().translateResult(json,
                SBrowserRanCFADetailListResult.class);
        assertThat(result.size(), is(0));
    }

    @Test
    /**
     * Test that no data is returned for wrong imsi
     */
    public void testGet_Ran_CFA_Err_Raw_NoDataWrongImsi() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_DAY);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234567");
        final String json = runQuery(sBrowserRanCfaDetailService, requestParameters);

        final List<SBrowserRanCFADetailListResult> result = getTranslator().translateResult(json,
                SBrowserRanCFADetailListResult.class);
        assertThat(result.size(), is(3));
    }

    private void verifyResultErrData(final String json) throws Exception {
        final List<SBrowserRanCFADetailListResult> result = getTranslator().translateResult(json,
                SBrowserRanCFADetailListResult.class);
        assertThat(result.size(), is(3));

        int matched = 0;
        for (SBrowserRanCFADetailListResult res : result) {
        	if (res.getcId().equals("2222")) {
        		verifyErrInternalSysReleaseRow(res);
        		matched++;
        	} else if (res.getcId().equals("2223")){
        		verifyErrInternalCallSetupFailRow(res);
        		matched++;
        	} else if (res.getcId().equals("2224")) {
        		verifyErrInternalSysReleaseRowNoCellId(res);
        		matched++;
        	}
        }
        
        assertThat(matched, is(3));
    }

    private void verifyErrInternalSysReleaseRow(final SBrowserRanCFADetailListResult resultForInternalSysRelease) {
    	//check the err row
    	assertThat(resultForInternalSysRelease.getEventType(), is("INTERNAL_SYSTEM_RELEASE"));
    	assertThat(resultForInternalSysRelease.getcId(), is("2222"));
    	assertThat(resultForInternalSysRelease.getCellId(), is("82104B"));
    	assertThat(resultForInternalSysRelease.getTac(), is("300001"));
    	assertThat(resultForInternalSysRelease.getEventClassType(), is("CALL_FAILURE"));
    }
    
    private void verifyErrInternalSysReleaseRowNoCellId(final SBrowserRanCFADetailListResult resultForInternalSysRelease) {
        assertThat(resultForInternalSysRelease.getEventType(), is("INTERNAL_SYSTEM_RELEASE"));
        assertThat(resultForInternalSysRelease.getcId(), is("2224"));
        assertThat(resultForInternalSysRelease.getCellId(), is("-"));
        assertThat(resultForInternalSysRelease.getTac(), is("300003"));
        assertThat(resultForInternalSysRelease.getEventClassType(), is("CALL_FAILURE"));
    }

    private void verifyErrInternalCallSetupFailRow(final SBrowserRanCFADetailListResult resultForInternalCallSetupFail) {
        //check the success row
        assertThat(resultForInternalCallSetupFail.getEventType(), is("INTERNAL_CALL_SETUP_FAIL"));
        assertThat(resultForInternalCallSetupFail.getcId(), is("2223"));
        assertThat(resultForInternalCallSetupFail.getCellId(), is("82104C"));
        assertThat(resultForInternalCallSetupFail.getTac(), is("300002"));
        assertThat(resultForInternalCallSetupFail.getEventClassType(), is("CALL_FAILURE"));
    }

    private void insertData() throws Exception {

        //setup DIM_E_SGEH_EVENTTYPE table
        final Map<String, Object> columnsFor_DIM_E_RAN_CFA_EVENTTYPE = new HashMap<String, Object>();
        columnsFor_DIM_E_RAN_CFA_EVENTTYPE.put(EVENT_ID, "438");
        columnsFor_DIM_E_RAN_CFA_EVENTTYPE.put(EVENT_ID_DESC, "INTERNAL_SYSTEM_RELEASE");
        insertRow(TEMP_DIM_E_RAN_CFA_EVENTTYPE, columnsFor_DIM_E_RAN_CFA_EVENTTYPE);

        final Map<String, Object> columnsFor_DIM_E_RAN_CFA_EVENTTYPE2 = new HashMap<String, Object>();
        columnsFor_DIM_E_RAN_CFA_EVENTTYPE2.put(EVENT_ID, "456");
        columnsFor_DIM_E_RAN_CFA_EVENTTYPE2.put(EVENT_ID_DESC, "INTERNAL_CALL_SETUP_FAIL");
        insertRow(TEMP_DIM_E_RAN_CFA_EVENTTYPE, columnsFor_DIM_E_RAN_CFA_EVENTTYPE2);

        //setup DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL table
        final Map<String, Object> columnsFor_DIM_E_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(CID, 2222);
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(CELL_ID, "82104B");
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Map<String, Object> columns2For_DIM_E_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columns2For_DIM_E_SGEH_HIER321_CELL.put(CID, 2223);
        columns2For_DIM_E_SGEH_HIER321_CELL.put(CELL_ID, "82104C");
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, columns2For_DIM_E_SGEH_HIER321_CELL);

        //setup  EVENT_E_RAN_CFA_ERR_RAW table
        //create 2 rows with following data
        //DATETIME_ID EVENT_TIME  IMSI     EVENT_ID     TAC         C_ID_1
        // Now-40mins  Now-40mins   1234567  438          300001       2222      (Internal System Release)
        // Now-55mins  Now-55mins   1234567  456          300002       2223      (Internal Call Setup Fail)
        final Map<String, Object> data1For_EVENT_E_RAN_CFA_ERR_RAW = new HashMap<String, Object>();
        data1For_EVENT_E_RAN_CFA_ERR_RAW.put(IMSI, "1234567");
        data1For_EVENT_E_RAN_CFA_ERR_RAW.put(EVENT_ID, "438");
        data1For_EVENT_E_RAN_CFA_ERR_RAW.put(EVENT_TIME, DateTimeUtilities.getDateTimeMinus55Minutes());
        data1For_EVENT_E_RAN_CFA_ERR_RAW.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus55Minutes());
        data1For_EVENT_E_RAN_CFA_ERR_RAW.put(TAC, 300001);
        data1For_EVENT_E_RAN_CFA_ERR_RAW.put(C_ID_1, 2222);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, data1For_EVENT_E_RAN_CFA_ERR_RAW);

        final Map<String, Object> data2For_EVENT_E_RAN_CFA_ERR_RAW = new HashMap<String, Object>();
        data2For_EVENT_E_RAN_CFA_ERR_RAW.put(IMSI, "1234567");
        data2For_EVENT_E_RAN_CFA_ERR_RAW.put(EVENT_ID, "456");
        data2For_EVENT_E_RAN_CFA_ERR_RAW.put(EVENT_TIME, DateTimeUtilities.getDateTimeMinus55Minutes());
        data2For_EVENT_E_RAN_CFA_ERR_RAW.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus55Minutes());
        data2For_EVENT_E_RAN_CFA_ERR_RAW.put(TAC, 300002);
        data2For_EVENT_E_RAN_CFA_ERR_RAW.put(C_ID_1, 2223);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, data2For_EVENT_E_RAN_CFA_ERR_RAW);
        
        final Map<String, Object> data3For_EVENT_E_RAN_CFA_ERR_RAW = new HashMap<String, Object>();
        data3For_EVENT_E_RAN_CFA_ERR_RAW.put(IMSI, "1234567");
        data3For_EVENT_E_RAN_CFA_ERR_RAW.put(EVENT_ID, "438");
        data3For_EVENT_E_RAN_CFA_ERR_RAW.put(EVENT_TIME, DateTimeUtilities.getDateTimeMinus55Minutes());
        data3For_EVENT_E_RAN_CFA_ERR_RAW.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus55Minutes());
        data3For_EVENT_E_RAN_CFA_ERR_RAW.put(TAC, 300003);
        data3For_EVENT_E_RAN_CFA_ERR_RAW.put(C_ID_1, 2224);
        insertRow(TEMP_EVENT_E_RAN_CFA_ERR_RAW, data3For_EVENT_E_RAN_CFA_ERR_RAW);
    }

    private void createTables() throws Exception {
        //create TEMP_DIM_E_RAN_CFA_EVENTTYPE table
        final Collection<String> columnsFor_DIM_E_RAN_CFA_EVENTTYPE = new ArrayList<String>();
        columnsFor_DIM_E_RAN_CFA_EVENTTYPE.add(EVENT_ID);
        columnsFor_DIM_E_RAN_CFA_EVENTTYPE.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_RAN_CFA_EVENTTYPE, columnsFor_DIM_E_RAN_CFA_EVENTTYPE);

        //create DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL table
        final Collection<String> columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL.add(CID);
        columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL.add(CELL_ID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL);

        //create EVENT_E_SGEH_ERR_RAW table
        final Collection<String> columnsFor_EVENT_E_RAN_CFA_ERR_RAW = new ArrayList<String>();
        columnsFor_EVENT_E_RAN_CFA_ERR_RAW.add(IMSI);
        columnsFor_EVENT_E_RAN_CFA_ERR_RAW.add(EVENT_TIME);
        columnsFor_EVENT_E_RAN_CFA_ERR_RAW.add(EVENT_ID);
        columnsFor_EVENT_E_RAN_CFA_ERR_RAW.add(DATETIME_ID);
        columnsFor_EVENT_E_RAN_CFA_ERR_RAW.add(C_ID_1);
        columnsFor_EVENT_E_RAN_CFA_ERR_RAW.add(TAC);
        createTemporaryTable(TEMP_EVENT_E_RAN_CFA_ERR_RAW, columnsFor_EVENT_E_RAN_CFA_ERR_RAW);
    }

}
