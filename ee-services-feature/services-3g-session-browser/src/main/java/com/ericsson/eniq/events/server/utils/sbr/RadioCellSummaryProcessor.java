package com.ericsson.eniq.events.server.utils.sbr;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: eeidpar
 * Date: 22/02/12
 * Time: 08:31
 * To change this template use File | Settings | File Templates.
 */
public class RadioCellSummaryProcessor implements IObjectmapProcessor<List<Map<String, Object>>> {

    @Override
    public Object processObjectMap(final List<Map<String, Object>> objectMap) {
        if (objectMap.size() > 0) {
            boolean foundValidValue = false;
            final Map<String, Object> resultMap = objectMap.get(0);
            for (final String key : resultMap.keySet()) {
                final String valueStr = String.valueOf(resultMap.get(key));
                if (!valueStr.equals("") && !valueStr.equals("null")) {
                    foundValidValue = true;
                    break;
                }
            }
            if (!foundValidValue) {
                resultMap.clear();
            }
        }
        return objectMap;
    }
}
