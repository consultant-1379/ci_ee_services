/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import com.ericsson.eniq.events.server.services.DataService;
import com.ericsson.eniq.events.server.templates.mappingengine.TemplateMappingEngine;
import com.ericsson.eniq.events.server.templates.utils.TemplateUtils;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.utils.QueryUtils;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.jmock.Expectations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

/**
 * @author eemecoy
 * @author epesmit
 *
 */

public class LiveLoadResourceMockedTest extends BaseJMockUnitTest {

    private static String LOGGED_URI = "http://sample.uri.com";

    private LiveLoadResource liveLoadResource;

    UriInfo mockedURIInfo;

    QueryUtils mockedQueryUtils;

    DataService mockedDataService;

    TemplateUtils mockedTemplateUtils;

    TemplateMappingEngine mockedTemplateMappingEngine;

    ApplicationConfigManager mockApplicationConfigManager;

    @Before
    public void setUp() {
        mockedURIInfo = mockery.mock(UriInfo.class);
        liveLoadResource = new LiveLoadResource();
        mockedQueryUtils = mockery.mock(QueryUtils.class);
        liveLoadResource.queryUtils = mockedQueryUtils;
        mockedTemplateUtils = mockery.mock(TemplateUtils.class);
        liveLoadResource.templateUtils = mockedTemplateUtils;
        mockedDataService = mockery.mock(DataService.class);
        liveLoadResource.dataService = mockedDataService;
        mockApplicationConfigManager = mockery.mock(ApplicationConfigManager.class);
        liveLoadResource.applicationConfigManager = mockApplicationConfigManager;
        mockedTemplateMappingEngine = mockery.mock(TemplateMappingEngine.class);
        liveLoadResource.templateMappingEngine = mockedTemplateMappingEngine;
        expectLoggingUriInfo();

    }

    @Test
    public void testGetLiveLoadSgsn() {
        liveLoadResource.uriInfo = mockedURIInfo;
        final MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        final String callBackId = "callBackId1";

        queryParams.putSingle(LIVELOAD_CALLBACK_PARAM, callBackId);

        final String pagingIndex = "3";
        queryParams.putSingle(PAGING_START_KEY, pagingIndex);

        final String pagingLimit = "10";
        queryParams.putSingle(PAGING_LIMIT_KEY, pagingLimit);

        expectGetQueryParametersOnUriInfo(queryParams);

        final Map<String, String> expectedTemplateParameters = new HashMap<String, String>();
        final int limit = 20;
        expectedTemplateParameters.put("limit", String.valueOf(limit));

        final String templateToUse = expectGetTemplateOnTemplateMappingEngine("LIVELOAD/SGSN");

        final String expectedQuery = "query that should be run";
        expectGetQueryFromTemplateOnQueryUtils(templateToUse, expectedTemplateParameters, expectedQuery);
        final String jsonToReturn = "expectedJSON";
        expectGetLiveLoadOnDataService(expectedQuery, "SGSN", callBackId, pagingLimit, pagingIndex, jsonToReturn, limit);

        final String result = liveLoadResource.getLiveLoadSgsn();
        Assert.assertEquals(jsonToReturn, result);
    }

    private String expectGetTemplateOnTemplateMappingEngine(final String templatePath) {
        final String templateToUse = "some template";
        mockery.checking(new Expectations() {
            {
                one(mockedTemplateMappingEngine).getTemplate(with(equal(templatePath)),
                        with(any(MultivaluedMap.class)), with(equal((String) null)));
                will(returnValue(templateToUse));
            }
        });
        return templateToUse;

    }

    @Test
    public void testGetLiveLoadHandsetMakesIDSpecified() {
        liveLoadResource.uriInfo = mockedURIInfo;
        final MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        final String callBackId = "callBackId1";
        queryParams.putSingle(LIVELOAD_CALLBACK_PARAM, callBackId);
        final String pagingIndex = "3";
        queryParams.putSingle(PAGING_START_KEY, pagingIndex);
        final String pagingLimit = "10";
        queryParams.putSingle(PAGING_LIMIT_KEY, pagingLimit);
        final String makeId = "3";
        queryParams.putSingle("id", makeId);
        expectGetQueryParametersOnUriInfo(queryParams);

        final Map<String, String> expectedTemplateParameters = new HashMap<String, String>();
        final int limit = 20;
        expectedTemplateParameters.put("limit", String.valueOf(limit));
        expectedTemplateParameters.put("id", makeId);

        final String templateToUse = expectGetTemplateOnTemplateMappingEngine("LIVELOAD/HANDSET_MAKES");
        final String expectedQuery = "query that should be run";
        expectGetQueryFromTemplateOnQueryUtils(templateToUse, expectedTemplateParameters, expectedQuery);
        final String jsonToReturn = "expectedJSON";
        expectGetLiveLoadOnDataService(expectedQuery, makeId, callBackId, pagingLimit, pagingIndex, jsonToReturn, limit);
        final String result = liveLoadResource.getLiveLoadHandsetMakes();
        Assert.assertEquals(jsonToReturn, result);
    }

