package com.ericsson.eniq.events.server.test.util;

import static com.ericsson.eniq.events.server.test.util.JSONTestUtils.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.springframework.stereotype.Component;

import com.ericsson.eniq.events.server.logging.ServicesLogger;

/**
 * Created by IntelliJ IDEA.
 * User: ericker
 * Date: 19/12/11
 * Time: 11:27
 * To change this template use File | Settings | File Templates.
 */
@Component
public class JSONAssertUtils {

    public void assertJSONSucceeds(final String testString, final String clazz) {
        assertResultContains(testString, JSON_SUCCESS_TOKEN + true + "\"", clazz);
    }

    public void assertResultContains(final String testString, final String matchString) {
        assertResultContains(testString, matchString, null);
    }

    public void assertResultContains(final String testString, final String matchString, final String clazz) {
        logTestResult(testString, clazz);
        assertThat("Result reported failure: " + testString, testString.contains(matchString), is(true));
    }

    public void assertDataIsNotInJSONFormat(final String result, final String clazz) {
        logTestResult(result, clazz);
        assertThat("Result contained JSON like syntax: " + result, result.contains("{" + JSON_SUCCESS_TOKEN), is(false));
    }

    public void assertDataIsNotInJSONFormat(final String result) {
        assertDataIsNotInJSONFormat(result, null);
    }

    public void assertNoSuchDisplay(final String testString, final String displayParameter, final String clazz) {
        assertResultContains(testString, "No such display type : " + displayParameter, clazz);

    }

    public void assertJSONErrorResult(final String testString) {
        assertJSONErrorResult(testString, null);
    }

    public void assertJSONErrorResult(final String testString, final String clazz) {
        assertResultContains(testString, JSON_SUCCESS_TOKEN + "false\"", clazz);
    }

    public void assertInputValidValue(final String json) {
        assertInputValidValue(json, null);
    }

    public void assertInputValidValue(final String json, final String clazz) {
        logTestResult(json, clazz);
        assertThat(json, is("{\"success\":\"false\",\"errorDescription\":\"Please input a valid value\"}"));
    }

    private void logTestResult(final String testString, final String clazz) {
        ServicesLogger.detailed(clazz, "assertResultContains", "JSON = " + testString);
    }

    public void assertJSONSucceeds(final String result) {
        assertJSONSucceeds(result, null);
    }
}
