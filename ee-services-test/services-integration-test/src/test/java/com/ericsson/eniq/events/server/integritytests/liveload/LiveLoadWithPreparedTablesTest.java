/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.integritytests.liveload;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.ericsson.eniq.events.server.integritytests.stubs.ReplaceTablesWithTempTablesTemplateUtils;
import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.LiveLoadResource;
import com.ericsson.eniq.events.server.resources.TestsWithTemporaryTablesBaseTestCase;
import com.ericsson.eniq.events.server.test.sql.SQLExecutor;
import com.ericsson.eniq.events.server.test.stubs.DummyUriInfoImpl;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * @author eemecoy
 * @author epesmit
 *
 */
public class LiveLoadWithPreparedTablesTest extends TestsWithTemporaryTablesBaseTestCase {

    private LiveLoadResource liveLoadResource;

    private final static List<String> tempTables = new ArrayList<String>();

    private final static List<String> wcdmaTempTables = new ArrayList<String>();

    private final static List<String> handsetMakeTempTables = new ArrayList<String>();

    private static final String DIM_Z_SGEH_HIER321 = "#DIM_Z_SGEH_HIER321";

    private static final String DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

    private static final String DIM_E_LTE_HIER321 = "#DIM_E_LTE_HIER321";

    private static final String DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

    private static final String DIM_Z_SGEH_HIER321_CELL = "#DIM_Z_SGEH_HIER321_CELL";

    private static final String GSMBSC2 = "GSMBSC2";

    private static final String BERBS2 = "BERBS2";

    private static final String GLTECELL2 = "GLTECELL2";

    private static final String CELL2 = "CELL2";

    private static final String TAC_1002151 = "1002151";

    private static final String MARKETINGNAME_CF788 = "CF788";

