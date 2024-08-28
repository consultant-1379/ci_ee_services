/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.resources;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CANCEL_REQ_NOT_SUPPORTED;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.COMMA;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TOPOLOGY;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_CELL;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.net.URISyntaxException;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Ignore;

import com.ericsson.eniq.events.server.query.resultsettransformers.ResultSetTransformer;
import com.ericsson.eniq.events.server.services.DataService;
import com.ericsson.eniq.events.server.templates.mappingengine.TemplateMappingEngine;
import com.ericsson.eniq.events.server.templates.utils.TemplateUtils;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.test.stubs.DummyUriInfoImpl;
import com.ericsson.eniq.events.server.utils.QueryUtils;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 *
 */
@Ignore
//Need fixing platform code of no class found error
public class TopologyResourceMockedTest extends BaseJMockUnitTest {

    private TopologyResource topologyResource;

    QueryUtils mockedQueryUtils;

    DataService mockedDataService;

    TemplateUtils mockedTemplateUtils;

    TemplateMappingEngine mockedTemplateMappingEngine;

    @Before
    public void setup() {
        topologyResource = new TopologyResource();
        mockedQueryUtils = mockery.mock(QueryUtils.class);
        topologyResource.queryUtils = mockedQueryUtils;
        mockedTemplateUtils = mockery.mock(TemplateUtils.class);
        topologyResource.templateUtils = mockedTemplateUtils;
        mockedDataService = mockery.mock(DataService.class);
        topologyResource.dataService = mockedDataService;
        mockedTemplateMappingEngine = mockery.mock(TemplateMappingEngine.class);
        topologyResource.templateMappingEngine = mockedTemplateMappingEngine;
    }

    @Ignore
    public void testListConnectedCellsPassesOnlySACParameterToQueryAndDropsVendorRNCRATInfo() throws URISyntaxException {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        final String cell = "7951";
        final String rnc = "RNC01";
        final String vendor = "Ericsson";
        final String rat = "0";
        requestParameters.putSingle(NODE_PARAM, cell + COMMA + rnc + COMMA + vendor + COMMA + rat);
        DummyUriInfoImpl.setUriInfo(requestParameters, topologyResource);
        final String templateFromEngine = expectGetTemplateFromEngine(TOPOLOGY, requestParameters, null);
        expectCheckValidValuesOnQueryUtils(requestParameters);

        final String query = expectGetQueryOnQueryUtils(templateFromEngine);

        final String expectedResult = expectGetGridDataOnDataService(query);

        final String result = topologyResource.getListOfConnectedCells();
        assertThat(result, is(expectedResult));
    }

    private String expectGetTemplateFromEngine(final String templatePath,
            final MultivaluedMap<String, String> requestParameters, final String drillType) {
        final String templateToUse = "some template file";
        mockery.checking(new Expectations() {
            {
                one(mockedTemplateMappingEngine).getTemplate(templatePath, requestParameters, drillType);
                will(returnValue(templateToUse));
            }
        });
        return templateToUse;
    }

    private void expectCheckValidValuesOnQueryUtils(final MultivaluedMap<String, String> requestParameters) {
        mockery.checking(new Expectations() {
            {
                one(mockedQueryUtils).checkValidValue(requestParameters);
                will(returnValue(true));
            }
        });
    }

    private String expectGetGridDataOnDataService(final String query) {
        final String queryResult = "result of query";
        mockery.checking(new Expectations() {
            {
                one(mockedDataService).getData(with(equal(CANCEL_REQ_NOT_SUPPORTED)), with(equal(query)),
                        with(any(Map.class)), with(any(ResultSetTransformer.class)));
                will(returnValue(queryResult));
            }
        });
        return queryResult;
    }

    private String expectGetQueryOnQueryUtils(final String queryTemplate) {
        final String queryToRun = "select *";
        mockery.checking(new Expectations() {
            {
                one(mockedTemplateUtils).getQueryFromTemplate(queryTemplate);
                will(returnValue(queryToRun));
            }
        });
        return queryToRun;
    }
}
