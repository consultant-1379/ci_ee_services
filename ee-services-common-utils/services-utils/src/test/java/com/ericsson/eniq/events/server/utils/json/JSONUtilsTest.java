package com.ericsson.eniq.events.server.utils.json;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.utils.json.JSONUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Sequence;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.common.ApplicationConstants;
import com.ericsson.eniq.events.server.common.utils.JSONUtilsConstants;
import com.ericsson.eniq.events.server.logging.ServicesLogger;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.test.util.JSONTestUtils;
import com.ericsson.eniq.events.server.utils.DateTimeUtils;

/**
 * Tests JSON data conversion utilities
 *
 * @author edeccox
 *
 */
public class JSONUtilsTest extends BaseJMockUnitTest {

    private final SimpleDateFormat timeDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private String expectedTimeRangeJson;

    private String dateFrom;

    private String dateTo;

    private String expectedDataTimeFrom;

    private String expectedDataTimeTo;

    @Before
    public void setupTimeRangeParametersForGrids() {
        final long dateFromAsLong = 1277213100000L;
        final long dateFromAsLongPlus1Hour = 1277216700000L;
        expectedDataTimeFrom = Long.toString(dateFromAsLongPlus1Hour);
        final long dateToAsLong = 1277213160000L;
        final long dateToAsLongPlus1Hour = 1277216760000L;
        expectedDataTimeTo = Long.toString(dateToAsLongPlus1Hour);
        dateFrom = DateTimeUtils.getUTCTime(timeDateFormatter.format(new Date(dateFromAsLong)), "+0100",
                DATE_TIME_FORMAT);
        dateTo = DateTimeUtils.getUTCTime(timeDateFormatter.format(new Date(dateToAsLong)), "+0100", DATE_TIME_FORMAT);

        expectedTimeRangeJson = putTogetherExpectedTimeRangeJson(expectedDataTimeFrom, expectedDataTimeTo);
    }

