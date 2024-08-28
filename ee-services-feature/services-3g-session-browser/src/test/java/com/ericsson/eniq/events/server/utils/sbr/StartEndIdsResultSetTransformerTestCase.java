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
import com.ericsson.eniq.events.ui.shared.model.sessionbrowser.details.IDataBearerSessionEventData;

public class StartEndIdsResultSetTransformerTestCase extends BaseJMockUnitTest {
	@Test
	public void testIDataBearerSessionEventDataTransformationWithUniqueEventTimes() throws SQLException {
		final ResultSet mock = mockery.mock(ResultSet.class);
		final StartEndIdsResultSetTransformer transformer = new StartEndIdsResultSetTransformer("0", "0", "0",
				IDataBearerSessionEventData.class, null); 

		final int iterations = 3;

		mockery.checking(new ResultSetExpectations(mock, new HashMap<String, List<?>>() {
			{
				put("EVENT_TIME", Arrays.asList(100l, 200l, 300l));
				put("EVENT_ID", Arrays.asList(0, 0, 0));
				put("TAC", Arrays.asList(3, 3, 3));
				put("START_CELL_ID", Arrays.asList(1, 2, 5));
				put("END_CELL_ID", Arrays.asList(3, 4, 5));
				put("START_RNC_ID", Arrays.asList(1, 2, 5));
				put("END_RNC_ID", Arrays.asList(3, 4, 5));
				put("RRC_CONN_START", Arrays.asList(100l, 200l, 300l));
				put("RRC_CONN_END", Arrays.asList(190l, 290l, 390l));
			}
		}, iterations, false));

		final List<Map<String, Object>> mapResultSetToObjects = transformer.mapResultSetToObjects(mock,
				IDataBearerSessionEventData.class);

		Assert.assertThat(mapResultSetToObjects, Matchers.notNullValue());
		Assert.assertThat(mapResultSetToObjects.size(), Matchers.is(iterations));
		final Map<String, Object> map = mapResultSetToObjects.get(0);
		Assert.assertThat((String) map.get("eventTime"), Matchers.is("100"));
		Assert.assertThat((EventType) map.get("eventType"), Matchers.is(EventType.ATTACH));
		Assert.assertThat((Integer) map.get("tac"), Matchers.is(3));
		Assert.assertThat((Integer) map.get("startCellId"), Matchers.is(1));
		Assert.assertThat((Integer) map.get("endCellId"), Matchers.is(3));
		Assert.assertThat((Integer) map.get("startRncId"), Matchers.is(1));
		Assert.assertThat((Integer) map.get("endRncId"), Matchers.is(3));
		Assert.assertThat((EventClassType) map.get("eventClassType"), Matchers.is(EventClassType.RAB));
	}

	@Test
	public void testIDataBearerSessionEventDataTransformationWithContinuationAtStart() throws SQLException {
		final ResultSet mock = mockery.mock(ResultSet.class);
		final StartEndIdsResultSetTransformer transformer = new StartEndIdsResultSetTransformer("0", "0", "0",
				IDataBearerSessionEventData.class, null);

		final int iterations = 3;

		mockery.checking(new ResultSetExpectations(mock, new HashMap<String, List<?>>() {
			{
				put("EVENT_TIME", Arrays.asList(100l, 100l, 300l));
				put("START_CELL_ID", Arrays.asList(1, 2, 5));
				put("END_CELL_ID", Arrays.asList(3, 4, 5));
				put("START_RNC_ID", Arrays.asList(1, 2, 5));
				put("END_RNC_ID", Arrays.asList(3, 4, 5));
				put("RRC_CONN_START", Arrays.asList(110l, 120l, 300l));
				put("RRC_CONN_END", Arrays.asList(170l, 190l, 390l));
			}
		}, iterations, false));

		final List<Map<String, Object>> mapResultSetToObjects = transformer.mapResultSetToObjects(mock,
				IDataBearerSessionEventData.class);
		Assert.assertThat(mapResultSetToObjects, Matchers.notNullValue());
		Assert.assertThat(mapResultSetToObjects.size(), Matchers.is(2));
		final Map<String, Object> map = mapResultSetToObjects.get(0);
		Assert.assertThat((String) map.get("eventTime"), Matchers.is("100"));
		Assert.assertThat((Integer) map.get("startCellId"), Matchers.is(1));
		Assert.assertThat((Integer) map.get("endCellId"), Matchers.is(4));
		Assert.assertThat((Integer) map.get("startRncId"), Matchers.is(1));
		Assert.assertThat((Integer) map.get("endRncId"), Matchers.is(4));
	}

