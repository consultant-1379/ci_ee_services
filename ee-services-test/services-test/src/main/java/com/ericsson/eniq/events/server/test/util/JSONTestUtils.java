package com.ericsson.eniq.events.server.test.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.springframework.stereotype.Component;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONException;
import com.ericsson.eniq.events.server.json.JSONObject;

/**
 * Various validation methods for JSON result objects.
 * 
 * @author edeccox
 *
 */
@Component
public class JSONTestUtils {

    static final String JSON_SUCCESS_TOKEN = "\"success\":\"";

    static final String DATA = "data";

    public static boolean isValidJson(final String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (final JSONException e) {
            return false;
        }

    }

    public static boolean hasValidResultFormat(final String json) throws JSONException {

        final JSONObject jsonObject = new JSONObject(json);
        return (jsonObject.get("success") != null && jsonObject.get("errorDescription") != null && jsonObject
                .get("data") != null);
    }

    public static Object getField(final String json, final String fieldName) throws JSONException {
        return new JSONObject(json).get(fieldName);
    }

    /**
     * Get the "data" element in a standard json object returned from a query
     * @param json
     * @return
     * @throws JSONException 
     */
    public static JSONArray getDataElement(final String json) throws JSONException {
        final JSONObject jsonObject = new JSONObject(json);
        final JSONArray data = (JSONArray) jsonObject.get(DATA);
        return data;
    }

    public static String getErrorDescription(final String json) throws JSONException {
        final JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getString("errorDescription");
    }

    public static void assertJSONSucceeds(final String testString) {
        assertResultContains(testString, JSON_SUCCESS_TOKEN + true + "\"");
    }

    public static void assertResultContains(final String testString, final String matchString) {
        assertThat("Result reported failure: " + testString, testString.contains(matchString), is(true));
    }

    public static void assertDataIsNotInJSONFormat(final String result) {
        assertThat("Result contained JSON like syntax: " + result, result.contains("{" + JSON_SUCCESS_TOKEN), is(false));
    }

    public static void assertNoSuchDisplay(final String testString, final String displayParameter) {
        assertResultContains(testString, "No such display type : " + displayParameter);

    }

    public static void assertJSONErrorResult(final String testString) {
        assertResultContains(testString, JSON_SUCCESS_TOKEN + "false\"");
    }

    public static void assertInputValidValue(final String json) {
        assertThat(json, is("{\"success\":\"false\",\"errorDescription\":\"Please input a valid value\"}"));
    }

}
