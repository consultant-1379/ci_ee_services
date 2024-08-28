/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.stubs;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

/**
 * @author ericker
 * @since 2010
 *
 */
public class StringOutputStream extends ServletOutputStream {

    StringBuilder mBuf = new StringBuilder();

    @Override
    public void write(final int b) throws IOException {
        mBuf.append((char) b);
    }

    public String getString() {
        return mBuf.toString();
    }
}
