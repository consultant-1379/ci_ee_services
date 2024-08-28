/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.sbr.detail;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.detail.SBrowserCoreDetailService;
import com.ericsson.eniq.events.server.test.queryresults.sbr.SBrowserCoreDetailListResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author ehaoswa
 * @since 2012
 *
 */
public class SBrowserCoreDetailListIntegrityTest extends BaseDataIntegrityTest<SBrowserCoreDetailListResult> {

    //define table names for test
    private static final String TEMP_DIM_E_SGEH_EVENTTYPE = "#DIM_E_SGEH_EVENTTYPE";

    private static final String TEMP_DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

    private static final String TEMP_DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String TEMP_EVENT_E_SGEH_SUC_RAW = "#EVENT_E_SGEH_SUC_RAW";

    private static final String TEMP_EVENT_E_SGEH_ERR_RAW = "#EVENT_E_SGEH_ERR_RAW";

    //define the column names not in ApplicationConstants
    private static final String ACCESS_AREA_ID = "ACCESS_AREA_ID";

    private static final String HIER321_ID = "HIER321_ID";

    private static final String CID = "CID";

    private static final String CELL_ID = "CELL_ID";

    private static final String RAT = "RAT";

    //DateTime formats
    private static String DATE_FORMAT = "yyyy-MM-dd";

    private static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private SBrowserCoreDetailService sBrowserCoreDetailService;

    @Before
    public void onSetUp() throws Exception {
        sBrowserCoreDetailService = new SBrowserCoreDetailService();
        attachDependencies(sBrowserCoreDetailService);
        createTables();
        insertData();
    }

    @Test
    public void testGet_Sgeh_Suc_Raw_Data_Only() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234567");
        final String json = runQuery(sBrowserCoreDetailService, requestParameters);

