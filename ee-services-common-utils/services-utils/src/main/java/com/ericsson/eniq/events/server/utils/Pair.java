/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils;

/**
 * Pair is used to store two variables in a pair
 * @author ezhibhe
 * @since 2011
 */
public class Pair<T1, T2> {
    public Pair(final T1 aFirst, final T2 aSecond) {
        first = aFirst;
        second = aSecond;
    }

    public T1 first;

    public T2 second;
}