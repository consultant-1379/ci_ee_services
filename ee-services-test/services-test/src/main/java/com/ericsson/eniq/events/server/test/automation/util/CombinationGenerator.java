package com.ericsson.eniq.events.server.test.automation.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface CombinationGenerator<T> {

    public abstract int getTotal();

    /**
     * Generates all combinations at once.
     * @return all combinations as a list of java.util.Map
     */
    public abstract List<Map<String, T>> getAllCombinations();

    public abstract Iterator<Map<String, T>> iterator();

}