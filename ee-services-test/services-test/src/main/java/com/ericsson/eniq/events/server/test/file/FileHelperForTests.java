/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * utility class to read file contents
 * There is a similiar class in the production code, but cannot reuse that code here due to maven cyclical dependencies
 * 
 * @author eemecoy
 *
 */
public class FileHelperForTests {

    /**
     * Read file from classpath
     * 
     * @param fileName  name of file on classpath
     * @return  list of the lines in the file
     */
    public List<String> readFileFromClasspath(final String fileName) throws IOException {
        final InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException(fileName);
        }
        return readInputStream(inputStream);
    }

    private static List<String> readInputStream(final InputStream fileInputStream) throws IOException {

        final List<String> fileContents = new ArrayList<String>();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContents.add(line);
            }

        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return fileContents;
    }

}
