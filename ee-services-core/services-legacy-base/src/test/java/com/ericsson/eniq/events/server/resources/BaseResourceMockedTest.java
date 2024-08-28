/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import com.ericsson.eniq.events.server.common.EventDataSourceType;
import com.ericsson.eniq.events.server.common.tablesandviews.AggregationTableInfo;
import com.ericsson.eniq.events.server.services.exclusivetacs.ExclusiveTACHandler;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.test.util.JSONAssertUtils;
import com.ericsson.eniq.events.server.test.util.JSONTestUtils;
import com.ericsson.eniq.events.server.utils.FormattedDateTimeRange;
import com.ericsson.eniq.events.server.utils.MediaTypeHandler;
import com.ericsson.eniq.events.server.utils.techpacks.RawTableFetcher;
import com.ericsson.eniq.events.server.utils.techpacks.TechPackListFactory;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.common.MessageConstants.E_INVALID_OR_MISSING_PARAMS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author eemecoy
 *
 */
public class BaseResourceMockedTest extends BaseJMockUnitTest {

    private static final MediaType APPLICATION_CSV_MEDIA_TYPE = new MediaType("application", "csv");

    private StubbedBaseResource objToTest;

    FormattedDateTimeRange mockedFormattedDateTimeRange;

    final String startDateTime = "2010-10-11 13:00";

    final String endDateTime = "2010-10-11 14:00";

    TechPackListFactory mockedTechPackTableFactory;

    RawTableFetcher mockedRawTableFetcher;

    private JSONAssertUtils jsonAssertUtils = new JSONAssertUtils();

    @Before
    public void setup() {
        objToTest = new StubbedBaseResource();
        mockedRawTableFetcher = mockery.mock(RawTableFetcher.class);
        objToTest.setRawTableFetcher(mockedRawTableFetcher);

        objToTest.setMediaTypeHandler(new MediaTypeHandler());
        mockedTechPackTableFactory = mockery.mock(TechPackListFactory.class);
        objToTest.setTechPackListFactory(mockedTechPackTableFactory);
        mockedFormattedDateTimeRange = mockery.mock(FormattedDateTimeRange.class);
        allowGetStartAndEndDateTimeOnDateTimeRange();
        final ExclusiveTACHandler exclusiveTACHandler = setUpMockedExclusiveTACHandler();
        objToTest.setExclusiveTACHandler(exclusiveTACHandler);
    }

    private ExclusiveTACHandler setUpMockedExclusiveTACHandler() {
        final ExclusiveTACHandler exclusiveTACHandler = mockery.mock(ExclusiveTACHandler.class);
        mockery.checking(new Expectations() {
            {
                allowing(exclusiveTACHandler).queryIsExclusiveTacRelated(null, null);
                will(returnValue(false));
                allowing(exclusiveTACHandler).queryIsExclusiveTacRelated(EXCLUSIVE_TAC_GROUP, null);
                will(returnValue(true));
            }

        });
        return exclusiveTACHandler;
    }