    final static String transId = "transId0";
    static {
        tempTables.add(DIM_Z_SGEH_HIER321);
        tempTables.add(DIM_E_SGEH_HIER321);
        tempTables.add(DIM_E_LTE_HIER321);
        wcdmaTempTables.add(DIM_E_SGEH_HIER321_CELL);
        wcdmaTempTables.add(DIM_Z_SGEH_HIER321_CELL);
        handsetMakeTempTables.add(DIM_E_SGEH_TAC);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.resources.TestsWithTemporaryTablesBaseTestCase#onSetUp()
     */
    @Override
    public void onSetUp() throws Exception {
        super.onSetUp();
        liveLoadResource = new LiveLoadResource();
        attachDependencies(liveLoadResource);
    }

    @Test
    public void testGetLiveLoad_Controller() throws Exception {
        createTempTables();
        createWCDMATempTables();
        populateTempTables();
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle("callback", transId);
        map.putSingle(PAGING_START_KEY, "0");
        map.putSingle(PAGING_LIMIT_KEY, "20");
        map.putSingle(TZ_OFFSET, "+0000");
        DummyUriInfoImpl.setUriInfo(map, liveLoadResource);
        final String json = liveLoadResource.getLiveLoadNodes(TYPE_BSC);
        System.out.println(json);
        final List<String> liveLoadResult = translateResult(json, "BSC");
        assertThat(liveLoadResult.size(), is(4));
        assertThat(liveLoadResult.contains(ERBS1 + "," + ERICSSON + "," + LTE), is(true));
        assertThat(liveLoadResult.contains(BERBS2 + "," + ERICSSON + COMMA + LTE), is(true));
        assertThat(liveLoadResult.contains(BSC1 + "," + ERICSSON + COMMA + GSM), is(true));
        assertThat(liveLoadResult.contains(GSMBSC2 + "," + ERICSSON + COMMA + GSM), is(true));
    }

    @Test
    public void testGetLiveLoad_Controller_NodePrefixProvided() throws Exception {
        createTempTables();
        createWCDMATempTables();
        populateTempTables();
        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle("callback", transId);
        map.putSingle(PAGING_START_KEY, "0");
        map.putSingle(PAGING_LIMIT_KEY, "20");
        map.putSingle(LIVELOAD_QUERY_PARAM, "B");
        map.putSingle(TZ_OFFSET, "+0000");
        DummyUriInfoImpl.setUriInfo(map, liveLoadResource);
        final String json = liveLoadResource.getLiveLoadNodes(TYPE_BSC);
        System.out.println(json);
        final List<String> liveLoadResult = translateResult(json, "BSC");
        assertThat(liveLoadResult.size(), is(4));
        assertThat(liveLoadResult.contains(BERBS2 + "," + ERICSSON + COMMA + LTE), is(true));
        assertThat(liveLoadResult.contains(BSC1 + "," + ERICSSON + COMMA + GSM), is(true));
    }

    @Test
    public void testGetLiveLoad_Cell() throws Exception {
        createTempTables();
        createWCDMATempTables();
        populateTempTables();

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle("callback", transId);
        map.putSingle(PAGING_START_KEY, "0");
        map.putSingle(PAGING_LIMIT_KEY, "20");
        map.putSingle(TZ_OFFSET, "+0000");
        DummyUriInfoImpl.setUriInfo(map, liveLoadResource);
        final String json = liveLoadResource.getLiveLoadNodes(TYPE_CELL);
        System.out.println(json);
        final List<String> liveLoadResult = translateResult(json, "CELL");
        assertThat(liveLoadResult.size(), is(4));
        assertThat(liveLoadResult.contains(LTECELL1 + "," + "" + "," + ERBS1 + "," + ERICSSON + COMMA + LTE), is(true));
        assertThat(liveLoadResult.contains(GLTECELL2 + "," + "" + "," + BERBS2 + "," + ERICSSON + COMMA + LTE),
                is(true));
        assertThat(liveLoadResult.contains(GSMCELL1 + "," + "" + "," + BSC1 + "," + ERICSSON + COMMA + GSM), is(true));
        assertThat(liveLoadResult.contains(CELL2 + "," + "" + "," + GSMBSC2 + "," + ERICSSON + COMMA + GSM), is(true));

    }

    @Test
    public void testGetLiveLoad_Cell_CellPrefixProvided() throws Exception {
        createTempTables();
        createWCDMATempTables();
        populateTempTables();

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();
        map.putSingle("callback", transId);
        map.putSingle(PAGING_START_KEY, "0");
        map.putSingle(PAGING_LIMIT_KEY, "20");
        map.putSingle(TZ_OFFSET, "+0000");
        map.putSingle(LIVELOAD_QUERY_PARAM, "G");
        DummyUriInfoImpl.setUriInfo(map, liveLoadResource);
        final String json = liveLoadResource.getLiveLoadNodes(TYPE_CELL);
        System.out.println(json);
        final List<String> liveLoadResult = translateResult(json, "CELL");
        assertThat(liveLoadResult.size(), is(3));

        assertThat(liveLoadResult.contains(GLTECELL2 + "," + "" + "," + BERBS2 + "," + ERICSSON + COMMA + LTE),
                is(true));
        assertThat(liveLoadResult.contains(GSMCELL1 + "," + "" + "," + BSC1 + "," + ERICSSON + COMMA + GSM), is(true));

    }

    @Test
    public void testGetLiveLoad_HandSetMakes_QueryWithComma() throws Exception {
        ReplaceTablesWithTempTablesTemplateUtils.addTableNameToReplace(DIM_E_SGEH_TAC, TEMP_DIM_E_SGEH_TAC);

        createHandsetMakeTempTables();
        populateHandsetMakeTempTables();

        final MultivaluedMap<String, String> map = new MultivaluedMapImpl();

        map.putSingle(LIVELOAD_CALLBACK_PARAM, transId);
        map.putSingle(JSON_ID, ERICSSON);
        map.putSingle(PAGING_START_KEY, "0");
        map.putSingle(PAGING_LIMIT_KEY, "10");
        map.putSingle(TZ_OFFSET, "+0000");
        map.putSingle(LIVELOAD_QUERY_PARAM, "CF788,");

        DummyUriInfoImpl.setUriInfo(map, liveLoadResource);
        final String json = liveLoadResource.getLiveLoadHandsetMakes();
        System.out.println(json);

        final List<String> liveLoadResult = translateResult(json, ERICSSON);

        assertThat(liveLoadResult.size(), is(1));
        assertThat(liveLoadResult.contains(MARKETINGNAME_CF788 + COMMA + TAC_1002151), is(true));
    }

    private List<String> translateResult(final String json, final String keyInJSON) throws JSONException {
        final String realJson = stripOffNonJsonWrapper(json);
        final JSONObject resultAsJSONObject = new JSONObject(realJson);
        final JSONArray nodes = (JSONArray) resultAsJSONObject.get(keyInJSON);

        final List<String> results = new ArrayList<String>();
        for (int i = 0; i < nodes.length(); i++) {
            final JSONObject liveLoadResult = (JSONObject) nodes.get(i);
            results.add(liveLoadResult.getString("id"));
        }
        return results;
    }

    private String stripOffNonJsonWrapper(final String json) {
        return json.substring(json.indexOf(transId) + transId.length() + 1, json.length() - 1);
    }

    private void populateTempTables() throws SQLException {
        SQLExecutor sqlExecutor = null;
        try {
            sqlExecutor = new SQLExecutor(connection);

            sqlExecutor.executeUpdate("insert into " + DIM_E_SGEH_HIER321 + " values('','" + BSC1 + "','" + "" + "','"
                    + GSMCELL1 + "',0,'" + ERICSSON + "')");
            sqlExecutor.executeUpdate("insert into " + DIM_E_SGEH_HIER321 + " values('','" + GSMBSC2 + "','" + ""
                    + "','" + CELL2 + "',0,'" + ERICSSON + "')");
            sqlExecutor.executeUpdate("insert into " + DIM_E_LTE_HIER321 + " values('','" + ERBS1 + "','" + "" + "','"
                    + LTECELL1 + "',2,'" + ERICSSON + "')");
            sqlExecutor.executeUpdate("insert into " + DIM_E_LTE_HIER321 + " values('','" + BERBS2 + "','" + "" + "','"
                    + GLTECELL2 + "',2,'" + ERICSSON + "')");
        } finally {
            if (sqlExecutor != null) {
                sqlExecutor.close();
            }
        }

    }

    private void populateHandsetMakeTempTables() throws SQLException {
        final Map<String, Object> values = new HashMap<String, Object>();
        values.put(MARKETING_NAME, MARKETINGNAME_CF788);
        values.put(MANUFACTURER, ERICSSON);
        values.put(TAC, TAC_1002151);
        insertRow(TEMP_DIM_E_SGEH_TAC, values);
    }

    private void createTempTables() throws SQLException {
        for (final String tempTable : tempTables) {
            SQLExecutor sqlExecutor = null;
            try {
                sqlExecutor = new SQLExecutor(connection);
                sqlExecutor
                        .executeUpdate("create local temporary table "
                                + tempTable
                                + "(ERBS_ID varchar(12), HIERARCHY_3 varchar(128), HIERARCHY_2 varchar(128),HIERARCHY_1 varchar(128), RAT tinyint, VENDOR varchar(20))");

            } finally {
                if (sqlExecutor != null) {
                    sqlExecutor.close();
                }
            }
        }

    }

    private void createWCDMATempTables() throws SQLException {
        for (final String tempTable : wcdmaTempTables) {
            SQLExecutor sqlExecutor = null;
            try {
                sqlExecutor = new SQLExecutor(connection);
                sqlExecutor
                        .executeUpdate("create local temporary table "
                                + tempTable
                                + "(ERBS_ID varchar(12), HIERARCHY_3 varchar(128), HIERARCHY_2 varchar(128),CELL_ID varchar(128), RAT tinyint, VENDOR varchar(20))");

            } finally {
                if (sqlExecutor != null) {
                    sqlExecutor.close();
                }
            }
        }

    }

    private void createHandsetMakeTempTables() throws Exception {
        final Collection<String> columns = new ArrayList<String>();
        columns.add(MARKETING_NAME);
        columns.add(MANUFACTURER);
        columns.add(TAC);
        createTemporaryTable(TEMP_DIM_E_SGEH_TAC, columns);
    }

}
