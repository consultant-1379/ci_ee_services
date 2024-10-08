/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.piechart;

import java.util.List;
import javax.ws.rs.core.MultivaluedMap;

import com.ericsson.eniq.events.server.resources.TestsWithTemporaryTablesBaseTestCase;
import com.ericsson.eniq.events.server.resources.piechart.CauseCodeAnalysisPieChartResource;
import com.ericsson.eniq.events.server.test.queryresults.PieChartCauseCodeAnalysisResult;
import com.ericsson.eniq.events.server.test.stubs.DummyUriInfoImpl;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_CODE_ANALYSIS;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.CAUSE_CODE_PIE_CHART;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.NODE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.PATH_SEPARATOR;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_APN;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_BSC;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_CELL;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TYPE_SGSN;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static com.ericsson.eniq.events.server.integritytests.piechart.PieChartCauseCodePopulator.TEST_APN_1;
import static com.ericsson.eniq.events.server.integritytests.piechart.PieChartCauseCodePopulator.TEST_BSC_1;
import static com.ericsson.eniq.events.server.integritytests.piechart.PieChartCauseCodePopulator.TEST_CELL_1;
import static com.ericsson.eniq.events.server.integritytests.piechart.PieChartCauseCodePopulator.TEST_SGSN_1;
import static com.ericsson.eniq.events.server.integritytests.piechart.PieChartCauseCodePopulator.causeCode_1_LTE;
import static com.ericsson.eniq.events.server.integritytests.piechart.PieChartCauseCodePopulator.causeCode_1_SGEH;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.CAUSE_CODE_IDS;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.THIRTY_MINUTES;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR;

public class PieChartCauseCodeAnalysisWithPreparedTablesTest extends
        TestsWithTemporaryTablesBaseTestCase<PieChartCauseCodeAnalysisResult> {

    private final CauseCodeAnalysisPieChartResource causeCodeAnalysisPieChartResource = new CauseCodeAnalysisPieChartResource();

    private final PieChartCauseCodeDataValidator pieChartCauseCodeDataValidator = new PieChartCauseCodeDataValidator();

    private static final String TEST_VALUE_CAUSE_CODE_IDS = "" + causeCode_1_SGEH + "," + causeCode_1_LTE;

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.TestsWithTemporaryTablesBaseTestCase#onSetUp()
     */
    @Override
    public void onSetUp() throws Exception {
        super.onSetUp();
        attachDependencies(causeCodeAnalysisPieChartResource);
        final PieChartCauseCodePopulator pieChartCauseCodePopulator = new PieChartCauseCodePopulator(this.connection);
        pieChartCauseCodePopulator.createTemporaryTables();
        pieChartCauseCodePopulator.populateTemporaryTables();
    }

    @Test
    public void testGetCauseCodeAnalysisByAPN() throws Exception {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(TYPE_PARAM, TYPE_APN);
        map.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        map.putSingle(NODE_PARAM, TEST_APN_1);
        map.putSingle(CAUSE_CODE_IDS, TEST_VALUE_CAUSE_CODE_IDS);
        map.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        DummyUriInfoImpl.setUriInfo(map, causeCodeAnalysisPieChartResource);

        final List<PieChartCauseCodeAnalysisResult> result = getResults(CAUSE_CODE_PIE_CHART + PATH_SEPARATOR
                + CAUSE_CODE_ANALYSIS);
        pieChartCauseCodeDataValidator.makeAssertionsCCAnaylsis(result);
    }

    @Test
    public void testGetCauseCodeAnalysisBySGSN() throws Exception {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(TYPE_PARAM, TYPE_SGSN);
        map.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        map.putSingle(NODE_PARAM, TEST_SGSN_1);
        map.putSingle(CAUSE_CODE_IDS, TEST_VALUE_CAUSE_CODE_IDS);
        map.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        DummyUriInfoImpl.setUriInfo(map, causeCodeAnalysisPieChartResource);

        final List<PieChartCauseCodeAnalysisResult> result = getResults(CAUSE_CODE_PIE_CHART + PATH_SEPARATOR
                + CAUSE_CODE_ANALYSIS);
        pieChartCauseCodeDataValidator.makeAssertionsCCAnaylsis(result);
    }

    @Test
    public void testGetCauseCodeAnalysisByBSC() throws Exception {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(TYPE_PARAM, TYPE_BSC);
        map.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        map.putSingle(NODE_PARAM, TEST_BSC_1);
        map.putSingle(CAUSE_CODE_IDS, TEST_VALUE_CAUSE_CODE_IDS);
        map.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        DummyUriInfoImpl.setUriInfo(map, causeCodeAnalysisPieChartResource);

        final List<PieChartCauseCodeAnalysisResult> result = getResults(CAUSE_CODE_PIE_CHART + PATH_SEPARATOR
                + CAUSE_CODE_ANALYSIS);
        pieChartCauseCodeDataValidator.makeAssertionsCCAnaylsis(result);
    }

    @Test
    public void testGetCauseCodeAnalysisByCell() throws Exception {
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle(TYPE_PARAM, TYPE_CELL);
        map.putSingle(TZ_OFFSET, TIME_ZONE_OFFSET_OF_PLUS_ONE_HOUR);
        map.putSingle(NODE_PARAM, TEST_CELL_1);
        map.putSingle(CAUSE_CODE_IDS, TEST_VALUE_CAUSE_CODE_IDS);
        map.putSingle(TIME_QUERY_PARAM, THIRTY_MINUTES);
        DummyUriInfoImpl.setUriInfo(map, causeCodeAnalysisPieChartResource);

        final List<PieChartCauseCodeAnalysisResult> result = getResults(CAUSE_CODE_PIE_CHART + PATH_SEPARATOR
                + CAUSE_CODE_ANALYSIS);
        pieChartCauseCodeDataValidator.makeAssertionsCCAnaylsis(result);
    }

    private List<PieChartCauseCodeAnalysisResult> getResults(final String path) throws Exception {
        final String json = causeCodeAnalysisPieChartResource.getResults(path);
        System.out.println(json);
        return getTranslator().translateResult(json, PieChartCauseCodeAnalysisResult.class);
    }
}