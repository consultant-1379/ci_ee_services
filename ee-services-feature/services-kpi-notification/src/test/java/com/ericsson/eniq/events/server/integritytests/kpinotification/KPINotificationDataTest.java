/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.kpinotification;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.KPINotificationDataService;
import com.ericsson.eniq.events.server.test.queryresults.KPINotificationDataResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.constants.KPINotificationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DC_Z_ALARM_INFO_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author eemecoy
 *
 */
public class KPINotificationDataTest extends BaseDataIntegrityTest<KPINotificationDataResult> {

    private KPINotificationDataService kpiNotificationDataService;

    private List<String> columnsInAlarmTable;

    private String utcTimestampForFirstRow;

    private final String timeZoneOffset = TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;

    private String utcTimestampForSecondRow;

    private String localTimestampForFirstRow;

    private String localTimestampForSecondRow;

    @Before
    public void setup() throws Exception {
        initialise();
        utcTimestampForFirstRow = DateTimeUtilities.getDateTimeMinus36Hours();
        localTimestampForFirstRow = DateTimeUtilities.getDateTimeMinus36HoursWithOffSet(1);
        utcTimestampForSecondRow = DateTimeUtilities.getDateTimeMinus48Hours();
        localTimestampForSecondRow = DateTimeUtilities.getDateTimeMinus48HoursWithOffSet(1);
        kpiNotificationDataService = new KPINotificationDataService();
        attachDependencies(kpiNotificationDataService);
        createTemporaryTable(TEMP_DC_Z_ALARM_INFO_RAW, columnsInAlarmTable);
        insertDataIntoAlarmTable();
    }

    @Test
    public void runTest() throws Exception {
        final MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
        parameters.putSingle(NOTIFICATION_SEVERITY_PARAM, MAJOR);
        parameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        parameters.putSingle(TZ_OFFSET, timeZoneOffset);
        final String json = runQuery(kpiNotificationDataService, parameters);
        validateResult(json);
    }

    private void validateResult(final String json) throws Exception {
        final List<KPINotificationDataResult> results = getTranslator().translateResult(json,
                KPINotificationDataResult.class);
        assertThat(results.size(), is(2));
        final KPINotificationDataResult latestNotification = results.get(0);
        assertThat(latestNotification.getDateTime(), is(appendZeroMilliseconds(localTimestampForFirstRow)));

        final KPINotificationDataResult earliestNotification = results.get(1);
        assertThat(earliestNotification.getDateTime(), is(appendZeroMilliseconds(localTimestampForSecondRow)));
    }

    private void insertDataIntoAlarmTable() throws SQLException {
        insertRowIntoAlarmTable(utcTimestampForFirstRow, localTimestampForFirstRow);
        insertRowIntoAlarmTable(utcTimestampForSecondRow, localTimestampForSecondRow);
    }

    private void insertRowIntoAlarmTable(final String utcTimestamp, final String localTimestamp) throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(PERCEIVED_SEVERITY, MAJOR_INTEGER_VALUE);
        values.put(Event_Date, utcTimestamp);
        values.put(DATETIME_ID, localTimestamp);
        provideDefaultValuesForColumns(values, columnsInAlarmTable);
        insertRow(TEMP_DC_Z_ALARM_INFO_RAW, values);
    }

    private void initialise() {
        columnsInAlarmTable = new ArrayList<String>();
        columnsInAlarmTable.add(ADDITIONALTEXT);
        columnsInAlarmTable.add(ADDITIONALINFORMATION);
        columnsInAlarmTable.add(MONITOREDATTRIBUTEVALUES);
        columnsInAlarmTable.add(MONITOREDATTRIBUTES);
        columnsInAlarmTable.add(THRESHOLDINFORMATION);
        columnsInAlarmTable.add(MANAGEDOBJECTCLASS);
        columnsInAlarmTable.add(MANAGEDOBJECTINSTANCE);
        columnsInAlarmTable.add(PERCEIVED_SEVERITY);
        columnsInAlarmTable.add(DATETIME_ID);
        columnsInAlarmTable.add(Event_Date);

    }

}
