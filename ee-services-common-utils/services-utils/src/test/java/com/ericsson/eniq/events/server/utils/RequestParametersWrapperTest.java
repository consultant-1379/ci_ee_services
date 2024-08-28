/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.test.common.ApplicationTestConstants.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Test;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author eemecoy
 *
 */
public class RequestParametersWrapperTest {

    @Test
    public void testIsAccessAreaIsTrueForACellQuery() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(NODE_PARAM, SAMPLE_BSC_CELL);
        assertThat(new RequestParametersWrapper(requestParameters).isAccessArea(), is(true));
    }

    @Test
    public void testIsAccessAreaIsFalseForACellGroupQuery() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_CELL);
        requestParameters.putSingle(GROUP_NAME_PARAM, SAMPLE_ECELL_GROUP);
        assertThat(new RequestParametersWrapper(requestParameters).isAccessArea(), is(false));

    }

    @Test
    public void testIsControllerIsTrueForABSCQuery() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(NODE_PARAM, SAMPLE_BSC);
        assertThat(new RequestParametersWrapper(requestParameters).isController(), is(true));
    }

    @Test
    public void testIsControllerIsFalseForABscGroupQuery() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_BSC);
        requestParameters.putSingle(GROUP_NAME_PARAM, SAMPLE_BSC_GROUP);
        assertThat(new RequestParametersWrapper(requestParameters).isController(), is(false));

    }

    @Test
    public void testGettingTacParamFromNodeWhereTheresACommaInTheMarketingName() {
        final String tac = "35197003";
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        final String marketingNameWithComma = "1208,1209";
        requestParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        requestParameters.putSingle(NODE_PARAM, marketingNameWithComma + COMMA + tac);
        assertThat(new RequestParametersWrapper(requestParameters).getTacParamFromNodeOrTacParam(), is(tac));
    }

    @Test
    public void testRequestContainsTACParamReturnsFalseIfRequestDoesntContainTypeParam() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        assertThat(new RequestParametersWrapper(requestParameters).requestContainsTACParam(), is(false));
    }

    @Test
    public void testRequestContainsTACParamReturnsFalseIfRequestDoesntContainTACParam() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_APN);
        assertThat(new RequestParametersWrapper(requestParameters).requestContainsTACParam(), is(false));
    }

    @Test
    public void testRequestContainsTACParamReturnsFalseIfRequestContainsEmptyTACParam() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        requestParameters.putSingle(TAC_PARAM, "");
        assertThat(new RequestParametersWrapper(requestParameters).requestContainsTACParam(), is(false));
    }

    @Test
    public void testRequestContainsTACParamReturnsTrueIfRequestContainsNonEmptyTACParam() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        requestParameters.putSingle(TAC_PARAM, SAMPLE_TAC_TO_STRING);
        assertThat(new RequestParametersWrapper(requestParameters).requestContainsTACParam(), is(true));
    }

    @Test
    public void testGetTacParamFromNodeOrTacParamReturnsNullIfItsATacGroup() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        requestParameters.putSingle(GROUP_NAME_PARAM, "some group name");
        assertThat(new RequestParametersWrapper(requestParameters).getTacParamFromNodeOrTacParam(), is((String) null));
    }

    @Test
    public void testgetTacParamFromNodeOrTacParamReturnsTacFromTheNodeParam() {
        final String tac = "123";
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_TAC);
        requestParameters.putSingle(NODE_PARAM, "some marketing name" + COMMA + tac);
        assertThat(new RequestParametersWrapper(requestParameters).getTacParamFromNodeOrTacParam(), is(tac));
    }

    @Test
    public void testgetTacParamFromNodeOrTacParamReturnsNullIfTheNodeTypeIsntTac() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_APN);
        requestParameters.putSingle(NODE_PARAM, "some apn");
        assertThat(new RequestParametersWrapper(requestParameters).getTacParamFromNodeOrTacParam(), is((String) null));
    }

    @Test
    public void testgetTacParamFromNodeOrTacParamReturnsTacIfTheTacParamIsSet() {
        final String tac = "456";
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TAC_PARAM, tac);
        assertThat(new RequestParametersWrapper(requestParameters).getTacParamFromNodeOrTacParam(), is(tac));
    }

    @Test
    public void testgetTacParamFromNodeOrTacParamReturnsNullIfTheresNoNodeOrTacParam() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        assertThat(new RequestParametersWrapper(requestParameters).getTacParamFromNodeOrTacParam(), is((String) null));
    }

    @Test
    public void testGetDisplay() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(DISPLAY_PARAM, GRID_PARAM);
        assertThat(new RequestParametersWrapper(requestParameters).getDisplay(), is(GRID_PARAM));
    }

    @Test
    public void testGetType() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(TYPE_PARAM, TYPE_IMSI);
        assertThat(new RequestParametersWrapper(requestParameters).getType(), is(TYPE_IMSI));
    }

    @Test
    public void testGetCountDoesNotExceedAllowableSize() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(MAX_ROWS, Integer.toString(10000));
        final int maxAllowableSize = 10;
        assertThat(new RequestParametersWrapper(requestParameters).getCountValue(maxAllowableSize),
                is(maxAllowableSize));
    }

    @Test
    public void testGetCountReturnsMaxRowsValue() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        final int maxRowsValue = 3;
        requestParameters.putSingle(MAX_ROWS, Integer.toString(maxRowsValue));
        assertThat(new RequestParametersWrapper(requestParameters).getCountValue(100), is(maxRowsValue));
    }

    @Test
    public void testGetGroupNameValue() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        final String groupValue = "Some Group";
        requestParameters.putSingle(GROUP_NAME_PARAM, groupValue);
        assertThat(new RequestParametersWrapper(requestParameters).getGroupName(), is(groupValue));
    }

    @Test
    public void testGetEventIdValue() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        final String eventIdValue = "12";
        requestParameters.putSingle(EVENT_ID_PARAM, eventIdValue);
        assertThat(new RequestParametersWrapper(requestParameters).getEventId(), is(eventIdValue));
    }

    @Test
    public void testGetKeyValue() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        final String keyValue = "SUM";
        requestParameters.putSingle(KEY_PARAM, keyValue);
        assertThat(new RequestParametersWrapper(requestParameters).getKey(), is(keyValue));
    }

    @Test
    public void testGetCountForMaxRowsNotValidNumberIsMaxAllowableSize() {
        final MultivaluedMap<String, String> requestParameters = new MultivaluedMapImpl();
        requestParameters.putSingle(MAX_ROWS, "xyz");
        final int maxAllowableSize = 100;
        assertThat(new RequestParametersWrapper(requestParameters).getCountValue(maxAllowableSize),
                is(maxAllowableSize));
    }

}
