/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.queryresults;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.ericsson.eniq.events.server.json.JSONArray;
import com.ericsson.eniq.events.server.json.JSONObject;
import com.ericsson.eniq.events.server.test.util.JSONTestUtils;

/**
 * @author eemecoy
 *
 */
public class ResultTranslator<T extends QueryResult> {

    /**
     * Translate the json result to one of the MultipleRankingXXResult class (which class is created is specified by
     * the classToCreate parameter)
     * 
     * @param json
     * @param classToCreate
     * @return
     * @throws Exception
     */
    public List<T> translateResult(final String json, final Class<T> clazz) throws Exception {
        final JSONArray dataElement = JSONTestUtils.getDataElement(json);
        final List<T> results = new ArrayList<T>();
        for (int i = 0; i < dataElement.length(); i++) {
            final JSONObject object = (JSONObject) dataElement.get(i);

            final Constructor<T> constructor = clazz.getConstructor();
            final T t = constructor.newInstance();
            t.parseJSONObject(object);
            results.add(t);
        }
        return results;
    }
}