	@Test
	public void testIDataBearerSessionEventDataTransformationWithContinuationAtEnd() throws SQLException {
		final ResultSet mock = mockery.mock(ResultSet.class);
		final StartEndIdsResultSetTransformer transformer = new StartEndIdsResultSetTransformer("0", "0", "0",
				IDataBearerSessionEventData.class, null);

		final int iterations = 3;

		mockery.checking(new ResultSetExpectations(mock, new HashMap<String, List<?>>() {
			{
				put("EVENT_TIME", Arrays.asList(100l, 300l, 300l));
				put("START_CELL_ID", Arrays.asList(1, 2, 5));
				put("END_CELL_ID", Arrays.asList(3, 4, 6));
				put("START_RNC_ID", Arrays.asList(1, 2, 5));
				put("END_RNC_ID", Arrays.asList(3, 4, 5));
				put("RRC_CONN_START", Arrays.asList(110l, 320l, 300l));
				put("RRC_CONN_END", Arrays.asList(170l, 390l, 395l));
			}
		}, iterations, false));

		final List<Map<String, Object>> mapResultSetToObjects = transformer.mapResultSetToObjects(mock,
				IDataBearerSessionEventData.class);
		Assert.assertThat(mapResultSetToObjects, Matchers.notNullValue());
		Assert.assertThat(mapResultSetToObjects.size(), Matchers.is(2));
		final Map<String, Object> map = mapResultSetToObjects.get(1);
		Assert.assertThat((String) map.get("eventTime"), Matchers.is("300"));
		Assert.assertThat((Integer) map.get("startCellId"), Matchers.is(5));
		Assert.assertThat((Integer) map.get("endCellId"), Matchers.is(6));
		Assert.assertThat((Integer) map.get("startRncId"), Matchers.is(5));
		Assert.assertThat((Integer) map.get("endRncId"), Matchers.is(5));
	}

	@Test
	public void testIDataBearerSessionEventDataTransformationWithContinuationInside() throws SQLException {
		final ResultSet mock = mockery.mock(ResultSet.class);
		final StartEndIdsResultSetTransformer transformer = new StartEndIdsResultSetTransformer("0", "0", "0",
				IDataBearerSessionEventData.class, null);

		final int iterations = 5;

		mockery.checking(new ResultSetExpectations(mock, new HashMap<String, List<?>>() {
			{
				put("EVENT_TIME", Arrays.asList(100l, 300l, 300l, 300l, 400l));
				put("START_CELL_ID", Arrays.asList(1, 2, 5, 6, 7));
				put("END_CELL_ID", Arrays.asList(3, 4, 6, 8, 9));
				put("START_RNC_ID", Arrays.asList(1, 2, 5, 11, 22));
				put("END_RNC_ID", Arrays.asList(3, 4, 5, 33, 44));
				put("RRC_CONN_START", Arrays.asList(110l, 320l, 300l, 330l, 410l));
				put("RRC_CONN_END", Arrays.asList(170l, 397l, 395l, 380l, 420l));
			}
		}, iterations, false));

		final List<Map<String, Object>> mapResultSetToObjects = transformer.mapResultSetToObjects(mock,
				IDataBearerSessionEventData.class);
		Assert.assertThat(mapResultSetToObjects, Matchers.notNullValue());
		Assert.assertThat(mapResultSetToObjects.size(), Matchers.is(3));
		final Map<String, Object> map = mapResultSetToObjects.get(1);
		Assert.assertThat((String) map.get("eventTime"), Matchers.is("300"));
		Assert.assertThat((Integer) map.get("startCellId"), Matchers.is(5));
		Assert.assertThat((Integer) map.get("endCellId"), Matchers.is(4));
		Assert.assertThat((Integer) map.get("startRncId"), Matchers.is(5));
		Assert.assertThat((Integer) map.get("endRncId"), Matchers.is(4));
	}
}