    @Test
    public void testGetLiveLoadNodesbsc() {
        liveLoadResource.uriInfo = mockedURIInfo;
        final MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        final String callBackId = "callBackId1";
        queryParams.putSingle(LIVELOAD_CALLBACK_PARAM, callBackId);
        final String pagingIndex = "3";
        queryParams.putSingle(PAGING_START_KEY, pagingIndex);
        final String pagingLimit = "10";
        queryParams.putSingle(PAGING_LIMIT_KEY, pagingLimit);
        expectGetQueryParametersOnUriInfo(queryParams);

        final Map<String, String> expectedTemplateParameters = new HashMap<String, String>();
        final int limit = 20;
        expectedTemplateParameters.put("limit", String.valueOf(limit));

        final String templateToUse = expectGetTemplateOnTemplateMappingEngine("LIVELOAD/bsc");
        final String expectedQuery = "query that should be run";
        expectGetQueryFromTemplateOnQueryUtils(templateToUse, expectedTemplateParameters, expectedQuery);
        final String liveLoadType = "bsc";
        final String jsonToReturn = "expectedJSON";
        expectGetLiveLoadOnDataService(expectedQuery, liveLoadType.toUpperCase(), callBackId, pagingLimit, pagingIndex,
                jsonToReturn, limit);
        final String result = liveLoadResource.getLiveLoadNodes(liveLoadType);
        Assert.assertEquals(jsonToReturn, result);
    }

    @Test
    public void testGetLiveLoadNodesBSC() {
        liveLoadResource.uriInfo = mockedURIInfo;
        final MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        final String callBackId = "callBackId1";
        queryParams.putSingle(LIVELOAD_CALLBACK_PARAM, callBackId);

        final String pagingIndex = "3";
        queryParams.putSingle(PAGING_START_KEY, pagingIndex);

        final String pagingLimit = "10";
        queryParams.putSingle(PAGING_LIMIT_KEY, pagingLimit);
        expectGetQueryParametersOnUriInfo(queryParams);

        final Map<String, String> expectedTemplateParameters = new HashMap<String, String>();
        final int limit = 20;
        expectedTemplateParameters.put("limit", String.valueOf(limit));

        final String expectedQuery = "query that should be run";

        final String templateToUse = expectGetTemplateOnTemplateMappingEngine("LIVELOAD/BSC");

        expectGetQueryFromTemplateOnQueryUtils(templateToUse, expectedTemplateParameters, expectedQuery);

        final String liveLoadType = "BSC";
        final String jsonToReturn = "expectedJSON";
        expectGetLiveLoadOnDataService(expectedQuery, liveLoadType, callBackId, pagingLimit, pagingIndex, jsonToReturn,
                limit);

        final String result = liveLoadResource.getLiveLoadNodes(liveLoadType);
        Assert.assertEquals(jsonToReturn, result);
    }

  @Test
  public void testDoesStringEndwithComma() {
      final String queryString = "1209,";
      final boolean expectedResult = true;
      final boolean actualResult = liveLoadResource.doesStringEndWithComma(queryString);
      Assert.assertEquals(expectedResult, actualResult);
  }

  @Test
  public void testRemoveTrailingComma() {
      final String stringWithComma = "comma,";
      final String expectedResult = "comma";
      final String actualResult = liveLoadResource.removeTrailingComma(stringWithComma);
      Assert.assertEquals(expectedResult, actualResult);
  }

  @Test
  public void testTrimQueryParameterComma() {
      final MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
      final String query = "1208,";
      queryParams.putSingle(LIVELOAD_QUERY_PARAM, query);
      final String expectedResult = "1208";
      liveLoadResource.trimQueryParameterComma(queryParams);
      final String actualResult = queryParams.getFirst(LIVELOAD_QUERY_PARAM);
      Assert.assertEquals(expectedResult, actualResult);

  }

    private void expectGetLiveLoadOnDataService(final String expectedQuery, final String expectedLiveLoadType,
            final String expectedCallbackId, final String expectedPagingLimit, final String expectedPagingIndex,
            final String jsonToReturn, final int expectedLiveLoadLimit) {

        mockery.checking(new Expectations() {
            {
                one(mockedDataService).getLiveLoad(expectedQuery, expectedLiveLoadType, expectedCallbackId,
                        expectedPagingLimit, expectedPagingIndex);
                will(returnValue(jsonToReturn));
                one(mockApplicationConfigManager).getLiveLoadCount();
                will(returnValue(expectedLiveLoadLimit));
            }
        });

    }

    private void expectGetQueryFromTemplateOnQueryUtils(final String templateFile,
            final Map<String, String> templateParameters, final String queryToReturn) {
        mockery.checking(new Expectations() {
            {
                one(mockedTemplateUtils).getQueryFromTemplate(templateFile, templateParameters);
                will(returnValue(queryToReturn));
            }
        });
    }

    private void expectGetQueryParametersOnUriInfo(final MultivaluedMap<String, String> queryParams) {
        mockery.checking(new Expectations() {
            {
                one(mockedURIInfo).getQueryParameters(true);
                will(returnValue(queryParams));
            }
        });
    }

    private void expectLoggingUriInfo() {
        try {
            mockery.checking(new Expectations() {
                {
                    allowing(mockedURIInfo).getRequestUri();
                    will(returnValue(new URI(LOGGED_URI)));
                }
            });
        } catch (final URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
