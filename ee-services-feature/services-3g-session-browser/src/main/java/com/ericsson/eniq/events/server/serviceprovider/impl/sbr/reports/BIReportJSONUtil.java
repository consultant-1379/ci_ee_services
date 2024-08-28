package com.ericsson.eniq.events.server.serviceprovider.impl.sbr.reports;

import com.ericsson.eniq.events.server.serviceprovider.impl.sbr.reports.client.BIObjectTreeDataType;

import java.util.List;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.COMMA;

/**
 * Describe BIReportJSONUtil
 */
public class BIReportJSONUtil {

    static final String SUCCESS_JSON_MSG_TEMPLATE = "{\"success\":\"true\" , \"bisServiceSuccess\" :\"true\" ,\"errorDescription\" : \"\" , \"data\" : [ %s ] }";

    static final String ERROR_JSON_MSG_TEMPLATE = "{\"success\":\"true\" , \"bisServiceSuccess\" :\"false\" ,\"errorDescription\" : \"%s\" }";

    private static final String FILE_RECORD_TEMPLATE = "{ \"name\": \"%s\" ,\"type\":\"file\" , \"link\" : \"%s\" }";

    private static final String FOLDER_RECORD_TEMPLATE = "{ \"name\": \"%s\" ,\"type\":\"folder\" , \"list\": [ %s ] }";

    static final String WRONG_URL_FORMAT_MSG = "Wrong URL format.";

    static final String INVALID_CREDENTIALS_MSG = "Invalid credentials.";

    static final String BIS_ERROR_MSG = "Business Intelligence Service is not available from the provided URL.";

    static final String BICATALOG_ERROR_MSG = "Failed to get reports.";

    static final String UNKNOWN_ERROR_MSG = "Unknown error.";

    /**
     * the private constructor can prevent the initialization of the object
     */
    private BIReportJSONUtil() {

    }

    /**
     * converts a datatype object {@link com.ericsson.eniq.events.server.serviceprovider.impl.sbr.reports.client.BIObjectTreeDataType} to JSON
     *
     * @param biObjectTree the object to be converted.
     * @return a JSON string
     */
    public static String getBiResponse(final BIObjectTreeDataType biObjectTree) {
        return convertBIObjectTreeDataTypeToJSON(biObjectTree);
    }

    /**
     * This method converts the  To {@link BIObjectTreeDataType} JSON recursively. will move to jackson JSON-POJO
     * mapping in the future .
     *
     * @param biObjectTree the object represent the reports tree.
     * @return the a json string ,which is the converted BIObjectTreeDataType object.
     */
    private static String convertBIObjectTreeDataTypeToJSON(final BIObjectTreeDataType biObjectTree) {
        final List<BIObjectTreeDataType> biObjects = biObjectTree.getChildren();

        final StringBuffer stringBuffer = new StringBuffer();
        for (final BIObjectTreeDataType biObject : biObjects) {
            if (biObject.getType() == BIObjectTreeDataType.ObjectType.FILE) {
                stringBuffer.append(String.format(FILE_RECORD_TEMPLATE, biObject.getDisplayName(), biObject.getFileURL()));
            } else {
                final String jsonString = convertBIObjectTreeDataTypeToJSON(biObject);
                stringBuffer.append(String.format(String.format(FOLDER_RECORD_TEMPLATE, biObject.getDisplayName(), jsonString)));
            }
            stringBuffer.append(COMMA);
        }
        stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length()); // delete the last "," symbol

        return stringBuffer.toString();

    }
}