    @Test
    public void testupdateTemplateWithErrRAWTablesForLTEAndNONLTEQueriesRMIEEngine() {
        final Map<String, Object> templateParameters = new HashMap<String, Object>();

        final String key = KEY_TYPE_ERR;
        final List<String> listOfTables = new ArrayList<String>();
        listOfTables.add("rawLteTable1");
        listOfTables.add("rawTable2");
        final Map<String, String> parameterMapForOrdinaryTables = new HashMap<String, String>();
        parameterMapForOrdinaryTables.put(KEY_PARAM, KEY_TYPE_ERR);

        final Map<String, String> parameterMapForLTETables = new HashMap<String, String>();
        parameterMapForLTETables.put(KEY_PARAM, KEY_TYPE_ERR);
        parameterMapForLTETables.put(IS_LTE_VIEW, "true");

        mockery.checking(new Expectations() {
            {

                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, ERR, RAW_ALL_ERR_TABLES);
                will(returnValue(listOfTables));
            }
        });
        final FormattedDateTimeRange dateTimeRange = mockedFormattedDateTimeRange;
        final boolean result = objToTest.updateTemplateWithRAWTables(templateParameters, dateTimeRange, key,
                RAW_ALL_ERR_TABLES);
        assertThat(result, is(true));
        assertThat((List<String>) templateParameters.get(RAW_ALL_ERR_TABLES), is(listOfTables));

    }

    @Test
    public void testshouldQueryUseAggregationViewWithNullGroupNameAndNoAgg() {
        objToTest.aggregationViews.put(TYPE_APN, new AggregationTableInfo("some agg table",
                EventDataSourceType.AGGREGATED_DAY));
        final String timerange = EventDataSourceType.AGGREGATED_15MIN.toString();
        expectsOnTechPackTableFactory(TYPE_APN, timerange, objToTest.aggregationViews, false);
        assertThat(objToTest.shouldQueryUseAggregationView(TYPE_APN, timerange, null), is(false));
    }

    private void expectsOnTechPackTableFactory(final String type, final String timerange,
            final Map<String, AggregationTableInfo> aggregationViews, final boolean valueToReturn) {
        mockery.checking(new Expectations() {
            {
                one(mockedTechPackTableFactory).shouldQueryUseAggregationView(type, timerange, aggregationViews);
                will(returnValue(valueToReturn));
            }
        });

    }

    @Test
    public void testshouldQueryUseAggregationViewWithNullGroupNameAndAggExists() {
        objToTest.aggregationViews.put(TYPE_APN, new AggregationTableInfo("some agg table",
                EventDataSourceType.AGGREGATED_DAY));
        final String timerange = EventDataSourceType.AGGREGATED_DAY.toString();
        expectsOnTechPackTableFactory(TYPE_APN, timerange, objToTest.aggregationViews, true);
        assertThat(objToTest.shouldQueryUseAggregationView(TYPE_APN, timerange, null), is(true));
    }

    @Test
    public void testshouldQueryUseAggregationViewForExclusiveTacGroupIsFalse() {
        assertThat(objToTest.shouldQueryUseAggregationView(null, null, EXCLUSIVE_TAC_GROUP), is(false));
    }

    @Test
    public void testisImsiOrImsiGroupQueryForIMSIGroupQuery() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(GROUP_NAME_PARAM, SAMPLE_IMSI_GROUP);
        assertThat(objToTest.isImsiOrImsiGroupQuery(requestParameters), is(true));
    }

    @Test
    public void testisImsiOrImsiGroupQueryForIMSIQuery() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        requestParameters.putSingle(IMSI_PARAM, String.valueOf(SAMPLE_IMSI));
        assertThat(objToTest.isImsiOrImsiGroupQuery(requestParameters), is(true));
    }

    @Test
    public void testisImsiOrImsiGroupQueryForPTMSIQuery() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_PTMSI);
        requestParameters.putSingle(PTMSI_PARAM, "98989");
        assertThat(objToTest.isImsiOrImsiGroupQuery(requestParameters), is(false));
    }

    @Test
    public void testupdateTemplateWithErrRAWTablesForLTEAndNONLTE() {
        final Map<String, Object> templateParameters = new HashMap<String, Object>();

        final String key = KEY_TYPE_ERR;
        final List<String> listOfTables = new ArrayList<String>();
        listOfTables.add("rawLteTable1");
        listOfTables.add("rawTable2");
        final Map<String, String> parameterMapForOrdinaryTables = new HashMap<String, String>();
        parameterMapForOrdinaryTables.put(KEY_PARAM, KEY_TYPE_ERR);

        final Map<String, String> parameterMapForLTETables = new HashMap<String, String>();
        parameterMapForLTETables.put(KEY_PARAM, KEY_TYPE_ERR);
        parameterMapForLTETables.put(IS_LTE_VIEW, "true");

        mockery.checking(new Expectations() {
            {

                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_ALL_ERR_TABLES);
                will(returnValue(listOfTables));
            }
        });
        final FormattedDateTimeRange dateTimeRange = mockedFormattedDateTimeRange;
        final boolean result = objToTest.updateTemplateWithRAWTables(templateParameters, dateTimeRange, key,
                RAW_ALL_ERR_TABLES);
        assertThat(result, is(true));
        assertThat((List<String>) templateParameters.get(RAW_ALL_ERR_TABLES), is(listOfTables));

    }

    @Test
    public void testupdateTemplateWithErrRAWTablesIncludingLTEReturnsTrueWhenOrdinaryTablesFoundButNoLTETablesFound() {
        final Map<String, Object> templateParameters = new HashMap<String, Object>();

        final String key = KEY_TYPE_ERR;
        final List<String> emptyList = new ArrayList<String>();
        final Map<String, String> parameterMapForOrdinaryTables = new HashMap<String, String>();
        parameterMapForOrdinaryTables.put(KEY_PARAM, KEY_TYPE_ERR);

        final Map<String, String> parameterMapForLTETables = new HashMap<String, String>();
        parameterMapForLTETables.put(KEY_PARAM, KEY_TYPE_ERR);
        parameterMapForLTETables.put(IS_LTE_VIEW, "true");
        parameterMapForLTETables.put(IS_COMBINED_VIEW, "false");
        parameterMapForLTETables.put(IS_DT_VIEW, "false");
        parameterMapForLTETables.put(IS_DTPDP_VIEW, "false");

        final List<String> listOfNonLteTables = new ArrayList<String>();
        listOfNonLteTables.add("rawTable2");
        mockery.checking(new Expectations() {
            {
                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_LTE_TABLES);

                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_NON_LTE_TABLES);
                will(returnValue(listOfNonLteTables));
            }
        });
        final FormattedDateTimeRange dateTimeRange = mockedFormattedDateTimeRange;
        final boolean result = objToTest.updateTemplateWithRAWTables(templateParameters, dateTimeRange, key,
                RAW_LTE_TABLES, RAW_NON_LTE_TABLES);
        assertThat(result, is(true));
        assertThat((List<String>) templateParameters.get(RAW_LTE_TABLES), is(emptyList));
        assertThat((List<String>) templateParameters.get(RAW_NON_LTE_TABLES), is(listOfNonLteTables));

    }

    @Test
    public void testupdateTemplateWithErrRAWTablesIncludingLTEReturnsFalseWhenNoTablesFound() {
        final Map<String, Object> templateParameters = new HashMap<String, Object>();

        final String key = KEY_TYPE_ERR;
        final Map<String, String> parameterMapForOrdinaryTables = new HashMap<String, String>();
        parameterMapForOrdinaryTables.put(KEY_PARAM, KEY_TYPE_ERR);
        parameterMapForOrdinaryTables.put(IS_LTE_VIEW, "false");
        parameterMapForOrdinaryTables.put(IS_COMBINED_VIEW, "false");
        parameterMapForOrdinaryTables.put(IS_DT_VIEW, "false");
        parameterMapForOrdinaryTables.put(IS_DTPDP_VIEW, "false");

        final Map<String, String> parameterMapForLTETables = new HashMap<String, String>();
        parameterMapForLTETables.put(KEY_PARAM, KEY_TYPE_ERR);
        parameterMapForLTETables.put(IS_LTE_VIEW, "true");
        parameterMapForLTETables.put(IS_COMBINED_VIEW, "false");
        parameterMapForLTETables.put(IS_DT_VIEW, "false");
        parameterMapForLTETables.put(IS_DTPDP_VIEW, "false");

        mockery.checking(new Expectations() {
            {
                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_LTE_TABLES);
                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_NON_LTE_TABLES);
            }
        });
        final FormattedDateTimeRange dateTimeRange = mockedFormattedDateTimeRange;
        final boolean result = objToTest.updateTemplateWithRAWTables(templateParameters, dateTimeRange, key,
                RAW_LTE_TABLES, RAW_NON_LTE_TABLES);
        assertThat(result, is(false));

    }

    @Test
    public void testupdateTemplateWithErrRAWTablesForLTE() {
        final Map<String, Object> templateParameters = new HashMap<String, Object>();

        final String key = KEY_TYPE_ERR;
        final List<String> rawNonLteTableNames = new ArrayList<String>();
        rawNonLteTableNames.add("rawErrTable1");
        final List<String> rawLTETableNames = new ArrayList<String>();
        rawLTETableNames.add("rawLteErrTable2");
        mockery.checking(new Expectations() {
            {
                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_LTE_TABLES);
                will(returnValue(rawLTETableNames));
                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_NON_LTE_TABLES);
                will(returnValue(rawNonLteTableNames));
            }
        });
        final FormattedDateTimeRange dateTimeRange = mockedFormattedDateTimeRange;
        final boolean result = objToTest.updateTemplateWithRAWTables(templateParameters, dateTimeRange, key,
                RAW_LTE_TABLES, RAW_NON_LTE_TABLES);
        assertThat(result, is(true));
        assertThat((List<String>) templateParameters.get(RAW_LTE_TABLES), is(rawLTETableNames));
        assertThat((List<String>) templateParameters.get(RAW_NON_LTE_TABLES), is(rawNonLteTableNames));

    }

    @Test
    public void testupdateTemplateWithErrRAWTablesReturnsFalseWhenNoTablesFound() {
        final Map<String, Object> templateParameters = new HashMap<String, Object>();
        final String key = KEY_TYPE_ERR;
        final Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put(KEY_PARAM, KEY_TYPE_ERR);
        parameterMap.put(IS_LTE_VIEW, "false");
        parameterMap.put(IS_COMBINED_VIEW, "false");
        parameterMap.put(IS_DT_VIEW, "false");
        parameterMap.put(IS_DTPDP_VIEW, "false");

        mockery.checking(new Expectations() {
            {
                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_ERR_TABLES);
            }
        });
        final FormattedDateTimeRange dateTimeRange = mockedFormattedDateTimeRange;
        final boolean result = objToTest.updateTemplateWithRAWTables(templateParameters, dateTimeRange, key,
                RAW_ERR_TABLES);
        assertThat(result, is(false));

    }

    @Test
    public void testupdateTemplateWithErrRAWTables() {
        final Map<String, Object> templateParameters = new HashMap<String, Object>();

        final String key = KEY_TYPE_ERR;
        final List<String> rawTableNames = new ArrayList<String>();
        rawTableNames.add("rawErrTable1");
        mockery.checking(new Expectations() {
            {
                one(mockedRawTableFetcher).getRAWTables(mockedFormattedDateTimeRange, key, RAW_ERR_TABLES);
                will(returnValue(rawTableNames));
            }
        });
        final FormattedDateTimeRange dateTimeRange = mockedFormattedDateTimeRange;
        final boolean result = objToTest.updateTemplateWithRAWTables(templateParameters, dateTimeRange, key,
                RAW_ERR_TABLES);
        assertThat(result, is(true));
        assertThat((List<String>) templateParameters.get(RAW_ERR_TABLES), is(rawTableNames));

    }

    @Test
    public void testisMediaTypeApplicationCSVWhenItsJson() {
        final List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON_TYPE);
        final HttpHeaders mockedHttpHeaders = mockHttpHeadersAndExpectCall(acceptableMediaTypes);
        objToTest.setHttpHeaders(mockedHttpHeaders);
        assertThat(objToTest.isMediaTypeApplicationCSV(), is(false));
    }

    @Test
    public void testisMediaTypeApplicationCSVIsFalseWhenListIsNull() {
        final List<MediaType> acceptableMediaTypes = null;
        final HttpHeaders mockedHttpHeaders = mockHttpHeadersAndExpectCall(acceptableMediaTypes);
        objToTest.setHttpHeaders(mockedHttpHeaders);
        assertThat(objToTest.isMediaTypeApplicationCSV(), is(false));
    }

    @Test
    public void testisMediaTypeApplicationCSVWhenMoreThanOneAcceptableMediaTypeAndContainsApplicationCSV() {
        final List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_ATOM_XML_TYPE);
        addMediaCSV(acceptableMediaTypes);
        acceptableMediaTypes.add(MediaType.APPLICATION_SVG_XML_TYPE);
        final HttpHeaders mockedHttpHeaders = mockHttpHeadersAndExpectCall(acceptableMediaTypes);
        objToTest.setHttpHeaders(mockedHttpHeaders);
        assertThat(objToTest.isMediaTypeApplicationCSV(), is(true));
    }

    @Test
    public void testisMediaTypeApplicationCSVWhenMoreThanOneAcceptableMediaTypeAndDoesNotContainApplicationCSV() {
        final List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_ATOM_XML_TYPE);
        acceptableMediaTypes.add(MediaType.APPLICATION_SVG_XML_TYPE);
        final HttpHeaders mockedHttpHeaders = mockHttpHeadersAndExpectCall(acceptableMediaTypes);
        objToTest.setHttpHeaders(mockedHttpHeaders);
        assertThat(objToTest.isMediaTypeApplicationCSV(), is(false));
    }

    @Test
    public void testisMediaTypeApplicationCSVWhenItIs() {
        final List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        addMediaCSV(acceptableMediaTypes);
        final HttpHeaders mockedHttpHeaders = mockHttpHeadersAndExpectCall(acceptableMediaTypes);
        objToTest.setHttpHeaders(mockedHttpHeaders);
        assertThat(objToTest.isMediaTypeApplicationCSV(), is(true));
    }

    /**
     * @param acceptableMediaTypes
     */
    private void addMediaCSV(final List<MediaType> acceptableMediaTypes) {
        acceptableMediaTypes.add(APPLICATION_CSV_MEDIA_TYPE);
    }

    private HttpHeaders mockHttpHeadersAndExpectCall(final List<MediaType> acceptableMediaTypes) {
        final HttpHeaders mockedHttpHeaders = mockery.mock(HttpHeaders.class);
        mockery.checking(new Expectations() {
            {
                one(mockedHttpHeaders).getAcceptableMediaTypes();
                will(returnValue(acceptableMediaTypes));

            }
        });

        return mockedHttpHeaders;
    }

    @Test
    public void testCheckRequiredParametersExistWhenTheyDo() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(BSC_PARAM, "BSC1");
        requestParameters.putSingle(CAUSE_CODE_PARAM, "3");
        requestParameters.putSingle(TIME_QUERY_PARAM, "30");
        final String[] requiredParameters = new String[] { BSC_PARAM, CAUSE_CODE_PARAM };
        assertThat(objToTest.checkRequiredParametersExistAndReturnErrorMessage(requestParameters, requiredParameters),
                is((String) null));
    }

    @Test
    public void testCheckRequiredParametersExistWhenOneMissing() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(BSC_PARAM, "BSC1");
        requestParameters.putSingle(TIME_QUERY_PARAM, "30");
        final String[] requiredParameters = new String[] { BSC_PARAM, CAUSE_CODE_PARAM };
        final String result = objToTest.checkRequiredParametersExistAndReturnErrorMessage(requestParameters,
                requiredParameters);
        jsonAssertUtils.assertJSONErrorResult(result,getClass().getName());
        jsonAssertUtils.assertResultContains(result, E_INVALID_OR_MISSING_PARAMS,getClass().getName());
        jsonAssertUtils.assertResultContains(result, CAUSE_CODE_PARAM,getClass().getName());
    }

    @Test
    public void testCheckRequiredParametersExistWhenTwoMissing() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_QUERY_PARAM, "30");
        final String[] requiredParameters = new String[] { BSC_PARAM, CAUSE_CODE_PARAM };
        final String result = objToTest.checkRequiredParametersExistAndReturnErrorMessage(requestParameters,
                requiredParameters);
        jsonAssertUtils.assertJSONErrorResult(result,getClass().getName());
        jsonAssertUtils.assertResultContains(result, E_INVALID_OR_MISSING_PARAMS,getClass().getName());
        jsonAssertUtils.assertResultContains(result, CAUSE_CODE_PARAM,getClass().getName());
        jsonAssertUtils.assertResultContains(result, BSC_PARAM,getClass().getName());
    }

    @Test
    public void testGetCountValueValidInput() {

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(MAX_ROWS, "300");
        final int count = objToTest.getCountValue(map, 500);
        assertEquals(300, count);
    }

    @Test
    public void testGetCountValueInputGreaterThanMax() {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(MAX_ROWS, "700");
        final int count = objToTest.getCountValue(map, 500);
        assertEquals(500, count);
    }

    @Test
    public void testGetCountValueInvalidInput() {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(MAX_ROWS, "FAIL");
        final int count = objToTest.getCountValue(map, 500);
        assertEquals(500, count);
    }

    private void allowGetStartAndEndDateTimeOnDateTimeRange() {
        mockery.checking(new Expectations() {
            {
                allowing(mockedFormattedDateTimeRange).getStartDateTime();
                will(returnValue(startDateTime));
                allowing(mockedFormattedDateTimeRange).getEndDateTime();
                will(returnValue(endDateTime));
            }
        });

    }

    class StubbedBaseResource extends BaseResource {

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.resources.BaseResource#getData(javax.ws.rs.core.MultivaluedMap)
         */
        @Override
        protected String getData(final String requestID, final MultivaluedMap<String, String> requestParameters)
                throws WebApplicationException {
            return null;
        }

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.resources.BaseResource#checkParameters(javax.ws.rs.core.MultivaluedMap)
         */
        @Override
        protected List<String> checkParameters(final MultivaluedMap<String, String> requestParameters) {
            // TODO Auto-generated method stub
            return null;
        }

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.resources.BaseResource#isValidValue(javax.ws.rs.core.MultivaluedMap)
         */
        @Override
        protected boolean isValidValue(final MultivaluedMap<String, String> requestParameters) {
            // TODO Auto-generated method stub
            return false;
        }

    }

}
