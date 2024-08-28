package com.ericsson.eniq.events.server.utils.sbr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: eeidpar
 * Date: 12/04/12
 * Time: 08:16
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionPropertiesDecoder {

    private ConnectionPropertiesDecoder() {
    }

    public static final String CFA_SOURCE_CONNECTION_PROPERTIES = "CFA_SOURCE_CONNECTION_PROPERTIES";

    public static final String CFA_TARGET_CONNECTION_PROPERTIES = "CFA_TARGET_CONNECTION_PROPERTIES";

    public static final String CFA_WANTED_CONNECTION_PROPERTIES = "CFA_WANTED_CONNECTION_PROPERTIES";

    public static final String HFA_SOURCE_CONNECTION_PROPERTIES = "HFA_SOURCE_CONNECTION_PROPERTIES";

    public static final String HFA_TARGET_CONNECTION_PROPERTIES = "HFA_TARGET_CONNECTION_PROPERTIES";

    public static final String HFA_WANTED_CONNECTION_PROPERTIES = "HFA_WANTED_CONNECTION_PROPERTIES";

    private static final String NEWLINE = "\n";

    private static final String PROPERTY_SEPARATOR_WITH_SPACE = ": ";

    private static Map<String, List<DecoderInfo>> decoderMap = new HashMap<String, List<DecoderInfo>>();

    static {
        //setup the decodings map
        setupDecoderMap();
    }

    private static void setupDecoderMap() {
        final List<DecoderInfo> decoderInfoCfaSourceTargetConnProp = new ArrayList<DecoderInfo>();
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(1, new String[] { "not used", "used" }, 0,
                "Compressed Mode DL"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(2, new String[] { "not used", "used" }, 1,
                "Compressed Mode UL"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(4, new String[] { "10ms", "2ms" }, 2, "TTL length"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(8, new String[] { "no", "yes" }, 3,
                "Connection using EL2"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(16, new String[] { "deactivated", "activated" }, 4,
                "64QAM"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(96, new String[] { "Not applicable",
                "AMR-NB MM Multi-rate", "AMR-NB MM Single-rate 12.2" }, 5, "Speech Category"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(128, new String[] { "deactivated", "activated" }, 7,
                "MIMO"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(256, new String[] { "no", "yes" }, 8, "SRB On Hs"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(512, new String[] { "no", "yes" }, 9, "SRB On EUL"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(1024, new String[] { "deactivated", "activated" }, 10,
                "Multicarrier"));
        decoderInfoCfaSourceTargetConnProp
                .add(new DecoderInfo(2048, new String[] { "not used", "used" }, 11, "HS-FACH"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(4096, new String[] { "not used", "used" }, 12,
                "Continuous Packet Connectivity"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(8192, new String[] { "not used", "used" }, 13,
                "Dual-band HSDPA Multicarrier"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(16384, new String[] { "not used", "used" }, 14,
                "Enhanced UE DRX"));
        decoderInfoCfaSourceTargetConnProp.add(new DecoderInfo(32768, new String[] { "no", "yes" }, 15,
                "Connection using IL2"));

        //add as cfa source and target connection properties
        decoderMap.put(CFA_SOURCE_CONNECTION_PROPERTIES, decoderInfoCfaSourceTargetConnProp);
        decoderMap.put(CFA_TARGET_CONNECTION_PROPERTIES, decoderInfoCfaSourceTargetConnProp);

        final List<DecoderInfo> decoderInfoCfaWantedConnProp = new ArrayList<DecoderInfo>();
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(1, new String[] { "not used", "used" }, 0,
                "Compressed Mode DL"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(2, new String[] { "not used", "used" }, 1,
                "Compressed Mode UL"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(4, new String[] { "10ms", "2ms" }, 2, "TTL length"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(8, new String[] { "no", "yes" }, 3, "Connection using EL2"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(16, new String[] { "deactivated", "activated" }, 4, "64QAM"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(96, new String[] { "Not applicable", "AMR-NB MM Multi-rate",
                "AMR-NB MM Single-rate 12.2" }, 5, "Speech Category"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(128, new String[] { "deactivated", "activated" }, 7, "MIMO"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(256, new String[] { "no", "yes" }, 8, "SRB On Hs"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(512, new String[] { "no", "yes" }, 9, "SRB On EUL"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(1024, new String[] { "deactivated", "activated" }, 10,
                "Multicarrier"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(2048, new String[] { "not used", "used" }, 11, "HS-FACH"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(4096, new String[] { "not used", "used" }, 12,
                "Continuous Packet Connectivity"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(8192, new String[] { "not used", "used" }, 13,
                "Dual-band HSDPA Multicarrier"));
        decoderInfoCfaWantedConnProp.add(new DecoderInfo(16384, new String[] { "not used", "used" }, 14,
                "Enhanced UE DRX"));

        //add as cfa wanted connection properties
        decoderMap.put(CFA_WANTED_CONNECTION_PROPERTIES, decoderInfoCfaWantedConnProp);
        //also same decoding settings for HFA, source, target and wanted.
        decoderMap.put(HFA_SOURCE_CONNECTION_PROPERTIES, decoderInfoCfaWantedConnProp);
        decoderMap.put(HFA_TARGET_CONNECTION_PROPERTIES, decoderInfoCfaWantedConnProp);
        decoderMap.put(HFA_WANTED_CONNECTION_PROPERTIES, decoderInfoCfaWantedConnProp);
    }

    public static String decode(final int value, final String decodingKey) {
        final List<DecoderInfo> decodingInfoList = decoderMap.get(decodingKey);
        final StringBuilder decodedValue = new StringBuilder();

        for (final DecoderInfo decoderInfo : decodingInfoList) {
            final int decodeIndex = (decoderInfo.getBitMask() & value) >>> decoderInfo.bitShifts;
            decodedValue.append(decoderInfo.fieldLabel).append(PROPERTY_SEPARATOR_WITH_SPACE)
                    .append(decoderInfo.getDecodedBitValues()[decodeIndex]).append(NEWLINE);
        }
        return decodedValue.toString();
    }

    private static class DecoderInfo {
        final private int bitMask;

        final private String[] decodedBitValues;

        final private int bitShifts;

        final private String fieldLabel;

        private DecoderInfo(final int bitMask, final String[] decodedBitValues, final int bitShifts,
                final String fieldLabel) {
            this.bitMask = bitMask;
            this.decodedBitValues = decodedBitValues;
            this.bitShifts = bitShifts;
            this.fieldLabel = fieldLabel;
        }

        public int getBitMask() {
            return bitMask;
        }

        public String[] getDecodedBitValues() {
            return decodedBitValues;
        }

        @SuppressWarnings("unused")
        public int getBitShifts() {
            return bitShifts;
        }

        @SuppressWarnings("unused")
        public String getFieldLabel() {
            return fieldLabel;
        }
    }
}