        final List<SBrowserCoreDetailListResult> result = getTranslator().translateResult(json,
                SBrowserCoreDetailListResult.class);
        assertThat(result.size(), is(1));
        verifySucRow(result.get(0));
    }

    @Test
    public void testGet_Sgeh_Err_Raw_Data_Only() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        //need 25 min window to select just error row which is set for now minus 35 mins
        final String startTime = DateTimeUtilities.getDateTimeMinus55Minutes();
        final String endTime = DateTimeUtilities.getDateTimeMinus30Minutes();

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM,
                DateTimeUtilities.getDateParamValueFromDateTimeString(startTime));
        requestParameters
                .putSingle(DATE_TO_QUERY_PARAM, DateTimeUtilities.getDateParamValueFromDateTimeString(endTime));
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM,
                DateTimeUtilities.getTimeParamValueFromDateTimeString(startTime));
        requestParameters
                .putSingle(TIME_TO_QUERY_PARAM, DateTimeUtilities.getTimeParamValueFromDateTimeString(endTime));
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234567");
        final String json = runQuery(sBrowserCoreDetailService, requestParameters);

        final List<SBrowserCoreDetailListResult> result = getTranslator().translateResult(json,
                SBrowserCoreDetailListResult.class);
        assertThat(result.size(), is(1));
        verifyErrRow(result.get(0));
    }

    @Test
    public void testGet_Sgeh_Err_And_Suc_Raw_Data() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_DAY);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234567");
        final String json = runQuery(sBrowserCoreDetailService, requestParameters);
        verifyResultSuccAndErrData(json);
    }

    @Test
    public void testGet_Sgeh_Err_And_Suc_Raw_NoDataWrongTime() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "1234567");
        final String json = runQuery(sBrowserCoreDetailService, requestParameters);

        final List<SBrowserCoreDetailListResult> result = getTranslator().translateResult(json,
                SBrowserCoreDetailListResult.class);
        assertThat(result.size(), is(0));
    }

    @Test
    public void testGet_Sgeh_Err_And_Suc_Raw_NoDataWrongImsi() throws Exception {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, ONE_DAY);
        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "456456");
        final String json = runQuery(sBrowserCoreDetailService, requestParameters);

        final List<SBrowserCoreDetailListResult> result = getTranslator().translateResult(json,
                SBrowserCoreDetailListResult.class);
        assertThat(result.size(), is(0));
    }

    private void verifyResultSuccAndErrData(final String json) throws Exception {
        final List<SBrowserCoreDetailListResult> result = getTranslator().translateResult(json,
                SBrowserCoreDetailListResult.class);
        assertThat(result.size(), is(2));

        SBrowserCoreDetailListResult resultForCoreDetailSuc = null;
        SBrowserCoreDetailListResult resultForCoreDetailErr = null;

        if (result.get(0).getErrFlag().equals("true")) {
            resultForCoreDetailSuc = result.get(1);
            resultForCoreDetailErr = result.get(0);
        } else {
            resultForCoreDetailSuc = result.get(0);
            resultForCoreDetailErr = result.get(1);
        }

        verifyErrRow(resultForCoreDetailErr);
        verifySucRow(resultForCoreDetailSuc);
    }

    private void verifyErrRow(final SBrowserCoreDetailListResult resultForCoreDetailErr) {
        //check the err row
        assertThat(resultForCoreDetailErr.getEventType(), is(DEACTIVATE));
        assertThat(resultForCoreDetailErr.getcId(), is("23456"));
        assertThat(resultForCoreDetailErr.getCellId(), is("82104C"));
        assertThat(resultForCoreDetailErr.getDuration(), is(333));
        assertThat(resultForCoreDetailErr.getTac(), is("500001"));
        assertThat(resultForCoreDetailErr.getErrFlag(), is("true"));
        assertThat(resultForCoreDetailErr.getEventClassType(), is("CORE"));
    }

    private void verifySucRow(final SBrowserCoreDetailListResult resultForCoreDetailSuc) {
        //check the success row
        assertThat(resultForCoreDetailSuc.getEventType(), is(ACTIVATE));
        assertThat(resultForCoreDetailSuc.getcId(), is("12345"));
        assertThat(resultForCoreDetailSuc.getCellId(), is("82104B"));
        assertThat(resultForCoreDetailSuc.getDuration(), is(123));
        assertThat(resultForCoreDetailSuc.getTac(), is("300001"));
        assertThat(resultForCoreDetailSuc.getErrFlag(), is("false"));
        assertThat(resultForCoreDetailSuc.getEventClassType(), is("CORE"));
    }

    private void insertData() throws Exception {

        //setup DIM_E_SGEH_EVENTTYPE table
        final Map<String, Object> columnsFor_DIM_E_SGEH_EVENTTYPE = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID, "1");
        columnsFor_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID_DESC, "ACTIVATE");
        insertRow(TEMP_DIM_E_SGEH_EVENTTYPE, columnsFor_DIM_E_SGEH_EVENTTYPE);

        final Map<String, Object> columns2For_DIM_E_SGEH_EVENTTYPE = new HashMap<String, Object>();
        columns2For_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID, "4");
        columns2For_DIM_E_SGEH_EVENTTYPE.put(EVENT_ID_DESC, "DEACTIVATE");
        insertRow(TEMP_DIM_E_SGEH_EVENTTYPE, columns2For_DIM_E_SGEH_EVENTTYPE);

        //setup DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL table
        final Map<String, Object> columnsFor_DIM_E_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIERARCHY_1, "2222");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(HIERARCHY_3, "RNC1");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(VENDOR, "Ericsson");
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(CID, 12345);
        columnsFor_DIM_E_SGEH_HIER321_CELL.put(CELL_ID, "82104B");
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_HIER321_CELL);

        final Map<String, Object> columns2For_DIM_E_SGEH_HIER321_CELL = new HashMap<String, Object>();
        columns2For_DIM_E_SGEH_HIER321_CELL.put(HIERARCHY_1, "2223");
        columns2For_DIM_E_SGEH_HIER321_CELL.put(HIERARCHY_3, "RNC1");
        columns2For_DIM_E_SGEH_HIER321_CELL.put(VENDOR, "Ericsson");
        columns2For_DIM_E_SGEH_HIER321_CELL.put(CID, 23456);
        columns2For_DIM_E_SGEH_HIER321_CELL.put(CELL_ID, "82104C");
        insertRow(TEMP_DIM_E_SGEH_HIER321_CELL, columns2For_DIM_E_SGEH_HIER321_CELL);

        //setup  EVENT_E_SGEH_SUC_RAW table
        //create 2 rows with following data
        //DATETIME_ID EVENT_TIME  IMSI    EVENT_ID  TAC      DURATION  RAT  ACCESS_AREA_ID
        // Now-25mins  Now-25mins   1234567  1         300001   123       1   2222      (correct rat should be selected)
        // Now-25mins  Now-25mins   1234567  1         300002   321       0   2222      (incorrect rat should not be selected)
        final Map<String, Object> data1For_EVENT_E_SGEH_SUC_RAW = new HashMap<String, Object>();
        data1For_EVENT_E_SGEH_SUC_RAW.put(IMSI, "1234567");
        data1For_EVENT_E_SGEH_SUC_RAW.put(EVENT_ID, "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put(EVENT_TIME, DateTimeUtilities.getDateTimeMinus25Minutes());
        data1For_EVENT_E_SGEH_SUC_RAW.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus25Minutes());
        data1For_EVENT_E_SGEH_SUC_RAW.put(TAC, 300001);
        data1For_EVENT_E_SGEH_SUC_RAW.put(RAT, "1");
        data1For_EVENT_E_SGEH_SUC_RAW.put(HIERARCHY_1, "2222");
        data1For_EVENT_E_SGEH_SUC_RAW.put(HIERARCHY_3, "RNC1");
        data1For_EVENT_E_SGEH_SUC_RAW.put(VENDOR, "Ericsson");
        data1For_EVENT_E_SGEH_SUC_RAW.put(DURATION, "123");
        insertRow(TEMP_EVENT_E_SGEH_SUC_RAW, data1For_EVENT_E_SGEH_SUC_RAW);

        //wont be matched as has RAT = 0
        final Map<String, Object> data2For_EVENT_E_SGEH_SUC_RAW = new HashMap<String, Object>();
        data2For_EVENT_E_SGEH_SUC_RAW.put(IMSI, "1234567");
        data2For_EVENT_E_SGEH_SUC_RAW.put(EVENT_ID, "1");
        data2For_EVENT_E_SGEH_SUC_RAW.put(EVENT_TIME, DateTimeUtilities.getDateTimeMinus25Minutes());
        data2For_EVENT_E_SGEH_SUC_RAW.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus25Minutes());
        data2For_EVENT_E_SGEH_SUC_RAW.put(TAC, 300002);
        data2For_EVENT_E_SGEH_SUC_RAW.put(RAT, "0");
        data2For_EVENT_E_SGEH_SUC_RAW.put(HIERARCHY_1, "2222");
        data2For_EVENT_E_SGEH_SUC_RAW.put(HIERARCHY_3, "RNC1");
        data2For_EVENT_E_SGEH_SUC_RAW.put(VENDOR, "Ericsson");
        data2For_EVENT_E_SGEH_SUC_RAW.put(DURATION, "321");
        insertRow(TEMP_EVENT_E_SGEH_SUC_RAW, data2For_EVENT_E_SGEH_SUC_RAW);

        //setup  EVENT_E_SGEH_ERR_RAW table
        //create 2 rows with following data
        //DATETIME_ID EVENT_TIME  IMSI    EVENT_ID  TAC      DURATION  RAT  ACCESS_AREA_ID
        // Now-35mins  Now-35mins   1234567  4         500001   333       1   2223      (correct rat should be selected)
        // Now-35mins  Now-35mins   1234567  4         500002   444       0   2223      (incorrect rat should not be selected)
        //setup  EVENT_E_SGEH_ERR_RAW table
        final Map<String, Object> data1For_EVENT_E_SGEH_ERR_RAW = new HashMap<String, Object>();
        data1For_EVENT_E_SGEH_ERR_RAW.put(IMSI, "1234567");
        data1For_EVENT_E_SGEH_ERR_RAW.put(EVENT_ID, "4");
        data1For_EVENT_E_SGEH_ERR_RAW.put(EVENT_TIME, DateTimeUtilities.getDateTimeMinus35Minutes());
        data1For_EVENT_E_SGEH_ERR_RAW.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus35Minutes());
        data1For_EVENT_E_SGEH_ERR_RAW.put(TAC, 500001);
        data1For_EVENT_E_SGEH_ERR_RAW.put(RAT, "1");
        data1For_EVENT_E_SGEH_ERR_RAW.put(HIERARCHY_1, "2223");
        data1For_EVENT_E_SGEH_ERR_RAW.put(HIERARCHY_3, "RNC1");
        data1For_EVENT_E_SGEH_ERR_RAW.put(VENDOR, "Ericsson");
        data1For_EVENT_E_SGEH_ERR_RAW.put(DURATION, "333");
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, data1For_EVENT_E_SGEH_ERR_RAW);

        //wont be matched as has RAT = 0
        final Map<String, Object> data2For_EVENT_E_SGEH_ERR_RAW = new HashMap<String, Object>();
        data2For_EVENT_E_SGEH_ERR_RAW.put(IMSI, "1234567");
        data2For_EVENT_E_SGEH_ERR_RAW.put(EVENT_ID, "4");
        data2For_EVENT_E_SGEH_ERR_RAW.put(EVENT_TIME, DateTimeUtilities.getDateTimeMinus35Minutes());
        data2For_EVENT_E_SGEH_ERR_RAW.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus35Minutes());
        data2For_EVENT_E_SGEH_ERR_RAW.put(TAC, 500002);
        data2For_EVENT_E_SGEH_ERR_RAW.put(RAT, "0");
        data2For_EVENT_E_SGEH_ERR_RAW.put(HIERARCHY_1, "2223");
        data2For_EVENT_E_SGEH_ERR_RAW.put(VENDOR, "Ericsson");
        data2For_EVENT_E_SGEH_ERR_RAW.put(HIERARCHY_3, "RNC1");
        data2For_EVENT_E_SGEH_ERR_RAW.put(DURATION, "444");
        insertRow(TEMP_EVENT_E_SGEH_ERR_RAW, data2For_EVENT_E_SGEH_ERR_RAW);
    }

    private void createTables() throws Exception {
        //create DIM_E_SGEH_EVENTTYPE table
        final Collection<String> columnsFor_DIM_E_SGEH_EVENTTYPE = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_EVENTTYPE.add(EVENT_ID);
        columnsFor_DIM_E_SGEH_EVENTTYPE.add(EVENT_ID_DESC);
        createTemporaryTable(TEMP_DIM_E_SGEH_EVENTTYPE, columnsFor_DIM_E_SGEH_EVENTTYPE);

        //create DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL table
        final Collection<String> columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL = new ArrayList<String>();
        columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL.add(HIERARCHY_1);
        columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL.add(HIERARCHY_3);
        columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL.add(VENDOR);
        columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL.add(CID);
        columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL.add(CELL_ID);
        createTemporaryTable(TEMP_DIM_E_SGEH_HIER321_CELL, columnsFor_DIM_E_SGEH_DIM_E_SGEH_HIER321_CELL);

        //create EVENT_E_SGEH_SUC_RAW table
        final Collection<String> columnsFor_EVENT_E_SGEH_SUC_RAW = new ArrayList<String>();
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(IMSI);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(EVENT_TIME);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(EVENT_ID);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(DATETIME_ID);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(TAC);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(RAT);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(HIERARCHY_1);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(HIERARCHY_3);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(VENDOR);
        columnsFor_EVENT_E_SGEH_SUC_RAW.add(DURATION);
        createTemporaryTable(TEMP_EVENT_E_SGEH_SUC_RAW, columnsFor_EVENT_E_SGEH_SUC_RAW);

        //create EVENT_E_SGEH_ERR_RAW table
        final Collection<String> columnsFor_EVENT_E_SGEH_ERR_RAW = new ArrayList<String>();
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(IMSI);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(EVENT_TIME);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(EVENT_ID);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(DATETIME_ID);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(TAC);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(RAT);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(HIERARCHY_1);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(HIERARCHY_3);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(VENDOR);
        columnsFor_EVENT_E_SGEH_ERR_RAW.add(DURATION);
        createTemporaryTable(TEMP_EVENT_E_SGEH_ERR_RAW, columnsFor_EVENT_E_SGEH_ERR_RAW);
    }

}
