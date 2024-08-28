package com.ericsson.eniq.events.server.integritytests.sbr.summary;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_FROM_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.DATE_TO_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.IMSI_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.INTERVAL_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_FROM_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TIME_TO_QUERY_PARAM;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.TZ_OFFSET;
import static junit.framework.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.resources.BaseDataIntegrityTest;
import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.summary.SBrowserTcpPerfSumService;
import com.ericsson.eniq.events.server.test.schema.ColumnDetails;
import com.ericsson.eniq.events.server.test.schema.Nullable;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SBrowserTcpPerfSumIntegrityTest extends BaseDataIntegrityTest {

    private static String TEMP_EVENT_E_USER_PLANE_TCP_RAW = "#EVENT_E_USER_PLANE_TCP_RAW";

    private static String TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW = "#EVENT_E_USER_PLANE_CLASSIFICATION_RAW";

    private SBrowserTcpPerfSumService sBrowserTcpPerfSumService;

    @Before
    public void setUp() throws Exception {
        sBrowserTcpPerfSumService = new SBrowserTcpPerfSumService();
        attachDependencies(sBrowserTcpPerfSumService);

        createTableSchema();
    }

    @Test
    public void testThroughputValues() throws Exception {
        prepareDataForThroughput();
        final JSONObject row0Data = runQuery();

        // Data volume from classification tables
        assertEquals("8500", row0Data.getString("totalDataVolumeDownlink"));
        assertEquals("0", row0Data.getString("totalDataVolumeUplink"));

        // Average TPut DL&UL from TCP Raw tables 1 min aggregation
        assertEquals("25.0000", row0Data.getString("averageThroughputDownlink"));
        assertEquals("10.0000", row0Data.getString("averageThroughputUplink"));

        // Peak values calculated based on 1 min aggregation
        assertEquals("30.0", row0Data.getString("peakThroughputDownlink"));
        assertEquals("15.0", row0Data.getString("peakThroughputUplink"));

        // Peak Host Uplink&Downlink
        assertEquals("fast.uplink.com", row0Data.getString("peakHostUplink"));
        assertEquals("fast.downlink.com", row0Data.getString("peakHostDownlink"));
    }

    @Test
    public void testLatencyPeakAndAverageValues() throws Exception {
        prepareDataForLatency();
        final JSONObject row0Data = runQuery();

        // Check average values for latency across all records regardless of direction
        assertEquals("10.75", row0Data.getString("averageUeGnRTT"));
        assertEquals("12.75", row0Data.getString("averageGnServerRTT"));
        assertEquals("23.5", row0Data.getString("averageUeServerRTT"));

        // Check peaks aggregated by 1 min average values without 0 and nulls
        assertEquals("20.0", row0Data.getString("peakUeGnRTT"));
        assertEquals("22.0", row0Data.getString("peakGnServerRTT"));
        assertEquals("42.0", row0Data.getString("peakUeServerRTT"));
        assertEquals("slow.host.com", row0Data.getString("worstHostRTT"));
    }

    @Test
    public void testLatencyPeakAndAverageValuesTestTwo() throws Exception {
        prepareDataForLatencyTest2();
        final JSONObject row0Data = runQuery();

        // Check average values for latency across all records regardless of direction
        assertEquals("10.75", row0Data.getString("averageUeGnRTT"));
        assertEquals("-", row0Data.getString("averageGnServerRTT")); //12.75
        assertEquals("10.75", row0Data.getString("averageUeServerRTT")); //23.5

        // Check peaks aggregated by 1 min average values without 0 and nulls
        assertEquals("15.0", row0Data.getString("peakUeGnRTT"));
        assertEquals("-", row0Data.getString("peakGnServerRTT"));
        assertEquals("15.0", row0Data.getString("peakUeServerRTT"));
        assertEquals("fast.uplink.com", row0Data.getString("worstHostRTT"));
    }

    @Test
    public void testPacketLossValues() throws Exception {
        prepareDataForPacketLoss();
        final JSONObject row0Data = runQuery();

        // Check peaks aggregated by 1 min average values without 0 and nulls
        assertEquals("7.000000", row0Data.getString("maxPacketLossUeGnUplink"));
        assertEquals("5.000000", row0Data.getString("maxPacketLossGnServerUplink"));
        assertEquals("10.000000", row0Data.getString("maxPacketLossUeGnDownlink"));
        assertEquals("8.000000", row0Data.getString("maxPacketLossGnServerDownlink"));

        assertEquals("fast2.downlink.com", row0Data.getString("worstPacketLossHostDownlink"));
        assertEquals("slow1.host.com", row0Data.getString("worstPacketLossHostUplink"));
    }

    @Test
    public void testPakcetLossNullULValues() throws Exception {
        prepareDataForPacketLossNoUL();
        final JSONObject row0Data = runQuery();

        // Check peaks aggregated by 1 min average values without 0 and nulls
        // Zero's do count so expect 0 result
        assertEquals("0.000000", row0Data.getString("maxPacketLossUeGnUplink"));
        assertEquals("-", row0Data.getString("maxPacketLossGnServerUplink"));
        assertEquals("10.000000", row0Data.getString("maxPacketLossUeGnDownlink"));
        assertEquals("8.000000", row0Data.getString("maxPacketLossGnServerDownlink"));
        //Even if packet loss is 0 in a peak this is still the 'worst' host
        assertEquals("-", row0Data.getString("worstPacketLossHostUplink"));
        assertEquals("fast2.downlink.com", row0Data.getString("worstPacketLossHostDownlink"));
    }

    @Test
    public void testPacketLossValues_TestNulls() throws Exception {
        prepareNullDataForPacketLoss();
        final JSONObject row0Data = runQuery();

        // Check peaks aggregated by 1 min average values without 0 and nulls
        assertEquals("-", row0Data.getString("maxPacketLossUeGnUplink"));
        assertEquals("-", row0Data.getString("maxPacketLossGnServerUplink"));
        assertEquals("-", row0Data.getString("maxPacketLossUeGnDownlink"));
        assertEquals("-", row0Data.getString("maxPacketLossGnServerDownlink"));
    }

    private void prepareNullDataForPacketLoss() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 5);
                put("PACKET_LOSS_NET", null); // Gn ->Server
                put("PACKET_LOSS_TERM", null); // Ue - > Gn
                put("HOST", "slow.host.com");
                put("EVENT_TIME", "2012-05-16 23:15:00.463");
                put("DATETIME_ID", "2012-05-16 23:15:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 15); // Peak for uplink
                put("PACKET_LOSS_NET", null); // Gn ->Server
                put("PACKET_LOSS_TERM", null); // Ue - > Gn
                put("HOST", "fast.uplink.com");
                put("EVENT_TIME", "2012-05-16 23:15:00.563");
                put("DATETIME_ID", "2012-05-16 23:15:00.563");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 30); // Peak downlink
                put("PACKET_LOSS_NET", null); // Gn ->Server
                put("PACKET_LOSS_TERM", null); // Ue - > Gn
                put("HOST", "fast.downlink.com");
                put("EVENT_TIME", "2012-05-16 23:19:00.463");
                put("DATETIME_ID", "2012-05-16 23:19:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 20);
                put("PACKET_LOSS_NET", null); // Gn ->Server
                put("PACKET_LOSS_TERM", null); // Ue - > Gn
                put("HOST", "another.host.com");
                put("EVENT_TIME", "2012-05-16 23:19:00.763");
                put("DATETIME_ID", "2012-05-16 23:19:00.763");
            }
        });

        populateClassificationData();
    }

    private void prepareDataForPacketLoss() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 5);
                put("PACKET_LOSS_NET", 5); // Gn ->Server
                put("PACKET_LOSS_TERM", 7); // Ue - > Gn
                put("HOST", "slow1.host.com");
                put("EVENT_TIME", "2012-05-16 23:15:00.463");
                put("DATETIME_ID", "2012-05-16 23:15:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 15); // Peak for uplink
                put("PACKET_LOSS_NET", 3); // Gn ->Server
                put("PACKET_LOSS_TERM", 2); // Ue - > Gn
                put("HOST", "fast1.uplink.com");
                put("EVENT_TIME", "2012-05-16 23:15:00.563");
                put("DATETIME_ID", "2012-05-16 23:15:00.563");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 30); // Peak downlink
                put("PACKET_LOSS_NET", 8); // Gn ->Server
                put("PACKET_LOSS_TERM", 10); // Ue - > Gn
                put("HOST", "fast2.downlink.com");
                put("EVENT_TIME", "2012-05-16 23:19:00.463");
                put("DATETIME_ID", "2012-05-16 23:19:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 20);
                put("PACKET_LOSS_NET", 2); // Gn ->Server
                put("PACKET_LOSS_TERM", 5); // Ue - > Gn
                put("HOST", "another.host.com");
                put("EVENT_TIME", "2012-05-16 23:19:00.763");
                put("DATETIME_ID", "2012-05-16 23:19:00.763");
            }
        });

        populateClassificationData();
    }

    private void prepareDataForPacketLossNoUL() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 5);
                put("PACKET_LOSS_NET", null); // Gn ->Server
                put("PACKET_LOSS_TERM", null); // Ue - > Gn
                put("HOST", "slow1.host.com");
                put("EVENT_TIME", "2012-05-16 23:15:00.463");
                put("DATETIME_ID", "2012-05-16 23:15:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 15); // Peak for uplink
                put("PACKET_LOSS_NET", null); // Gn ->Server
                put("PACKET_LOSS_TERM", 0); // Ue - > Gn
                put("HOST", "fast1.uplink.com");
                put("EVENT_TIME", "2012-05-16 23:15:00.563");
                put("DATETIME_ID", "2012-05-16 23:15:00.563");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 30); // Peak downlink
                put("PACKET_LOSS_NET", 8); // Gn ->Server
                put("PACKET_LOSS_TERM", 10); // Ue - > Gn
                put("HOST", "fast2.downlink.com");
                put("EVENT_TIME", "2012-05-16 23:19:00.463");
                put("DATETIME_ID", "2012-05-16 23:19:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 20);
                put("PACKET_LOSS_NET", 2); // Gn ->Server
                put("PACKET_LOSS_TERM", 5); // Ue - > Gn
                put("HOST", "another.host.com");
                put("EVENT_TIME", "2012-05-16 23:19:00.763");
                put("DATETIME_ID", "2012-05-16 23:19:00.763");
            }
        });

        populateClassificationData();
    }

    private void prepareDataForThroughput() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 5);
                put("HOST", "slow.host.com");
                put("EVENT_TIME", "2012-05-16 23:15:00.463");
                put("DATETIME_ID", "2012-05-16 23:15:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 15); // Peak for uplink
                put("HOST", "fast.uplink.com");
                put("EVENT_TIME", "2012-05-16 23:15:00.563");
                put("DATETIME_ID", "2012-05-16 23:15:00.563");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 30); // Peak downlink
                put("HOST", "fast.downlink.com");
                put("EVENT_TIME", "2012-05-16 23:19:00.463");
                put("DATETIME_ID", "2012-05-16 23:19:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 20);
                put("HOST", "another.host.com");
                put("EVENT_TIME", "2012-05-16 23:19:00.763");
                put("DATETIME_ID", "2012-05-16 23:19:00.763");
            }
        });

        populateClassificationData();
    }

    private void prepareDataForLatency() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 5);
                put("HOST", "slow.host.com");
                put("SETUP_TIME_TERM", "20"); //UE -> Gn
                put("SETUP_TIME_NET", "22"); //Gn -> Server
                put("EVENT_TIME", "2012-05-16 23:15:00.463");
                put("DATETIME_ID", "2012-05-16 23:15:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 15); // Peak for uplink
                put("HOST", "fast.uplink.com");
                put("SETUP_TIME_TERM", "10"); //UE -> Gn
                put("SETUP_TIME_NET", "12"); //Gn -> Server
                put("EVENT_TIME", "2012-05-16 23:15:00.563");
                put("DATETIME_ID", "2012-05-16 23:15:00.563");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 30); // Peak downlink
                put("HOST", "fast.downlink.com");
                put("SETUP_TIME_TERM", "5"); //UE -> Gn
                put("SETUP_TIME_NET", "7"); //Gn -> Server
                put("EVENT_TIME", "2012-05-16 23:19:00.463");
                put("DATETIME_ID", "2012-05-16 23:19:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 20);
                put("HOST", "another.host.com");
                put("SETUP_TIME_TERM", "8"); //UE -> Gn
                put("SETUP_TIME_NET", "10"); //Gn -> Server
                put("EVENT_TIME", "2012-05-16 23:19:00.763");
                put("DATETIME_ID", "2012-05-16 23:19:00.763");
            }
        });

        populateClassificationData();
    }

    private void prepareDataForLatencyTest2() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 5);
                put("HOST", "slow.host.com");
                put("SETUP_TIME_TERM", "10"); //UE -> Gn
                put("SETUP_TIME_NET", null); // 10 Gn -> Server
                put("EVENT_TIME", "2012-05-16 23:15:00");
                put("DATETIME_ID", "2012-05-16 23:15:00");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 0); // Uplink
                put("THROUGHPUT", 15); // Peak for uplink
                put("HOST", "fast.uplink.com");
                put("SETUP_TIME_TERM", "20"); //UE -> Gn
                put("SETUP_TIME_NET", null); //12 Gn -> Server
                put("EVENT_TIME", "2012-05-16 23:15:00");
                put("DATETIME_ID", "2012-05-16 23:15:00");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 30); // Peak downlink
                put("HOST", "fast.downlink.com");
                put("SETUP_TIME_TERM", "5"); //UE -> Gn
                put("SETUP_TIME_NET", null); //7 Gn -> Server
                put("EVENT_TIME", "2012-05-16 23:19:00");
                put("DATETIME_ID", "2012-05-16 23:19:00");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_TCP_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("DOWNLINK", 1); // Downlink
                put("THROUGHPUT", 20);
                put("HOST", "another.host.com");
                put("SETUP_TIME_TERM", "8"); //UE -> Gn
                put("SETUP_TIME_NET", null); //22 Gn -> Server
                put("EVENT_TIME", "2012-05-16 23:19:00");
                put("DATETIME_ID", "2012-05-16 23:19:00");
            }
        });

        populateClassificationData();
    }

    /**
     * Data for classification tables, can be reused for each test case
     */
    private void populateClassificationData() throws Exception {
        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("BYTES_DOWNLINK", 3500);
                put("BYTES_UPLINK", 0);
                put("EVENT_TIME", "2012-05-16 23:29:00.463");
                put("DATETIME_ID", "2012-05-16 23:29:00.463");
            }
        });

        insertRow(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, new HashMap<String, Object>() {
            {
                put("IMSI", "123");
                put("EVENT_ID", "1");
                put("BYTES_DOWNLINK", 5000);
                put("BYTES_UPLINK", 0);
                put("EVENT_TIME", "2012-05-16 23:29:00.563");
                put("DATETIME_ID", "2012-05-16 23:29:00.463");
            }
        });
    }

    private void createTableSchema() throws Exception {
        final List<ColumnDetails> tcpRawTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("IMSI_MCC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("START_APN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("END_APN", "varchar", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DURATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IS_PARTIAL", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DOWNLINK", "bit", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATA_RECEIVED", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PIPE_THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NONSS_PIPE_THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CHANNEL_PIPE_THROUGHPUT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ALONE_RATIO", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MAX_RWIN", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SETUP_TIME_TERM", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SETUP_TIME_NET", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MAX_DATA_PACKET", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKET_LOSS_TERM", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKET_LOSS_NET", "float", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CONTENT_TYPE", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("HOST", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_IP_ADDRESS", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACTIVITY_START_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ACTIVITY_DURATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("NO_OF_SAMPLES", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FIVE_MIN_AGG_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
            }
        };

        final List<ColumnDetails> classificationRawTableSchema = new ArrayList<ColumnDetails>() {
            {
                add(new ColumnDetails("IMSI_MCC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI_MNC", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMEISV", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_ID", "smallint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("APN", "varchar (127)", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DURATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKETS_DOWNLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PACKETS_UPLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BYTES_DOWNLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("BYTES_UPLINK", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("PROTOCOL", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FUNCTION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ENCAPSULATION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("ENCRYPTION", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("SERVICE_PROVIDER", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("CLIENT", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("UE_IP_ADDRESS", "binary", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("TAC", "unsigned int", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("FIVE_MIN_AGG_TIME", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("IMSI", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("MSISDN", "unsigned bigint", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("DATETIME_ID", "timestamp", Nullable.CAN_BE_NULL));
                add(new ColumnDetails("EVENT_TIME", "timestamp", Nullable.CAN_BE_NULL));
            }
        };

        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_TCP_RAW, tcpRawTableSchema);
        createTemporaryTable(TEMP_EVENT_E_USER_PLANE_CLASSIFICATION_RAW, classificationRawTableSchema);
    }

    private MultivaluedMap<String, String> getRequestParameters(final String dateFrom, final String dateTo,
            final String timeFrom, final String timeTo) {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TIME_FROM_QUERY_PARAM, timeFrom);
        requestParameters.putSingle(TIME_TO_QUERY_PARAM, timeTo);

        requestParameters.putSingle(DATE_FROM_QUERY_PARAM, dateFrom);
        requestParameters.putSingle(DATE_TO_QUERY_PARAM, dateTo);

        requestParameters.putSingle(TZ_OFFSET, "+0000");
        requestParameters.putSingle(IMSI_PARAM, "123");
        requestParameters.putSingle(INTERVAL_PARAM, "1");
        return requestParameters;
    }

    private JSONObject runQuery() throws JSONException {
        final MultivaluedMap<String, String> requestParameters = getRequestParameters("15052012", "16052012", "2300",
                "2359");

        final String json = runQuery(sBrowserTcpPerfSumService, requestParameters);
        final JSONObject jsonResult = new JSONObject(json);
        final JSONArray data = jsonResult.getJSONArray("data");
        return data.getJSONObject(0);
    }
}