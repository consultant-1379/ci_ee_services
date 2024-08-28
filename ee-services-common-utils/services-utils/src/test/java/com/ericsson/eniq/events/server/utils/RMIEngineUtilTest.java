/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.TechPackData.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.distocraft.dc5000.etl.engine.main.ITransferEngineRMI;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;

/**
 * @author eavidat
 * @since 2010
 *
 */
public class RMIEngineUtilTest extends BaseJMockUnitTest {

    private RMIEngineUtils rmiEngineUtils;

    ITransferEngineRMI mockedEngine;

    private final Timestamp FROM_TIME_STAMP = Timestamp.valueOf(getDateTime(-7));

    private final Timestamp TO_TIME_STAMP = Timestamp.valueOf(getDateTime(7));

    @Before
    public void setup() {
        rmiEngineUtils = new RMIEngineUtils();
        mockedEngine = mockery.mock(ITransferEngineRMI.class);
        rmiEngineUtils.setITransferEngineRMI(mockedEngine);
        rmiEngineUtils.populateKeyToViewMaps();
    }

    /**
     * Gets the formatted time for testing
     *
     * @param offset the number of days to be added with today
     *
     * @return formatted date time
     */
    private String getDateTime(final int offset) {

        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final Calendar cal = Calendar.getInstance();
        final Date date = new Date();
        cal.setTime(date);
        cal.add(Calendar.DATE, offset);
        return dateFormat.format(cal.getTime());
    }

    @Test
    public void testgetLatestTableNamesForMSSRawEvents() throws RemoteException {
        final List<String> expectedRawTables = expectGetLatestTableNames(EVENT_E_MSS_VOICE_CDR_ERR_RAW);
        final List<String> result = rmiEngineUtils.getLatestTableNamesForMSSRawEvents(KEY_TYPE_ERR);
        assertThat(result, is(expectedRawTables));
    }