    @Test
    public void testtoSingleValueMap() throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final Map<String, String> values = new HashMap<String, String>();
        final String key1 = "key1";
        final String value1 = "value1";
        final String key2 = "key2";
        final String value2 = "value2";
        values.put(key1, value1);
        values.put(key2, value2);
        setupMockedResultSet(values, mockedResultSet);
        final Map<String, String> result = JSONUtils.toSingleValueMap(mockedResultSet);
        assertThat(result.size(), is(2));
        assertThat(result.containsKey(key1), is(true));
        assertThat(result.get(key1), is(value1));
        assertThat(result.containsKey(key2), is(true));
        assertThat(result.get(key2), is(value2));
    }

    @Test
    public void testtoSingleValueMap_EmptyResultSet() throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final Map<String, String> values = new HashMap<String, String>();
        setupMockedResultSet(values, mockedResultSet);
        final Map<String, String> result = JSONUtils.toSingleValueMap(mockedResultSet);
        assertThat(result.size(), is(0));
    }

    private void setupMockedResultSet(final Map<String, String> values, final ResultSet mockedResultSet)
            throws SQLException {
        for (final String key : values.keySet()) {
            mockery.checking(new Expectations() {
                {
                    one(mockedResultSet).next();
                    will(returnValue(true));
                    one(mockedResultSet).getString(1);
                    will(returnValue(key));
                    one(mockedResultSet).getString(2);
                    will(returnValue(values.get(key)));
                }
            });

        }

        mockery.checking(new Expectations() {
            {
                //and ensure transformer doesn't loop over result set forever
                one(mockedResultSet).next();
                will(returnValue(false));
            }
        });

    }

    @Test
    public void testListToJsonArray() {
        final String firstValue = "4G Core Network KPIs";
        final int secondValue = 5;
        final int thirdValue = -1;
        final String fourthValue = "L_ATTACH_ATTEMPTS";
        final List<Object> values = new ArrayList<Object>();
        values.add(firstValue);
        values.add(secondValue);
        values.add(thirdValue);
        values.add(fourthValue);
        final String result = JSONUtils.listToJsonArray(values);
        final String expectedString = "{\"1\":\"" + firstValue + "\",\"2\":\"" + secondValue + "\",\"3\":\""
                + thirdValue + "\",\"4\":\"" + fourthValue + "\"}";
        assertEquals(expectedString, result);
    }

    @Test
    public void testtoJSONForListCellsConnectedToSACResultsTwoCells() throws SQLException {
        final String cell1 = "celll";
        final String sacId = "206";
        final String vendor = "ericsson";
        final ResultSet mockedResultSet = setUpResultSetExpectationsForOneCellConnectedToSAC(sacId, vendor, cell1);
        final String result = JSONUtils.toJSONForListCellsConnectedToSACResults(mockedResultSet);
        System.out.println(result);
        assertTrue(JSONTestUtils.isValidJson(result));
        final String expectedJson = "{\"success\":\"true\",\"errorDescription\":\"\"," + "\"data\":[{\"1\":\"" + sacId
                + "\",\"2\":\"" + vendor + "\",\"3\":\"" + cell1 + "\"}]}";
        assertEquals(expectedJson, result);
    }

    @Test
    public void testtoJSONForListCellsConnectedToSACResultsOneCellConnected() throws SQLException {
        final String cell1 = "celll";
        final String cell2 = "cell2";
        final String sacId = "206";
        final String vendor = "ericsson";
        final ResultSet mockedResultSet = setUpResultSetExpectationsForTwoCellsConnectedToSAC(sacId, vendor, cell1,
                cell2);
        final String result = JSONUtils.toJSONForListCellsConnectedToSACResults(mockedResultSet);
        System.out.println(result);
        assertTrue(JSONTestUtils.isValidJson(result));
        final String expectedJson = "{\"success\":\"true\",\"errorDescription\":\"\"," + "\"data\":[{\"1\":\"" + sacId
                + "\",\"2\":\"" + vendor + "\",\"3\":\"" + cell1 + "," + cell2 + "\"}]}";
        assertEquals(expectedJson, result);
    }

    @Test
    public void testtoJSONForListCellsConnectedToSACResultsThreeCellsConnected() throws SQLException {
        final String cell1 = "celll";
        final String cell2 = "cell2";
        final String sacId = "206";
        final String vendor = "ericsson";
        final String cell3 = "cell3";
        final ResultSet mockedResultSet = setUpResultSetExpectationsForThreeCellsConnectedToSAC(sacId, vendor, cell1,
                cell2, cell3);
        final String result = JSONUtils.toJSONForListCellsConnectedToSACResults(mockedResultSet);
        System.out.println(result);
        assertTrue(JSONTestUtils.isValidJson(result));
        final String expectedJson = "{\"success\":\"true\",\"errorDescription\":\"\"," + "\"data\":[{\"1\":\"" + sacId
                + "\",\"2\":\"" + vendor + "\",\"3\":\"" + cell1 + "," + cell2 + "," + cell3 + "\"}]}";
        assertEquals(expectedJson, result);
    }

    private ResultSet setUpResultSetExpectationsForOneCellConnectedToSAC(final String sacId, final String vendor,
            final String cell1) throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final ResultSetMetaData mockedResultSetMetaData = mockery.mock(ResultSetMetaData.class);
        mockery.checking(new Expectations() {
            {
                one(mockedResultSet).getMetaData();
                will(returnValue(mockedResultSetMetaData));
                one(mockedResultSetMetaData).getColumnCount();
                will(returnValue(3));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).next();
                will(returnValue(false));

                one(mockedResultSet).getString(1);
                will(returnValue(sacId));
                one(mockedResultSet).getString(2);
                will(returnValue(vendor));
                one(mockedResultSet).getString(3);
                will(returnValue(cell1));

            }
        });
        return mockedResultSet;
    }

    private ResultSet setUpResultSetExpectationsForTwoCellsConnectedToSAC(final String sacId, final String vendor,
            final String cell1, final String cell2) throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final ResultSetMetaData mockedResultSetMetaData = mockery.mock(ResultSetMetaData.class);
        mockery.checking(new Expectations() {
            {
                one(mockedResultSet).getMetaData();
                will(returnValue(mockedResultSetMetaData));
                one(mockedResultSetMetaData).getColumnCount();
                will(returnValue(3));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).next();
                will(returnValue(false));
                one(mockedResultSet).getString(1);
                will(returnValue(sacId));
                one(mockedResultSet).getString(2);
                will(returnValue(vendor));
                one(mockedResultSet).getString(3);
                will(returnValue(cell1));

                one(mockedResultSet).getString(1);
                will(returnValue(sacId));
                one(mockedResultSet).getString(2);
                will(returnValue(vendor));
                one(mockedResultSet).getString(3);
                will(returnValue(cell2));

            }
        });
        return mockedResultSet;
    }

    private ResultSet setUpResultSetExpectationsForThreeCellsConnectedToSAC(final String sacId, final String vendor,
            final String cell1, final String cell2, final String cell3) throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final ResultSetMetaData mockedResultSetMetaData = mockery.mock(ResultSetMetaData.class);
        mockery.checking(new Expectations() {
            {
                one(mockedResultSet).getMetaData();
                will(returnValue(mockedResultSetMetaData));
                one(mockedResultSetMetaData).getColumnCount();
                will(returnValue(3));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).next();
                will(returnValue(false));
                one(mockedResultSet).getString(1);
                will(returnValue(sacId));
                one(mockedResultSet).getString(2);
                will(returnValue(vendor));
                one(mockedResultSet).getString(3);
                will(returnValue(cell1));

                one(mockedResultSet).getString(1);
                will(returnValue(sacId));
                one(mockedResultSet).getString(2);
                will(returnValue(vendor));
                one(mockedResultSet).getString(3);
                will(returnValue(cell2));

                one(mockedResultSet).getString(1);
                will(returnValue(sacId));
                one(mockedResultSet).getString(2);
                will(returnValue(vendor));
                one(mockedResultSet).getString(3);
                will(returnValue(cell3));

            }
        });
        return mockedResultSet;
    }

    private ResultSet setUpResultSetExpectationsForCauseCode() throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final ResultSetMetaData mockedResultSetMetaData = mockery.mock(ResultSetMetaData.class);
        mockery.checking(new Expectations() {
            {

                one(mockedResultSet).findColumn(ApplicationConstants.SCC_HELP_SQL_NAME);
                will(returnValue(8));
                one(mockedResultSet).findColumn(ApplicationConstants.CC_SQL_NAME);
                will(returnValue(4));

                one(mockedResultSet).getMetaData();
                will(returnValue(mockedResultSetMetaData));
                one(mockedResultSetMetaData).getColumnCount();
                will(returnValue(10));
                one(mockedResultSet).getString(1);
                will(returnValue("val11"));
                one(mockedResultSet).getString(2);
                will(returnValue("val12"));
                one(mockedResultSet).getString(3);
                will(returnValue("val13"));
                allowing(mockedResultSet).getString(4);
                will(returnValue("201"));
                allowing(mockedResultSet).getString(5);
                will(returnValue("val5"));
                allowing(mockedResultSet).getString(6);
                will(returnValue("val6"));
                allowing(mockedResultSet).getString(7);
                will(returnValue("val7"));
                allowing(mockedResultSet).getString(8);
                will(returnValue("##203##201##205##[|this is your help|]##32##[|this is not your help|]"));
                allowing(mockedResultSet).getString(9);
                will(returnValue("val9"));
                allowing(mockedResultSet).getString(10);
                will(returnValue("val10"));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).next();
                will(returnValue(false));

            }
        });
        return mockedResultSet;
    }

    private ResultSet setUpResultSetExpectationsForCauseCodeWithExceptions() throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final ResultSetMetaData mockedResultSetMetaData = mockery.mock(ResultSetMetaData.class);
        mockery.checking(new Expectations() {
            {

                one(mockedResultSet).findColumn(ApplicationConstants.SCC_HELP_SQL_NAME);
                will(throwException(new SQLException()));
                one(mockedResultSet).findColumn(ApplicationConstants.CC_SQL_NAME);
                will(throwException(new SQLException()));

                one(mockedResultSet).getMetaData();
                will(returnValue(mockedResultSetMetaData));
                one(mockedResultSetMetaData).getColumnCount();
                will(returnValue(10));
                one(mockedResultSet).getString(1);
                will(returnValue("val11"));
                one(mockedResultSet).getString(2);
                will(returnValue("val12"));
                one(mockedResultSet).getString(3);
                will(returnValue("val13"));
                allowing(mockedResultSet).getString(4);
                will(returnValue("201"));
                allowing(mockedResultSet).getString(5);
                will(returnValue("val5"));
                allowing(mockedResultSet).getString(6);
                will(returnValue("val6"));
                allowing(mockedResultSet).getString(7);
                will(returnValue("val7"));
                allowing(mockedResultSet).getString(8);
                will(returnValue("##203##201##205##[|this is your help|]##32##[|this is not your help|]"));
                allowing(mockedResultSet).getString(9);
                will(returnValue("val9"));
                allowing(mockedResultSet).getString(10);
                will(returnValue("val10"));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).next();
                will(returnValue(false));

            }
        });
        return mockedResultSet;
    }

    @Test
    public void testJSONErrorResult() {
        final String result = JSONUtils.createJSONErrorResult(new IllegalArgumentException("The argument was illegal")
                .getMessage());
        assertEquals("{\"success\":\"false\",\"errorDescription\":\"The argument was illegal\"}", result);

    }

    @Test
    public void testJSONEmptySuccessResult() {
        final String result = JSONUtils.JSONEmptySuccessResult();
        assertEquals("{\"success\":\"true\",\"errorDescription\":\"\",\"data\":[]}", result);

    }

    @Test
    public void testToJSONObjectWithOneRowOfGridDataTimeFieldsNull() throws SQLException {

        final ResultSet rs = mockery.mock(ResultSet.class);
        final Sequence rsSequence = mockery.sequence("mysequence");
        final ResultSetMetaData mt = mockery.mock(ResultSetMetaData.class);

        mockery.checking(new Expectations() {
            {
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnCount();
                will(returnValue(4));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(1);
                will(returnValue(12));
                one(rs).getString(1);
                will(returnValue("val11"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(2);
                will(returnValue(12));
                one(rs).getString(2);
                will(returnValue("val12"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(3);
                will(returnValue(12));
                one(rs).getString(3);
                will(returnValue("val13"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(4);
                will(returnValue(12));
                one(rs).getString(4);
                will(returnValue("val14"));
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(false));
            }

        });
        final String timeColumn = null;
        final String json = JSONUtils.toJSONGridDataObject(rs, null, null, timeColumn, null);
        ServicesLogger.info(getClass().getName(), "testToJSONObjectWithOneRowOfGridDataIncludesTimeFields", json);
        final String expectedTimeRangeWithNulls = putTogetherExpectedTimeRangeJson(null, null, null);
        final String expectedJson = "{" + "\"success\":\"true\"," + "\"errorDescription\":\"\","
                + expectedTimeRangeWithNulls + ",\"data\":["
                + "{\"1\":\"val11\",\"2\":\"val12\",\"3\":\"val13\",\"4\":\"val14\"}" + "]}";
        ServicesLogger.info(getClass().getName(), "testToJSONObjectWithOneRowOfGridDataIncludesTimeFields",
                expectedJson);
        assertEquals(expectedJson, json);
    }

    @Test
    public void testToJSONObjectWithOneRowOfGridDataIncludesTimeRangeFields() throws SQLException {

        final ResultSet rs = mockery.mock(ResultSet.class);
        final Sequence rsSequence = mockery.sequence("mysequence");
        final ResultSetMetaData mt = mockery.mock(ResultSetMetaData.class);

        mockery.checking(new Expectations() {
            {
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnCount();
                will(returnValue(4));
                will(returnValue(4));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(1);
                will(returnValue(12));
                one(rs).getString(1);
                will(returnValue("val11"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(2);
                will(returnValue(12));
                one(rs).getString(2);
                will(returnValue("val12"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(3);
                will(returnValue(12));
                one(rs).getString(3);
                will(returnValue("val13"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(4);
                will(returnValue(12));
                one(rs).getString(4);
                will(returnValue("val14"));
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(false));
            }

        });
        final String timeColumn = null;
        final String json = JSONUtils.toJSONGridDataObject(rs, dateFrom, dateTo, timeColumn, "+0100");
        ServicesLogger.info(getClass().getName(), "testToJSONObjectWithOneRowOfGridDataIncludesTimeFields", json);
        //final String expectedTimeRange = putTogetherExpectedTimeRangeJson(expectedDataTimeFrom, expectedDataTimeTo,
        //      null);
        final String expectedJson = "{" + "\"success\":\"true\"," + "\"errorDescription\":\"\","
                + expectedTimeRangeJson + ",\"data\":["
                + "{\"1\":\"val11\",\"2\":\"val12\",\"3\":\"val13\",\"4\":\"val14\"}" + "]}";
        System.out.println(expectedJson);
        ServicesLogger.info(getClass().getName(), "testToJSONObjectWithOneRowOfGridDataIncludesTimeFields",
                expectedJson);
        assertEquals(expectedJson, json);
    }

    @Test
    public void testJSONStringWithSpecialChar() throws SQLException {

        final ResultSet rs = mockery.mock(ResultSet.class);
        final Sequence rsSequence = mockery.sequence("mysequence");
        final ResultSetMetaData mt = mockery.mock(ResultSetMetaData.class);

        mockery.checking(new Expectations() {
            {
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnCount();
                will(returnValue(2));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(1);
                will(returnValue(12));
                one(rs).getString(1);
                will(returnValue("\"\\"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(2);
                will(returnValue(12));
                one(rs).getString(2);
                will(returnValue("\f\b\"\\"));
                one(rs).next();
                inSequence(rsSequence);
            }
        });
        final String timeColumn = null;
        final String json = JSONUtils.toJSONGridDataObject(rs, dateFrom, dateTo, timeColumn, "+0100");
        ServicesLogger.info(getClass().getName(), "testToJSONObjectWithOneRowOfGridData", json);
        assertEquals("{" + "\"success\":\"true\"," + "\"errorDescription\":\"\"," + expectedTimeRangeJson
                + ",\"data\":[" + "{\"1\":\"\\\"\\\\\",\"2\":\"\\f\\b\\\"\\\\\"}" + "]}", json);
    }

    @Test
    public void testToJSONObjectWithOneRowOfGridData() throws SQLException {

        final ResultSet rs = mockery.mock(ResultSet.class);
        final Sequence rsSequence = mockery.sequence("mysequence");
        final ResultSetMetaData mt = mockery.mock(ResultSetMetaData.class);

        mockery.checking(new Expectations() {
            {
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnCount();
                will(returnValue(4));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(1);
                will(returnValue(12));
                one(rs).getString(1);
                will(returnValue("val11"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(2);
                will(returnValue(12));
                one(rs).getString(2);
                will(returnValue("val12"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(3);
                will(returnValue(12));
                one(rs).getString(3);
                will(returnValue("val13"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(4);
                will(returnValue(12));
                one(rs).getString(4);
                will(returnValue("val14"));
                one(rs).next();
                inSequence(rsSequence);
            }
        });
        final String timeColumn = null;
        final String json = JSONUtils.toJSONGridDataObject(rs, dateFrom, dateTo, timeColumn, "+0100");
        ServicesLogger.info(getClass().getName(), "testToJSONObjectWithOneRowOfGridData", json);
        assertEquals("{" + "\"success\":\"true\"," + "\"errorDescription\":\"\"," + expectedTimeRangeJson
                + ",\"data\":[" + "{\"1\":\"val11\",\"2\":\"val12\",\"3\":\"val13\",\"4\":\"val14\"}" + "]}", json);
    }

    @Test
    public void testToJSONObjectWithEmptyResultSet() throws SQLException {

        final ResultSet rs = mockery.mock(ResultSet.class);
        mockery.checking(new Expectations() {
            {
                one(rs).next();
            }
        });
        final String timeColumn = null;
        assertEquals("{" + "\"success\":\"true\"," + "\"errorDescription\":\"\"," + expectedTimeRangeJson
                + ",\"data\":[]" + // valid JSON
                // representation
                // of empty
                // array
                "}", JSONUtils.toJSONGridDataObject(rs, dateFrom, dateTo, timeColumn, "+0100"));

    }

    @Test
    public void replaceNullValueWithEmptyString() throws SQLException {

        final ResultSet rs = mockery.mock(ResultSet.class);
        final Sequence rsSequence = mockery.sequence("mysequence");
        final ResultSetMetaData mt = mockery.mock(ResultSetMetaData.class);
        final int NCOLS = 2;

        mockery.checking(new Expectations() {
            {
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnCount();
                will(returnValue(NCOLS));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(1);
                will(returnValue(12));
                one(rs).getString(1);
                will(returnValue("val11"));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnType(2);
                will(returnValue(12));
                one(rs).getString(2);
                will(returnValue(null)); // null value returned here
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(false));
            }
        });
        final String timeColumn = null;
        assertEquals("{" + "\"success\":\"true\"," + "\"errorDescription\":\"\"," + expectedTimeRangeJson
                + ",\"data\":[" + "{\"1\":\"val11\",\"2\":\"\"}" + "]}",
                JSONUtils.toJSONGridDataObject(rs, dateFrom, dateTo, timeColumn, "+0100"));

    }

    //    @Test
    public void testToListWithTwoRowsInResultSet() throws Exception {

        final ResultSet rs = mockery.mock(ResultSet.class);
        final Sequence rsSequence = mockery.sequence("mysequence");
        final ResultSetMetaData mt = mockery.mock(ResultSetMetaData.class);

        mockery.checking(new Expectations() {
            {
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getMetaData();
                will(returnValue(mt));
                one(mt).getColumnCount();
                will(returnValue(4));
                one(rs).getString(1);
                will(returnValue("val11"));
                one(rs).getString(2);
                will(returnValue("val12"));
                one(rs).getString(3);
                will(returnValue("val13"));
                one(rs).getString(4);
                will(returnValue("val14"));
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getString(1);
                will(returnValue("val21"));
                one(rs).getString(2);
                will(returnValue("val22"));
                one(rs).getString(3);
                will(returnValue("val23"));
                one(rs).getString(4);
                will(returnValue("val24"));
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(false));

            }
        });

        final List<List<String>> result = JSONUtils.toList(rs);
        assertEquals(2, result.size());

        List<String> row;
        row = result.get(0);
        assertEquals(4, row.size());
        assertEquals("val11", row.get(0));
        assertEquals("val12", row.get(1));
        assertEquals("val13", row.get(2));
        assertEquals("val14", row.get(3));

        row = result.get(1);
        assertEquals(4, row.size());
        assertEquals("val21", row.get(0));
        assertEquals("val22", row.get(1));
        assertEquals("val23", row.get(2));
        assertEquals("val24", row.get(3));

    }

    @Test
    public void testToListWithEmptyResultSet() throws Exception {

        final ResultSet rs = mockery.mock(ResultSet.class);
        mockery.checking(new Expectations() {
            {
                one(rs).next();
                will(returnValue(false));
            }
        });

        final List<List<String>> result = JSONUtils.toList(rs);
        assertEquals(0, result.size());
    }

    //    @Test
    //    public void testToListWithNullResultSet() throws Exception {
    //        final List<List<String>> result = JSONUtils.toList(null);
    //        assertEquals(0, result.size());
    //    }

    @Test
    public void testGetYaxisMaxAndMin() {
        final List<List<String>> data = new ArrayList<List<String>>();

        List<String> row;

        row = new ArrayList<String>();
        row.add("12.11");
        row.add("13.11");
        row.add("14.22");
        row.add("15.33");
        row.add("16.44");
        row.add("17.11");
        row.add("18.55");

        data.add(row);
        row = new ArrayList<String>();
        row.add("25.22");
        row.add("26.33");
        row.add("27.33");
        row.add("28.33");
        row.add("29.22");
        row.add("30.22");
        row.add("31.22");
        data.add(row);
        final Map<String, String> values = JSONUtils.getYaxisMaxAndMinValue(data, "7", "6");
        assertEquals("12.11", values.get(Y_AXIS_MIN));
        assertEquals("29.22", values.get(Y_AXIS_MAX));
    }

    private String putTogetherExpectedTimeRangeJson(final String dataTimeFrom, final String dataTimeTo,
            final String expectedTimeZoneToUse) {
        return "\"dataTimeFrom\":\"" + dataTimeFrom + "\",\"dataTimeTo\":\"" + dataTimeTo + "\",\"timeZone\":\""
                + expectedTimeZoneToUse + "\"";
    }

    private String putTogetherExpectedTimeRangeJson(final String inExpectedDataTimeFrom,
            final String inExpectedDataTimeTo) {
        return putTogetherExpectedTimeRangeJson(inExpectedDataTimeFrom, inExpectedDataTimeTo,
                DateTimeUtils.getUTCOffset());
    }

    @Test
    public void testToJSONChartDataObjectForSubBIBusyDay() throws SQLException {

        final ResultSet rs = mockery.mock(ResultSet.class);
        final Sequence rsSequence = mockery.sequence("mysequence");

        mockery.checking(new Expectations() {
            {
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getString(1);
                will(returnValue("2"));
                one(rs).getString(2);
                will(returnValue("0"));
                one(rs).getString(3);
                will(returnValue("Friday"));
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getString(1);
                will(returnValue("7"));
                one(rs).getString(2);
                will(returnValue("7"));
                one(rs).getString(3);
                will(returnValue("Sunday"));
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getString(1);
                will(returnValue("7"));
                one(rs).getString(2);
                will(returnValue("7"));
                one(rs).getString(3);
                will(returnValue("Friday"));

                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(false));

            }
        });

        final String json = "{" + JSONUtils.toJsonGridDataArrayForSubBIBusy(rs, SUBBI_BUSY_DAY, "+0700") + "}";
        ServicesLogger.info(getClass().getName(), "testToJSONChartDataObjectForSubBIBusyDay", json);
        assertNotNull(json);
    }

    @Test
    public void testToJSONChartDataObjectForSubBIBusyHour() throws SQLException {

        final ResultSet rs = mockery.mock(ResultSet.class);
        final Sequence rsSequence = mockery.sequence("mysequence");

        mockery.checking(new Expectations() {
            {
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getString(1);
                will(returnValue("2"));
                one(rs).getString(2);
                will(returnValue("0"));
                one(rs).getString(3);
                will(returnValue("7"));
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(true));
                one(rs).getString(1);
                will(returnValue("2"));
                one(rs).getString(2);
                will(returnValue("2"));
                one(rs).getString(3);
                will(returnValue("7"));
                one(rs).next();
                inSequence(rsSequence);
                will(returnValue(false));
            }
        });

        final String json = "{" + JSONUtils.toJsonGridDataArrayForSubBIBusy(rs, SUBBI_BUSY_HOUR, "+0700") + "}";
        ServicesLogger.info(getClass().getName(), "testToJSONChartDataObjectForSubBIBusyHour", json);
        assertNotNull(json);
    }

    @Test
    public void toJSONGridDataObjectForCauseCodeWithOneRowOfGridData() throws SQLException {

        final ResultSet mockedResultSet = setUpResultSetExpectationsForCauseCode();

        final String json = JSONCauseCodeUtils.toJSONGridDataObjectForCauseCode(mockedResultSet, dateFrom, dateTo,
                null, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        ServicesLogger.info(getClass().getName(), "testToJSONObjectWithOneRowOfGridData", json);
        final String expectedJson = "{"
                + "\"success\":\"true\","
                + "\"errorDescription\":\"\","
                + expectedTimeRangeJson
                + ",\"data\":["
                + "{\"1\":\"val11\",\"2\":\"val12\",\"3\":\"val13\",\"4\":\"201\",\"5\":\"val5\",\"6\":\"val6\",\"7\":\"val7\",\"8\":\"this is your help\",\"9\":\"val9\",\"10\":\"val10\"}"
                + "]}";
        assertEquals(expectedJson, json);
    }

    @Test
    public void toJSONGridDataObjectForCauseCodeWithOneRowOfGridDataWithExceptions() throws SQLException {

        final ResultSet mockedResultSet = setUpResultSetExpectationsForCauseCodeWithExceptions();

        final String json = JSONCauseCodeUtils.toJSONGridDataObjectForCauseCode(mockedResultSet, dateFrom, dateTo,
                null, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        ServicesLogger.info(getClass().getName(), "testToJSONObjectWithOneRowOfGridData", json);
        final String expectedJson = "{"
                + "\"success\":\"true\","
                + "\"errorDescription\":\"\","
                + expectedTimeRangeJson
                + ",\"data\":["
                + "{\"1\":\"val11\",\"2\":\"val12\",\"3\":\"val13\",\"4\":\"201\",\"5\":\"val5\",\"6\":\"val6\",\"7\":\"val7\",\"8\":\"##203##201##205##[|this is your help|]##32##[|this is not your help|]\",\"9\":\"val9\",\"10\":\"val10\"}"
                + "]}";
        assertEquals(expectedJson, json);
    }

    @Test
    public void testFormatValue() {
        final String valueFromDatabase = "\n\rTEST";
        final String valueFromDatabase2 = "\r\nTEST";
        final String valueFromDatabase3 = "\rTEST";
        final String valueFromDatabase4 = "\nTEST";
        final String valueFromDatabase5 = "\nTEST\r";
        final String expectedValueFromDatabase = JSONUtilsConstants.INVERTED_COMMAS.concat("TEST").concat(
                JSONUtilsConstants.INVERTED_COMMAS);

        assertEquals(expectedValueFromDatabase, JSONUtils.formatValue(valueFromDatabase));
        assertEquals(expectedValueFromDatabase, JSONUtils.formatValue(valueFromDatabase2));
        assertEquals(expectedValueFromDatabase, JSONUtils.formatValue(valueFromDatabase3));
        assertEquals(expectedValueFromDatabase, JSONUtils.formatValue(valueFromDatabase4));
        assertEquals(expectedValueFromDatabase, JSONUtils.formatValue(valueFromDatabase5));

        //testing the condition, when value from database is null
        final String expectedValueFromDatabase2 = JSONUtilsConstants.INVERTED_COMMAS.concat("").concat(
                JSONUtilsConstants.INVERTED_COMMAS);

        assertEquals(expectedValueFromDatabase2, JSONUtils.formatValue(null));
    }

    @Test
    public void testJSONNoLicensedTechPacksError_OneTechPack() {
        final List<String> applicableTechPacks = new ArrayList<String>();
        final String featureName = "MyFeature";
        applicableTechPacks.add(featureName);
        final String json = JSONUtils.JSONNoLicensedFeaturesError(applicableTechPacks);
        assertThat(JSONTestUtils.isValidJson(json), is(true));
        System.out.println(json);
        assertThat(json, is("{\"success\":\"false\",\"errorDescription\":\"The feature " + featureName
                + " is not licensed\"}"));
    }

    @Test
    public void testJSONNoLicensedTechPacksError_TwoTechPacks() {
        final List<String> applicableTechPacks = new ArrayList<String>();
        final String feature1 = "MyNiceFeature1";
        applicableTechPacks.add(feature1);
        final String feature2 = "MyNiceFeature2";
        applicableTechPacks.add(feature2);
        final String json = JSONUtils.JSONNoLicensedFeaturesError(applicableTechPacks);
        assertThat(JSONTestUtils.isValidJson(json), is(true));
        System.out.println(json);
        assertThat(json, is("{\"success\":\"false\",\"errorDescription\":\"The features " + feature1 + COMMA + SPACE
                + feature2 + " are not licensed\"}"));
    }

    @Test
    public void testToMap() throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final String key1 = "France";
        final String value1 = "Paris";
        final String key2 = "England";
        final String value2_1 = "London";
        final String value_2_2 = "Manchester";
        final String value2 = value2_1 + COMMA + value_2_2;

        mockery.checking(new Expectations() {
            {
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).getString(1);
                will(returnValue(key1));
                one(mockedResultSet).getString(2);
                will(returnValue(value1));
                one(mockedResultSet).next();
                will(returnValue(true));
                one(mockedResultSet).getString(1);
                will(returnValue(key2));
                one(mockedResultSet).getString(2);
                will(returnValue(value2));
                one(mockedResultSet).next();
                will(returnValue(false));
            }
        });
        final Map<String, List<String>> result = JSONUtils.toMap(mockedResultSet);
        assertThat(result.size(), is(2));

        assertThat(result.containsKey(key1), is(true));
        final List<String> valuesForKey1 = result.get(key1);
        assertThat(valuesForKey1.size(), is(1));
        assertThat(valuesForKey1.contains(value1), is(true));

        assertThat(result.containsKey(key2), is(true));
        final List<String> valuesForKey2 = result.get(key2);
        assertThat(valuesForKey2.size(), is(2));
        assertThat(valuesForKey2.contains(value2_1), is(true));
        assertThat(valuesForKey2.contains(value_2_2), is(true));
    }

    @Test
    public void JSONDataSuccessResultTest() {
        final String payLoadString = "{\"LatestRop\": \"6487575\"}";

        final String expectedJSON = "{\"success\":\"true\",\"errorDescription\":\"\",\"data\":[{\"LatestRop\": \"6487575\"}]}";

        assertEquals(expectedJSON, JSONDataSuccessResult(payLoadString));
    }
}
