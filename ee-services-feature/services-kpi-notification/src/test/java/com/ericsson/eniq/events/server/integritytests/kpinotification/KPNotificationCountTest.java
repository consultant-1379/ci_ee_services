/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.kpinotification;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.KPINotificationCountService;
import com.ericsson.eniq.events.server.test.queryresults.KPINotificationCountResult;
import com.ericsson.eniq.events.server.test.util.DateTimeUtilities;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import java.sql.SQLException;
import java.util.*;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.serviceprovider.impl.kpinotification.constants.KPINotificationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DC_Z_ALARM_INFO_RAW;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**                                                               ;
 * @author eemecoy
 *
 */
public class KPNotificationCountTest extends BaseDataIntegrityTest<KPINotificationCountResult> {

    private KPINotificationCountService kpiNotificationCountService;

    private final int numberCriticalAlarms = 3;

    private final int numberMajorAlarms = 2;

    private final int numberMinorAlarms = 4;

    private final int numberWarningAlarms = 5;

    @Before
    public void setup() throws Exception {
        kpiNotificationCountService = new KPINotificationCountService();
        attachDependencies(kpiNotificationCountService);
        createAlarmTable();
    }

    private void createAlarmTable() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(PERCEIVED_SEVERITY_TEXT);
        columns.add(Event_Date);
        createTemporaryTable(TEMP_DC_Z_ALARM_INFO_RAW, columns);
        insertData();
    }

    private void insertData() throws SQLException {
        insertMultipleRowsIntoAlarmTable(CRITICAL, numberCriticalAlarms);
        insertMultipleRowsIntoAlarmTable(MAJOR, numberMajorAlarms);
        insertMultipleRowsIntoAlarmTable(MINOR, numberMinorAlarms);
        insertMultipleRowsIntoAlarmTable(WARNING, numberWarningAlarms);
    }

    private void insertMultipleRowsIntoAlarmTable(final String severityText, final int numberAlarms)
            throws SQLException {
        for (int i = 0; i < numberAlarms; i++) {
            insertRowIntoAlarmTable(severityText);
        }
    }

    private void insertRowIntoAlarmTable(final String perceivedServityText) throws SQLException {
        final String timestamp = DateTimeUtilities.getDateTimeMinus36Hours();
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(PERCEIVED_SEVERITY_TEXT, perceivedServityText);
        values.put(Event_Date, timestamp);
        insertRow(TEMP_DC_Z_ALARM_INFO_RAW, values);
    }

    @Test
    public void testRunningQuery() throws Exception {
        final MultivaluedMap<String, String> parameters = new MultivaluedMapImpl();
        parameters.putSingle(TIME_QUERY_PARAM, ONE_WEEK);
        parameters.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        final String json = runQuery(kpiNotificationCountService, parameters);
        validateResult(json);
    }

    private void validateResult(final String json) throws Exception {
        final List<KPINotificationCountResult> results = getTranslator().translateResult(json,
                KPINotificationCountResult.class);
        assertThat(results.size(), is(4));

        final KPINotificationCountResult criticalAlarms = getAlarmResult(results, CRITICAL);
        assertThat(criticalAlarms.getNumberAlarms(), is(numberCriticalAlarms));

        final KPINotificationCountResult majorAlarms = getAlarmResult(results, MAJOR);
        assertThat(majorAlarms.getNumberAlarms(), is(numberMajorAlarms));

        final KPINotificationCountResult minorAlarms = getAlarmResult(results, MINOR);
        assertThat(minorAlarms.getNumberAlarms(), is(numberMinorAlarms));

        final KPINotificationCountResult warningAlarms = getAlarmResult(results, WARNING);
        assertThat(warningAlarms.getNumberAlarms(), is(numberWarningAlarms));

    }

    private KPINotificationCountResult getAlarmResult(final List<KPINotificationCountResult> results,
            final String alarmText) {
        for (final KPINotificationCountResult result : results) {
            if (result.getPerceivedSeverityText().equals(alarmText)) {
                return result;
            }
        }
        fail("Could not find result of type " + alarmText);
        return null;
    }

}
