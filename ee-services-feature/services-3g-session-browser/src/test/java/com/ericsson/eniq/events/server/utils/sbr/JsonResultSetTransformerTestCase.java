package com.ericsson.eniq.events.server.utils.sbr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.ui.shared.enums.EventClassType;
import com.ericsson.eniq.events.ui.shared.enums.EventType;
import com.ericsson.eniq.events.ui.shared.model.sessionbrowser.details.ICoreEventData;

public class JsonResultSetTransformerTestCase extends BaseJMockUnitTest {

    @Test
    public void testICoreEventDataTransformation() throws SQLException {
        final ResultSet mock = mockery.mock(ResultSet.class);
        final JsonResultSetTransformer transformer = new JsonResultSetTransformer("0", "0", "0", ICoreEventData.class,
                null);

        final int iterations = 3;
        mockery.checking(new ResultSetExpectations(mock, new HashMap<String, List<?>>() {
            {
                put("EVENT_TIME", Arrays.asList(100l, 200l, 300l));
                put("EVENT_ID", Arrays.asList(0, 0, 0));
                put("CELL_ID", Arrays.asList(1, 2, 5));
                put("CID", Arrays.asList(3, 4, 6));
                put("DURATION", Arrays.asList(100, 200, 300));
                put("SERVING_AREA", Arrays.asList("test", "test", "test"));
                put("TAC", Arrays.asList(22, 33, 44));
                put("ERR_FLAG", Arrays.asList(0, 1, 0));
            }
        }, iterations, true));

        final List<Map<String, Object>> mapResultSetToObjects = transformer.mapResultSetToObjects(mock,
                ICoreEventData.class);
        Assert.assertThat(mapResultSetToObjects, Matchers.notNullValue());
        Assert.assertThat(mapResultSetToObjects.size(), Matchers.is(iterations));
        final Map<String, Object> map = mapResultSetToObjects.get(0);
        Assert.assertThat((String) map.get("eventTime"), Matchers.is("100"));
        Assert.assertThat((EventType) map.get("eventType"), Matchers.is(EventType.ATTACH));
        Assert.assertThat((Integer) map.get("cellId"), Matchers.is(1));
        Assert.assertThat((String) map.get("serviceArea"), Matchers.is("test"));
        Assert.assertThat((Integer) map.get("duration"), Matchers.is(100));
        Assert.assertThat((Integer) map.get("tac"), Matchers.is(22));
        Assert.assertThat((Integer) map.get("errFlag"), Matchers.is(0));
        Assert.assertThat((EventClassType) map.get("eventClassType"), Matchers.is(EventClassType.CORE));
    }
}
