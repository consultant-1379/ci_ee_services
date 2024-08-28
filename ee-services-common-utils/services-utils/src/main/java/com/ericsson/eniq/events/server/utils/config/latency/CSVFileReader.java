/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.ericsson.eniq.events.server.logging.ServicesLogger;

/**
 * Utility class to read contents of a CSV file
 * 
 * @TODO - move this class to the new utils module once the maven refactoring is merged in
 * 
 * @author eemecoy
 *
 */
public class CSVFileReader {

    /**
     * Read a csv file on the classpath
     * 
     * @param csvFileName               name of csv file
     * @return map of the values in the file - the key to each map entry is the first token in each line.  The value
     * in the map is a list of all comma separated tokens in that line (including the first token ie the key)
     */
    public Map<String, List<String>> readCSVFile(final String csvFileName) {
        final Map<String, List<String>> values = new HashMap<String, List<String>>();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {

            inputStream = getResourceAsStream(csvFileName);
            if (inputStream == null) {
                throw new FileNotFoundException("Could not find file " + csvFileName);
            }
            inputStreamReader = createInputStreamReader(inputStream);
            bufferedReader = createBufferedReader(inputStreamReader);
            String line = "";
            StringTokenizer stringTokenizer = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (isNotAComment(line)) {
                    final List<String> csvValues = new ArrayList<String>();//NOPMD eemecoy 25/2/11, a necessary evil
                    stringTokenizer = new StringTokenizer(line, COMMA);//NOPMD eemecoy 25/2/11, a necessary evil
                    while (stringTokenizer.hasMoreTokens()) {
                        final String nextToken = stringTokenizer.nextToken();
                        csvValues.add(nextToken);
                    }

                    values.put(csvValues.get(0), csvValues);
                }
            }
        } catch (final IOException e) {
            ServicesLogger.error(getClass().getName(), "applicationStartup", e);
        } finally {
            try {
                close(bufferedReader);
                close(inputStreamReader);
                close(inputStream);
            } catch (final IOException e) {
                ServicesLogger.error(getClass().getName(), "readCSVFile", e);
            }
        }
        return values;
    }

    InputStream getResourceAsStream(final String csvFileName) {
        return this.getClass().getResourceAsStream(csvFileName);
    }

    BufferedReader createBufferedReader(final InputStreamReader inputStreamReader) {
        return new BufferedReader(inputStreamReader);
    }

    InputStreamReader createInputStreamReader(final InputStream inputStream) {
        return new InputStreamReader(inputStream);
    }

    private void close(final Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

    private boolean isNotAComment(final String line) {
        return !line.startsWith(COMMENT_PREFIX);
    }

}
