/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.json;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.test.util.JSONAssertUtils;
import com.ericsson.eniq.events.server.test.util.JSONTestUtils;

/**
 * @author eemecoy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:AnnotationConfiguration-context.xml" })
public class JSONLiveLoadUtilsTest extends BaseJMockUnitTest {

    private static final String RAT_TYPE_3G = "3G";

    private static final String RAT_TYPE_GSM = "GSM";

    @Autowired
    JSONAssertUtils jsonAssertUtils;

    @Before
    public void setup() {
        allowCallsForRatMappings();
    }

    @Test
    public void testToJsonLiveLoadHandsetsMetadataUI() throws SQLException {
        final ResultSet mockedResultSet = mockery.mock(ResultSet.class);
        final String[] handsetManufacturers = new String[] { "Nokia", "Siemens" };
        setUpExpectationsForHandsetDataOnResultSet(mockedResultSet, handsetManufacturers);
        final String servicesUrl = "servicesURL";
        final String handsetPath = "handset path";
        final String result = JSONLiveLoadUtils.toJSONLiveLoadHandsetsMetadataUI(mockedResultSet, servicesUrl,
                handsetPath);
        final String expectedResult = putTogetherExpectedJSON(handsetManufacturers, servicesUrl, handsetPath);
        System.out.println(result);
        assertEquals(expectedResult, result);
    }

    private String putTogetherExpectedJSON(final String[] handsetManufacturers, final String servicesUrl,
            final String handsetPath) {
        final StringBuilder expectedResult = new StringBuilder();
        expectedResult.append("{\"success\":\"true\",\"errorDescription\":\"\",\"data\":[");
        for (final String manufacturer : handsetManufacturers) {
            expectedResult.append("{\"id\" : \"");
            expectedResult.append(manufacturer);
            expectedResult.append("\",\"liveLoadURL\" : \"" + servicesUrl + handsetPath + "?id=");
            expectedResult.append(manufacturer);
            expectedResult.append("\"},");
        }

        expectedResult.append("]}");
        return expectedResult.toString();
    }

    private void setUpExpectationsForHandsetDataOnResultSet(final ResultSet mockedResultSet,
            final String[] handsetManufacturers) throws SQLException {
        final ResultSetMetaData mockedMetaData = mockery.mock(ResultSetMetaData.class);
        mockery.checking(new Expectations() {
            {
                one(mockedResultSet).getMetaData();
                will(returnValue(mockedMetaData));
                one(mockedMetaData).getColumnCount();
                will(returnValue(1));

                for (final String manufacturer : handsetManufacturers) {
                    one(mockedResultSet).next();
                    will(returnValue(true));
                    one(mockedResultSet).getString(1);
                    will(returnValue(manufacturer));
                }

                //to exit loop
                one(mockedResultSet).next();
            }
        });

    }

    private void allowCallsForRatMappings() {
        mockery.checking(new Expectations() {
            {
            }
        });

    }

    @Test
    public void testJsonEscapeDoubleQuote() {
        final String testJson = "{\"totalCount\":\"1\",\"APN\" : [{\"id\" : \"${ARG}\"}]}";
        final String badValue = "\"";

        assertFalse(JSONTestUtils.isValidJson(testJson.replace("${ARG}", badValue)));
        final String escapedValue = JSONUtils.jsonEscapedString(badValue);

        final String checkJson = testJson.replace("${ARG}", escapedValue);
        assertTrue(checkJson, JSONTestUtils.isValidJson(checkJson));

    }

    @Test
    public void testJsonEscapeBackSlash() {
        final String testJson = "{\"totalCount\":\"1\",\"APN\" : [{\"id\" : \"${ARG}\"}]}";
        final String badValue = "\\";

        assertFalse(JSONTestUtils.isValidJson(testJson.replace("${ARG}", badValue)));
        final String escapedValue = JSONUtils.jsonEscapedString(badValue);

        final String checkJson = testJson.replace("${ARG}", escapedValue);
        assertTrue(checkJson, JSONTestUtils.isValidJson(checkJson));
    }

    @Test
    public void testJsonEscapeForwardSlash() {
        final String testJson = "{\"totalCount\":\"1\",\"APN\" : [{\"id\" : \"${ARG}\"}]}";
        final String badValue = "/";

        assertTrue(JSONTestUtils.isValidJson(testJson.replace("${ARG}", badValue)));
        final String escapedValue = JSONUtils.jsonEscapedString(badValue);

        final String checkJson = testJson.replace("${ARG}", escapedValue);
        assertTrue(checkJson, JSONTestUtils.isValidJson(checkJson));
    }

    @Test
    public void testLiveLoadPagingOnlyReturnsLast2RowsOutOf3WhenPagingLimitIs2AndPagingIndexIs1() throws SQLException {
        final ResultSet rs = mockery.mock(ResultSet.class);
        final String pagingIndex = "1";
        final String pagingLimit = "2";
        final String callbackId = "some callback id";
        final String cell1 = "CELL123";
        final String ratType = RAT_TYPE_3G;
        final String bsc1 = "BSC1";
        final String vendor = "ericsson";
        final String cell2 = "cell2";
        final String bsc2 = "bsc2";
        final String cell3 = "cell3";
        final String bsc3 = "bsc3";

        final int numberRowsInFirstPage = 1;
        final String[][] cellBscMappingsFromFirstPage = new String[numberRowsInFirstPage][2];
        cellBscMappingsFromFirstPage[0] = new String[] { cell1, bsc1 };

        final int numberRowsInThisPage = 2;
        final String[][] cellBscMappingsForThisPage = new String[numberRowsInThisPage][2];
        cellBscMappingsForThisPage[0] = new String[] { cell2, bsc2 };
        cellBscMappingsForThisPage[1] = new String[] { cell3, bsc3 };

        final int totalNumberRowsInDB = numberRowsInFirstPage + numberRowsInThisPage;

        setUpExpectationsForLiveLoadForLatterPages(rs, vendor, ratType, cellBscMappingsFromFirstPage,
                cellBscMappingsForThisPage);

        final String nodeType = "CELL";
        final String ratTypeStringFormat = "3G";
        final String dataForSecondCell = putTogetherDataStringForOneCell(cell2, bsc2, vendor, ratTypeStringFormat);
        final String dataForThirdCell = putTogetherDataStringForOneCell(cell3, bsc3, vendor, ratTypeStringFormat);
        final String result = JSONLiveLoadUtils.toJSONLiveLoad(nodeType, callbackId, pagingLimit, pagingIndex, rs);
        assertEquals(callbackId + "({\"totalCount\":\"" + totalNumberRowsInDB + "\",\"" + nodeType + "\" : ["
                + dataForSecondCell + "," + dataForThirdCell + "]})", result);
    }

    private void setUpExpectationsForLiveLoadForLatterPages(final ResultSet rs, final String vendor,
            final String ratType, final String[][] cellBscMappingsForFirstPage,
            final String[][] cellBscMappingsForThisPage) throws SQLException {
        final ResultSetMetaData metaData = mockery.mock(ResultSetMetaData.class);
        mockery.checking(new Expectations() {
            {
                one(rs).getMetaData();
                will(returnValue(metaData));
                one(metaData).getColumnCount();
                will(returnValue(4));

                for (final String[] element : cellBscMappingsForFirstPage) {
                    one(rs).next();
                    will(returnValue(true));
                }

                for (final String[] element : cellBscMappingsForThisPage) {
                    one(rs).getString(1);
                    will(returnValue(element[0]));
                    one(rs).getString(2);
                    will(returnValue(element[1]));
                    one(rs).getString(3);
                    will(returnValue(vendor));
                    one(rs).getString(4);
                    will(returnValue(ratType.toString()));
                    one(rs).next();
                    will(returnValue(true));
                }
                one(rs).next();

            }
        });

    }

    @Test
    public void testLiveLoadPagingOnlyReturnsFirst2RowsWhenPagingLimitIs2() throws SQLException {
        final ResultSet rs = mockery.mock(ResultSet.class);
        final String pagingIndex = "0";
        final String pagingLimit = "2";
        final String callbackId = "some callback id";
        final String cell1 = "CELL123";
        final String ratType = RAT_TYPE_GSM;
        final String bsc1 = "BSC1";
        final String vendor = "ericsson";
        final String cell2 = "cell2";
        final String bsc2 = "bsc2";

        final int numberRowsInFirstPage = 2;
        final String[][] validCellBscMappings = new String[numberRowsInFirstPage][2];
        validCellBscMappings[0] = new String[] { cell1, bsc1 };
        validCellBscMappings[1] = new String[] { cell2, bsc2 };
        final int numberRowsInSecondPage = 1;
        final String[][] cellBscMappingsExceedingPageLimit = new String[numberRowsInSecondPage][2];
        cellBscMappingsExceedingPageLimit[0] = new String[] { "cell that won't be returned in this page",
                "bsc that won't be returned in this page" };
        final int totalNumberRowsInResult = numberRowsInFirstPage + numberRowsInSecondPage;
        setUpExpectationsForLiveLoadWhereItsOnFirstPage(rs, vendor, ratType, validCellBscMappings,
                cellBscMappingsExceedingPageLimit);

        final String nodeType = "CELL";
        final String ratTypeStringFormat = "GSM";
        final String dataForFirstCell = putTogetherDataStringForOneCell(cell1, bsc1, vendor, ratTypeStringFormat);
        final String dataForSecondCell = putTogetherDataStringForOneCell(cell2, bsc2, vendor, ratTypeStringFormat);
        final String result = JSONLiveLoadUtils.toJSONLiveLoad(nodeType, callbackId, pagingLimit, pagingIndex, rs);
        final String string = callbackId + "({\"totalCount\":\"" + totalNumberRowsInResult + "\",\"" + nodeType
                + "\" : [" + dataForFirstCell + "," + dataForSecondCell + "]})";
        assertEquals(string, result);
    }

    @Test
    public void testLiveLoadPagingAllRowsReturned() throws SQLException {
        final ResultSet rs = mockery.mock(ResultSet.class);
        final String pagingIndex = "0";
        final String pagingLimit = "20";
        final String callbackId = "some callback id";
        final String cell1 = "CELL123";
        final String ratType = RAT_TYPE_GSM;
        final String bsc1 = "BSC1";
        final String vendor = "ericsson";
        final String cell2 = "cell2";
        final String bsc2 = "bsc2";
        final String cell3 = "cell3";
        final String bsc3 = "bsc3";

        final String[][] cellBscMappings = new String[3][2];
        cellBscMappings[0] = new String[] { cell1, bsc1 };
        cellBscMappings[1] = new String[] { cell2, bsc2 };
        cellBscMappings[2] = new String[] { cell3, bsc3 };
        setUpExpectationsForLiveLoadWhereItsOnFirstPage(rs, vendor, ratType, cellBscMappings, null);

        final String nodeType = "CELL";
        final String result = JSONLiveLoadUtils.toJSONLiveLoad(nodeType, callbackId, pagingLimit, pagingIndex, rs);
        final String ratTypeStringFormat = "GSM";
        final String dataForFirstCell = putTogetherDataStringForOneCell(cell1, bsc1, vendor, ratTypeStringFormat);
        final String dataForSecondCell = putTogetherDataStringForOneCell(cell2, bsc2, vendor, ratTypeStringFormat);
        final String dataForThirdCell = putTogetherDataStringForOneCell(cell3, bsc3, vendor, ratTypeStringFormat);

        final String numberRows = "3";
        assertEquals(callbackId + "({\"totalCount\":\"" + numberRows + "\",\"" + nodeType + "\" : [" + dataForFirstCell
                + "," + dataForSecondCell + "," + dataForThirdCell + "]})", result);
    }

    private void setUpExpectationsForLiveLoadWhereItsOnFirstPage(final ResultSet rs, final String vendor,
            final String ratType, final String[][] bscCellMappings, final String[][] cellBscMappingsExceedingPageLimit)
            throws SQLException {
        final ResultSetMetaData metaData = mockery.mock(ResultSetMetaData.class);
        mockery.checking(new Expectations() {
            {

                one(rs).getMetaData();
                will(returnValue(metaData));
                one(metaData).getColumnCount();
                will(returnValue(4));

                for (final String[] bscCellMapping : bscCellMappings) {
                    one(rs).getString(1);
                    will(returnValue(bscCellMapping[0]));
                    one(rs).getString(2);
                    will(returnValue(bscCellMapping[1]));
                    one(rs).getString(3);
                    will(returnValue(vendor));
                    one(rs).getString(4);
                    will(returnValue(ratType.toString()));
                    one(rs).next();
                    will(returnValue(true));
                }

                if (cellBscMappingsExceedingPageLimit != null && cellBscMappingsExceedingPageLimit.length > 0) {
                    for (final String[] element : cellBscMappingsExceedingPageLimit) {
                        one(rs).next();
                        will(returnValue(true));
                    }
                    one(rs).next();
                } else {
                    one(rs).next();
                }
            }
        });
    }

    private String putTogetherDataStringForOneCell(final String cell, final String bsc, final String vendor,
            final String ratTypeStringFormat) {
        return "{\"id\":\"" + cell + "," + bsc + "," + vendor + "," + ratTypeStringFormat + "\"}";
    }
}