    @Test
    public void testGetTableNamesThatTakesAnyTechPackName() throws RemoteException {
        final String viewName = "EVENT_E_SAMPLE";
        final List<String> result = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        assertThat(rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName), is(result));
    }

    @Test
    public void testGetTableNamesForBothLTEAndSGEHForSUM() throws RemoteException {

        final List<String> lteTableNames = expectCallOnEnginesGetTableNames(EVENT_E_LTE_RAW, FROM_TIME_STAMP,
                TO_TIME_STAMP, 1);
        final List<String> sgehTableNames = expectCallOnEnginesGetTableNames(EVENT_E_SGEH_RAW, FROM_TIME_STAMP,
                TO_TIME_STAMP, 1);

        final String[] viewNames = new String[] { EVENT_E_SGEH_RAW, EVENT_E_LTE_RAW };
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewNames);
        final List<String> expectedResult = new ArrayList<String>();
        expectedResult.addAll(sgehTableNames);
        expectedResult.addAll(lteTableNames);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void testGetTableNamesForBothLTEAndNONLTEForErr() throws RemoteException {

        final List<String> lteTableNames = expectCallOnEnginesGetTableNames("EVENT_E_LTE_ERR_RAW", FROM_TIME_STAMP,
                TO_TIME_STAMP, 1);
        final List<String> sgehTableNames = expectCallOnEnginesGetTableNames("EVENT_E_SGEH_ERR_RAW", FROM_TIME_STAMP,
                TO_TIME_STAMP, 1);

        final String[] viewNames = new String[] { "EVENT_E_LTE_ERR_RAW", "EVENT_E_SGEH_ERR_RAW" };
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewNames);
        final List<String> expectedResult = new ArrayList<String>();
        expectedResult.addAll(lteTableNames);
        expectedResult.addAll(sgehTableNames);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void testGetTableNamesForBothLTEAndNONLTEForSuc() throws RemoteException {

        final List<String> lteTableNames = expectCallOnEnginesGetTableNames("EVENT_E_LTE_SUC_RAW", FROM_TIME_STAMP,
                TO_TIME_STAMP, 1);
        final List<String> sgehTableNames = expectCallOnEnginesGetTableNames("EVENT_E_SGEH_SUC_RAW", FROM_TIME_STAMP,
                TO_TIME_STAMP, 1);
        lteTableNames.addAll(sgehTableNames);
        final String[] viewNames = new String[] { "EVENT_E_LTE_SUC_RAW", "EVENT_E_SGEH_SUC_RAW" };
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewNames);
        final List<String> expectedResult = new ArrayList<String>();
        expectedResult.addAll(lteTableNames);
        expectedResult.addAll(sgehTableNames);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void testSUMKepMapsToEVENT_E_SGEH_RAW_LTE() throws RemoteException {

        final String viewName = EVENT_E_LTE_RAW;
        final List<String> tableNames = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName);
        assertTrue(tableNames != null);
        assertThat(result, is(tableNames));
    }

    @Test
    public void testSUCKepMapsToEVENT_E_SGEH_SUC_RAW_LTE() throws RemoteException {

        final String viewName = "EVENT_E_LTE_SUC_RAW";
        final List<String> tableNames = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName);
        assertTrue(tableNames != null);
        assertThat(result, is(tableNames));
    }

    @Test
    public void testERRKeyMapsToEVENT_E_SGEH_ERR_RAW_LTE() throws RemoteException {
        final String viewName = "EVENT_E_LTE_ERR_RAW";
        final List<String> tableNames = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName);
        assertTrue(result != null);
        assertThat(result, is(tableNames));
    }

    @Test
    public void testTOTALKeyMapsToEVENT_E_SGEH_RAW_LTE() throws RemoteException {
        final String viewName = EVENT_E_LTE_RAW;
        final List<String> tableNames = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName);
        assertTrue(result != null);
        assertThat(result, is(tableNames));
    }

    @Test
    public void testSUMKepMapsToEVENT_E_SGEH_RAW() throws RemoteException {

        final String viewName = EVENT_E_SGEH_RAW;
        final List<String> tableNames = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName);
        assertTrue(tableNames != null);
        assertThat(result, is(tableNames));
    }

    @Test
    public void testSUCKepMapsToEVENT_E_SGEH_SUC_RAW() throws RemoteException {

        final String viewName = "EVENT_E_SGEH_SUC_RAW";
        final List<String> tableNames = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        final List<String> result = this.rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName);
        assertTrue(tableNames != null);
        assertThat(result, is(tableNames));
    }

    private List<String> expectCallOnEnginesGetTableNames(final String viewName, final Timestamp to,
            final Timestamp from, final int numTablesToReturn) throws RemoteException {
        System.out.println("Expecting call for " + viewName);
        final List<String> tableNames = new ArrayList<String>();
        for (int i = 1; i <= numTablesToReturn; i++) {
            final String tableName = viewName + UNDERSCORE + "0" + i;
            tableNames.add(tableName);
            System.out.println("Returnving table: " + tableName);
        }
        mockery.checking(new Expectations() {
            {
                one(mockedEngine).getTableNamesForRawEvents(viewName, to, from);
                will(returnValue(tableNames));
            }
        });
        return tableNames;
    }

    @Test
    public void testERRKeyMapsToEVENT_E_SGEH_ERR_RAW() throws RemoteException {
        final String viewName = "EVENT_E_SGEH_ERR_RAW";
        final List<String> tableNames = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        final List<String> result = rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName);
        assertTrue(result != null);
        assertThat(result, is(tableNames));
    }

    @Test
    public void testTOTALKeyMapsToEVENT_E_SGEH_RAW() throws RemoteException {
        final String viewName = EVENT_E_SGEH_RAW;
        final List<String> tableNames = expectCallOnEnginesGetTableNames(viewName, FROM_TIME_STAMP, TO_TIME_STAMP, 1);
        final List<String> result = rmiEngineUtils.getTableNames(FROM_TIME_STAMP, TO_TIME_STAMP, viewName);
        assertTrue(result != null);
        assertThat(result, is(tableNames));
    }

    @Test
    public void testGetLatestTableNamesForBothLTEAndSGEHRawEvents() throws Exception {
        final String[] viewNames = new String[] { EVENT_E_SGEH_RAW, EVENT_E_LTE_RAW };
        final List<String> sgehTableNames = expectGetLatestTableNames(EVENT_E_SGEH_RAW);
        final List<String> lteTableNames = expectGetLatestTableNames(EVENT_E_LTE_RAW);
        final List<String> tableNames = this.rmiEngineUtils.getLatestTableNames(viewNames);
        final List<String> expectedTableNames = new ArrayList<String>();
        expectedTableNames.addAll(sgehTableNames);
        expectedTableNames.addAll(lteTableNames);
        assertThat(tableNames, is(expectedTableNames));
    }

    @Test
    public void testGetLatestTableNamesForAlarmInfoRaw() throws Exception {
        final String viewNames = DC_Z_ALARM_INFO_RAW;
        final List<String> dimZAlarmNames = expectGetLatestTableNames(DC_Z_ALARM_INFO_RAW);
        final List<String> tableNames = this.rmiEngineUtils.getLatestTableNames(viewNames);
        final List<String> expectedTableNames = new ArrayList<String>();
        expectedTableNames.addAll(dimZAlarmNames);
        assertThat(tableNames, is(expectedTableNames));
    }

    private List<String> expectGetLatestTableNames(final String viewName) throws RemoteException {
        final List<String> rawTables = new ArrayList<String>();
        rawTables.add(viewName + "_01");
        mockery.checking(new Expectations() {
            {
                one(mockedEngine).getLatestTableNamesForRawEvents(viewName);
                will(returnValue(rawTables));
            }
        });
        return rawTables;
    }
}
