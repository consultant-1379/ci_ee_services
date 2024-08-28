/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.automation.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Generates combinations of lists of values.
 * 
 * @author ejedmar
 * @since 2011
 *
 */
public class CombinationGeneratorImpl<T> implements CombinationGenerator<T> {

    private final List<List<T>> uncombinedList;

    private final List<String> parameterNames;

    private final int total;

    private CombinationGeneratorImpl(final List<List<T>> uncombinedList, final List<String> parameterNames) {
        this.uncombinedList = Collections.unmodifiableList(uncombinedList);
        this.parameterNames = Collections.unmodifiableList(parameterNames);
        this.total = calculateTotal();
    }

    /**
     * @param builder
     */
    private CombinationGeneratorImpl(final Builder<T> builder) {
        this(builder.uncombinedList, builder.parameterNames);
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.common.CombinationGenerator1#getTotal()
     */
    @Override
    public int getTotal() {
        return total;
    }

    private int calculateTotal() {
        final Iterator<List<T>> iterator = this.uncombinedList.iterator();
        long combinationsNo = 1;
        while (iterator.hasNext()) {
            combinationsNo = combinationsNo * iterator.next().size();
            if (combinationsNo > Integer.MAX_VALUE) {
                throw new ArithmeticException("Too many combinations!");
            }
        }
        return (int) combinationsNo;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.common.CombinationGenerator1#getAllCombinations()
     */
    @Override
    public List<Map<String, T>> getAllCombinations() {
        final List<Map<String, T>> resultAll = new ArrayList<Map<String, T>>();
        final Iterator<Map<String, T>> iterator = iterator();
        while (iterator.hasNext()) {
            resultAll.add(iterator.next());
        }
        return resultAll;
    }

    /* (non-Javadoc)
     * @see com.ericsson.eniq.events.server.test.common.CombinationGenerator1#iterator()
     */
    @Override
    public Iterator<Map<String, T>> iterator() {
        return new Itr();
    }

    private List<List<T>> getUncombinedList() {
        return uncombinedList;
    }

    public static class Builder<T> {

        private final List<List<T>> uncombinedList = new ArrayList<List<T>>();

        private final List<String> parameterNames = new ArrayList<String>();

        public CombinationGenerator<T> build() {
            return new CombinationGeneratorImpl<T>(this);
        }

        public Builder<T> add(final String parameterName, final T... elements) {
            uncombinedList.add(Arrays.asList(elements));
            parameterNames.add(parameterName);
            return this;
        }
    }

    private class Itr implements Iterator<Map<String, T>> {

        private final int[] currentPositions;

        private int numLeft;

        public Itr() {
            this.currentPositions = new int[uncombinedList.size()];
            numLeft = getTotal();
        }

        @Override
        public boolean hasNext() {
            return numLeft > 0;
        }

        /**
         * Generates a single combination at each time it is called. It's not a recursive approach.
         * @return a single combination
         */
        @Override
        public Map<String, T> next() {
            final Map<String, T> result = new HashMap<String, T>();

            for (int i = 0; i < getUncombinedList().size(); i++) {
                final List<T> currentSublist = getUncombinedList().get(i);
                if (currentSublist.get(currentPositions[i]) != null) {
                    result.put(parameterNames.get(i), currentSublist.get(currentPositions[i]));
                }
            }

            for (int i = 0; i < getUncombinedList().size(); i++) {
                final List<T> currentSublist = getUncombinedList().get(i);
                final int size = currentSublist.size();
                if ((currentPositions[i] + 1) >= size) {
                    currentPositions[i] = 0;
                } else {
                    currentPositions[i] = currentPositions[i] + 1;
                    break;
                }
            }

            numLeft--;
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet");
        }
    }
}