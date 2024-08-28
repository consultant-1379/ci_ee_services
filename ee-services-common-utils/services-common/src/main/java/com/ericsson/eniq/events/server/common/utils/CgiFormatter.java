package com.ericsson.eniq.events.server.common.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * -----------------------------------------------------------------------
 * Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
public class CgiFormatter {

    private static final char CGI_SEPARATOR = '-';

    static CgiFormatter instance = null;

    private CgiFormatter() {
    }

    public static CgiFormatter getInstance() {
        if (instance == null) {
            instance = new CgiFormatter();
        }
        return instance;
    }

    public String getFormattedCgi(final ResultSet resultSet) throws SQLException {
        return getFormattedCgi(resultSet, "");
    }

    public String getFormattedCgi(final ResultSet resultSet, final String nameExtention) throws
            SQLException {
        final short mcc = resultSet.getShort("mcc" + nameExtention);
        final short mnc = resultSet.getShort("mnc" + nameExtention);
        final int enodeb_id = resultSet.getInt("enodeb_id" + nameExtention);
        final short cellid = resultSet.getShort("cellid" + nameExtention);
        return getFormattedCgi(mcc, mnc, enodeb_id, cellid).toString();
    }

    public StringBuilder getFormattedCgi(final short mcc, final short mnc, final int enodeb_id, final short cellid) {
        final StringBuilder builder = new StringBuilder(30);
        builder.append(String.valueOf(mcc));
        builder.append(CGI_SEPARATOR);
        builder.append(String.valueOf(mnc));
        builder.append(CGI_SEPARATOR);
        builder.append(String.valueOf(enodeb_id));
        builder.append(CGI_SEPARATOR);
        builder.append(String.valueOf(cellid));
        return builder;
    }
}

