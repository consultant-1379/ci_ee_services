package com.ericsson.eniq.events.server.utils.sbr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;

import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.ui.shared.model.kpianalysis.ISGEHNonNwrkMapKpiResult;

@Ignore
public class KpiMapResultSetTransformerTestCase extends BaseJMockUnitTest {

    @SuppressWarnings("unchecked")
    //Pass on local host , fail on CI.
    @Ignore
    //@Test
    public void testICoreEventDataTransformation() throws SQLException {
        final ResultSet mock = mockery.mock(ResultSet.class);
        final MapKpiResultSetTransformer transformer = new MapKpiResultSetTransformer("2012-06-22 13:46",
                "2012-06-22 13:46", "+0000", ISGEHNonNwrkMapKpiResult.class, null, false);

        final int iterations = 3;
        mockery.checking(new ResultSetExpectations(mock, new HashMap<String, List<?>>() {
            {
                put("CELL_ID", Arrays.asList("100", "200", "300"));
                put("RAT", Arrays.asList(0, 0, 0));
                put("VENDOR", Arrays.asList(1, 2, 5));
                put("HIERARCHY_3", Arrays.asList(3, 4, 6));
                put("HIERARCHY_1", Arrays.asList(100, 200, 300));
                put("Attach Success Rate", Arrays.asList(22, 33, 44));
                put("PDP Context Activation Success Rate", Arrays.asList(22, 33, 44));
                put("RAU Success Rate", Arrays.asList(22, 33, 44));
                put("ISRAU Success Rate", Arrays.asList(22, 33, 44));
                put("PDP Context Cutoff Ratio", Arrays.asList(22, 33, 44));
                put("Detach Success Rate", Arrays.asList(21, 33, 44));
                put("Service Request Failure Ratio", Arrays.asList(22, 33, 44));
                put("Paging Failure Ratio", Arrays.asList(0, 1, 0));
            }
        }, iterations, true));

        //		System.out.println(transformer.transform(mock));
        final Map<String, Object> map = transformer.mapResultSetToObjects(mock, ISGEHNonNwrkMapKpiResult.class);
        Assert.assertThat(map, Matchers.notNullValue());
        Assert.assertThat(map.size(), Matchers.is(iterations));
        final Map<String, Object> row = (Map<String, Object>) map.get("100");
        Assert.assertThat(row, Matchers.notNullValue());
        Assert.assertThat((String) row.get("cellId"), Matchers.is("100"));
        Assert.assertThat((Integer) row.get("rncName"), Matchers.is(3));
        Assert.assertThat((Integer) row.get("vendor"), Matchers.is(1));
        Assert.assertThat((Integer) row.get("rat"), Matchers.is(0));
        final Map<String, Integer> kpis = (Map<String, Integer>) row.get("kpis");
        Assert.assertThat(kpis, Matchers.notNullValue());
        Assert.assertThat(kpis.get("Attach Success Rate"), Matchers.is(22));
        Assert.assertThat(kpis.get("PDP Context Activation Success Rate"), Matchers.is(22));
        Assert.assertThat(kpis.get("RAU Success Rate"), Matchers.is(22));
        Assert.assertThat(kpis.get("ISRAU Success Rate"), Matchers.is(22));
        Assert.assertThat(kpis.get("PDP Context Cutoff Ratio"), Matchers.is(22));
        Assert.assertThat(kpis.get("Detach Success Rate"), Matchers.is(21));
        Assert.assertThat(kpis.get("PDP Context Cutoff Ratio"), Matchers.is(22));
        Assert.assertThat(kpis.get("Service Request Failure Ratio"), Matchers.is(22));
        Assert.assertThat(kpis.get("Paging Failure Ratio"), Matchers.is(0));
    }
}
