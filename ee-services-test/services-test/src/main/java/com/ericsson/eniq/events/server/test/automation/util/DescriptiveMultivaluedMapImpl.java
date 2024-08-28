/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.automation.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * Convenience wrapper for pretty print in tests.
 * 
 * @author ejedmar
 * @since 2011
 *
 */
public class DescriptiveMultivaluedMapImpl extends MultivaluedMapImpl {

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        final StringBuilder toStringBuilder = new StringBuilder();
        final Iterator<java.util.Map.Entry<String, List<String>>> iterator = this.entrySet().iterator();
        while (iterator.hasNext()) {
            final java.util.Map.Entry<String, List<String>> next = iterator.next();
            toStringBuilder.append(next.getKey()).append("=").append(Arrays.deepToString(next.getValue().toArray()))
                    .append(",");
        }
        return toStringBuilder.toString();
    }
}
