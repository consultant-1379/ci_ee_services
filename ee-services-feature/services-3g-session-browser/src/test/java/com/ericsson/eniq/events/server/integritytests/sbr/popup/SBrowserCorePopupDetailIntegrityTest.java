package com.ericsson.eniq.events.server.integritytests.sbr.popup;

import org.junit.Ignore;

import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.test.queryresults.sbr.SBrowserCorePopupDetailResult;

/**
 * @author edarbla
 * @since 2012
 *
 */
@Ignore
public class SBrowserCorePopupDetailIntegrityTest extends BaseDataIntegrityTest<SBrowserCorePopupDetailResult> {
    /*
        private static final String TEMP_DIM_E_SGEH_CAUSECODE = "#DIM_E_SGEH_CAUSECODE";

        private static final String TEMP_DIM_E_SGEH_RAT = "#DIM_E_SGEH_RAT";

        private static final String TEMP_DIM_E_SGEH_TAC = "#DIM_E_SGEH_TAC";

        private static final String TEMP_DIM_E_SGEH_MCCMNC = "#DIM_E_SGEH_MCCMNC";

        private static final String TEMP_DIM_E_SGEH_EVENTTYPE = "#DIM_E_SGEH_EVENTTYPE";

        private static final String TEMP_DIM_E_SGEH_HIER321 = "#DIM_E_SGEH_HIER321";

        private static final String TEMP_DIM_E_SGEH_HIER321_CELL = "#DIM_E_SGEH_HIER321_CELL";

        private static final String TEMP_DIM_E_SGEH_SUBCAUSECODE = "#DIM_E_SGEH_SUBCAUSECODE";

        private static final String TEMP_EVENT_E_SGEH_SUC_RAW = "#EVENT_E_SGEH_SUC_RAW";

        private static final String TEMP_EVENT_E_SGEH_ERR_RAW = "#EVENT_E_SGEH_ERR_RAW";

        private SBrowserSgehPopupDetailService sBrowserSgehPopupDetailService;

        @Before
        public void onSetUp() throws Exception {
            sBrowserSgehPopupDetailService = new SBrowserSgehPopupDetailService();
            attachDependencies(sBrowserSgehPopupDetailService);
            createTables(TEMP_DIM_E_SGEH_CAUSECODE,CAUSE_CODE_COLUMN,CAUSE_CODE_DESC_COLUMN);
            createTables(TEMP_DIM_E_SGEH_RAT,RAT,RAT_DESC);
            createTables(TEMP_DIM_E_SGEH_TAC,TAC,VENDOR_NAME);
            createTables(TEMP_DIM_E_SGEH_MCCMNC,MCC,MNC,VENDOR);
            createTables(TEMP_DIM_E_SGEH_EVENTTYPE,EVENT_ID);
            createTables(TEMP_DIM_E_SGEH_HIER321,ACCESS_AREA_ID,LAC,HIERARCHY_3,RAC);
            createTables(TEMP_DIM_E_SGEH_HIER321_CELL,HIER321_ID,CELL_ID);
            createTables(TEMP_DIM_E_SGEH_SUBCAUSECODE,SUBCAUSE_CODE_COLUMN,SUB_CAUSE_CODE_DESC_COLUMN);

            createTables(TEMP_EVENT_E_SGEH_SUC_RAW,IMSI,EVENT_TIME,EVENT_ID,DATETIME_ID,CAUSE_CODE_COLUMN,CAUSE_CODE_DESC_COLUMN,
                         RAT,RAT_DESC,TAC,VENDOR_NAME,MCC,MNC,VENDOR,ACCESS_AREA_ID,LAC,HIERARCHY_3,RAC,HIER321_ID,CELL_ID,
                         SUBCAUSE_CODE_COLUMN,SUB_CAUSE_CODE_DESC_COLUMN,IMSI_MNC,IMSI_MCC,ROAMING,EVENT_SOURCE_NAME,MSISDN,DURATION,SERVING_AREA,
                    CONTROLLER_COLUMN,PLMN,CELL_COLUMN,TERMINAL,NETWORK_COLUMN,EVENT_TYPE,EVENT_TIME,SGSN,ROUTING_AREA,EVENT_RESULT);


            createTables(TEMP_EVENT_E_SGEH_ERR_RAW,IMSI,EVENT_TIME,EVENT_ID,DATETIME_ID,CAUSE_CODE_COLUMN,CAUSE_CODE_DESC_COLUMN,
                         RAT,RAT_DESC,TAC,VENDOR_NAME,MCC,MNC,VENDOR,ACCESS_AREA_ID,LAC,HIERARCHY_3,RAC,HIER321_ID,CELL_ID,
                         SUBCAUSE_CODE_COLUMN,SUB_CAUSE_CODE_DESC_COLUMN,IMSI_MNC,IMSI_MCC,ROAMING,EVENT_SOURCE_NAME,MSISDN,DURATION,SERVING_AREA,
                    CONTROLLER_COLUMN,PLMN,CELL_COLUMN,TERMINAL,NETWORK_COLUMN,EVENT_TYPE,EVENT_TIME,SGSN,ROUTING_AREA,EVENT_RESULT);

            insertData(TEMP_DIM_E_SGEH_CAUSECODE);
            insertData(TEMP_DIM_E_SGEH_RAT);
            insertData(TEMP_DIM_E_SGEH_TAC);
            insertData(TEMP_DIM_E_SGEH_MCCMNC);
            insertData(TEMP_DIM_E_SGEH_EVENTTYPE);
            insertData(TEMP_DIM_E_SGEH_HIER321);
            insertData(TEMP_DIM_E_SGEH_HIER321_CELL);
            insertData(TEMP_DIM_E_SGEH_SUBCAUSECODE);

            insertData(TEMP_EVENT_E_SGEH_SUC_RAW);
            insertData(TEMP_EVENT_E_SGEH_ERR_RAW);
        }

        @Test
        public void testRunQuery() throws Exception {
            final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
            requestParameters.putSingle(IMSI_PARAM, "1234567");
            requestParameters.putSingle(EVENT_ID_PARAM, "1");
            requestParameters.putSingle(TZ_OFFSET, "+0000");
            requestParameters.putSingle(TIME_QUERY_PARAM, FIVE_MINUTES);
            requestParameters.putSingle(EVENT_TIME_FROM_PARAM, "1340198820000");
            requestParameters.putSingle(EVENT_TIME_TO_PARAM, "1340199120000");
            requestParameters.putSingle(TIME_FROM_QUERY_PARAM, "1327");
            requestParameters.putSingle(TIME_TO_QUERY_PARAM, "1332");
            requestParameters.putSingle(DATE_FROM_QUERY_PARAM, "20092011");
            requestParameters.putSingle(DATE_TO_QUERY_PARAM, "20092011");
               
            final String json = runQuery(sBrowserSgehPopupDetailService, requestParameters);
            System.out.println(json);
            verifyResult(json);
        }

        private void verifyResult(final String json) throws Exception {
            final List<SBrowserCorePopupDetailResult> result = getTranslator().translateResult(json,
                    SBrowserCorePopupDetailResult.class);
            assertThat(result.size(), is(2));

            final SBrowserCorePopupDetailResult resultForCorePopupDetail = result.get(0);

            assertThat(resultForCorePopupDetail.getCauseCode(), is(1));
            assertThat(resultForCorePopupDetail.getCauseCodeDesc(), is("Test Error"));
        }

        private void insertData(final String table) throws Exception {
           final Map<String, Object> dataForTempTable = new HashMap<String, Object>();
            
            if(table.equals(TEMP_DIM_E_SGEH_CAUSECODE)){
                dataForTempTable.put(CAUSE_CODE_COLUMN, 1);
                dataForTempTable.put(CAUSE_CODE_DESC_COLUMN, "Test Error");
                insertRow(table, dataForTempTable);

            }else if(table.equals(TEMP_DIM_E_SGEH_RAT)){
                dataForTempTable.put(RAT, 1);
                dataForTempTable.put(RAT_DESC, "GSM");
                insertRow(table, dataForTempTable);

            }else if(table.equals(TEMP_DIM_E_SGEH_TAC)){
                dataForTempTable.put(TAC, 100200);
                dataForTempTable.put(VENDOR_NAME, "Nokia");
                insertRow(table, dataForTempTable);

            }else if(table.equals(TEMP_DIM_E_SGEH_MCCMNC)) {
                dataForTempTable.put(MCC, "440");
                dataForTempTable.put(MNC, "84");
                dataForTempTable.put(VENDOR, "ERICSSON");
                insertRow(table, dataForTempTable);

            }else if(table.equals(TEMP_DIM_E_SGEH_EVENTTYPE)){
                dataForTempTable.put(EVENT_ID, 1);
                insertRow(table, dataForTempTable);

            }else if(table.equals(TEMP_DIM_E_SGEH_HIER321)){
                dataForTempTable.put(ACCESS_AREA_ID, 13019);
                dataForTempTable.put(LAC, 8100);
                dataForTempTable.put(HIERARCHY_3, "rnc10");
                dataForTempTable.put(RAC, 1);
                insertRow(table, dataForTempTable);

            }else if(table.equals(TEMP_DIM_E_SGEH_HIER321_CELL)){
                dataForTempTable.put(HIER321_ID, "22184");
                dataForTempTable.put(CELL_ID, "81301");
                insertRow(table, dataForTempTable);

            }else if(table.equals(TEMP_DIM_E_SGEH_SUBCAUSECODE)){
                dataForTempTable.put(SUBCAUSE_CODE_COLUMN, 2);
                dataForTempTable.put(SUB_CAUSE_CODE_DESC_COLUMN, "Test");

            }else if(table.equals(TEMP_EVENT_E_SGEH_SUC_RAW) || table.equals(TEMP_EVENT_E_SGEH_ERR_RAW)){
                dataForTempTable.put(IMSI, 1234567);
                dataForTempTable.put(EVENT_ID, 1);
                dataForTempTable.put(EVENT_TIME, DateTimeUtilities.getDateTimeMinus2Minutes());
                dataForTempTable.put(DATETIME_ID, DateTimeUtilities.getDateTimeMinus2Minutes());
                dataForTempTable.put(CAUSE_CODE_COLUMN, 1);
                dataForTempTable.put(CAUSE_CODE_DESC_COLUMN, "Test Error");
                dataForTempTable.put(RAT, 1);
                dataForTempTable.put(RAT_DESC, "GSM");
                dataForTempTable.put(TAC, 100200);
                dataForTempTable.put(VENDOR_NAME, "Nokia");
                dataForTempTable.put(MCC, "440");
                dataForTempTable.put(MNC, "84");
                dataForTempTable.put(IMSI_MCC, "123");
                dataForTempTable.put(IMSI_MNC, "456");
                dataForTempTable.put(ROAMING, 1);
                dataForTempTable.put(VENDOR, "ERICSSON");
                dataForTempTable.put(EVENT_SOURCE_NAME, "A");
                dataForTempTable.put(ACCESS_AREA_ID, 13019);
                dataForTempTable.put(LAC, 8100);
                dataForTempTable.put(HIERARCHY_3, "rnc10");
                dataForTempTable.put(RAC, 1);
                dataForTempTable.put(HIER321_ID, "22184");
                dataForTempTable.put(CELL_ID, "81301");
                dataForTempTable.put(SUBCAUSE_CODE_COLUMN, 2);
                dataForTempTable.put(SUB_CAUSE_CODE_DESC_COLUMN, "Test");

                dataForTempTable.put(MSISDN, 12345);
                dataForTempTable.put(DURATION, 5);
                dataForTempTable.put(SERVING_AREA, 1);
                dataForTempTable.put(CONTROLLER_COLUMN, "smartone:rnc01");
                dataForTempTable.put(PLMN, "34.88");
                dataForTempTable.put(CELL_COLUMN, "4");
                dataForTempTable.put(TERMINAL, "5");
                dataForTempTable.put(NETWORK_COLUMN, "5");
                dataForTempTable.put(EVENT_TYPE, "ATTACH");
                dataForTempTable.put(SGSN, "6");
                dataForTempTable.put(ROUTING_AREA, 7);
                dataForTempTable.put(EVENT_RESULT, 1);

                insertRow(table, dataForTempTable);

            }
        }

        private void createTables(final String temporaryTable, String... columns) throws Exception {

            final Collection<String> columnsForTempTable = new ArrayList<String>();

                for(String col : columns){
                    columnsForTempTable.add(col);
                }

            createTemporaryTable(temporaryTable, columnsForTempTable);

        }
    */
}
