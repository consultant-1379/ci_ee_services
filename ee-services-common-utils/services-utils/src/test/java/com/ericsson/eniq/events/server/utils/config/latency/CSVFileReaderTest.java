/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2011 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.config.latency;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

/**
 * @author eemecoy
 */
public class CSVFileReaderTest {

    protected Mockery mockery = new JUnit4Mockery();

    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    private CSVFileReader csvFileReader;

    BufferedReader mockedBufferedReader;

    InputStream inputStream;

    @Before
    public void setup() {
        inputStream = null;
        csvFileReader = new StubbedCSVFileReader();
        mockedBufferedReader = mockery.mock(BufferedReader.class);
    }

    @Test
    public void testNothingHappensWhenInputStreamIsNull() {
        inputStream = null;
        csvFileReader.readCSVFile(null);
    }

    @Test
    public void testReadingCSVFile() throws Exception {
        inputStream = mockery.mock(InputStream.class);
        expectClose(inputStream);
        final String csvFileName = "a_csv_file.csv";
        final String firstProperty = "chanelBag";
        final String defaultValueForFirstProperty = "3";
        final String maxConfigurableValueForFirstProperty = "5";
        final String firstLine = firstProperty + "," + defaultValueForFirstProperty + ","
                + maxConfigurableValueForFirstProperty;
        final String secondProperty = "gucciBag";
        final String defaultValueForSecondProperty = "4";
        final String maxConfigurableValueForSecondProperty = "7";
        final String secondLine = secondProperty + "," + defaultValueForSecondProperty + ","
                + maxConfigurableValueForSecondProperty;
        setUpTheMockedReader(firstLine, secondLine);
        final Map<String, List<String>> result = csvFileReader.readCSVFile(csvFileName);
        assertThat(result.size(), is(2));

        assertThat(result.containsKey(firstProperty), is(true));
        final List<String> propertiesForFirstValue = result.get(firstProperty);
        assertThat(propertiesForFirstValue.size(), is(3));
        assertThat(propertiesForFirstValue.get(0), is(firstProperty));
        assertThat(propertiesForFirstValue.get(1), is(defaultValueForFirstProperty));
        assertThat(propertiesForFirstValue.get(2), is(maxConfigurableValueForFirstProperty));

        assertThat(result.containsKey(secondProperty), is(true));
        final List<String> propertiesForSecondValue = result.get(secondProperty);
        assertThat(propertiesForSecondValue.size(), is(3));
        assertThat(propertiesForSecondValue.get(0), is(secondProperty));
        assertThat(propertiesForSecondValue.get(1), is(defaultValueForSecondProperty));
        assertThat(propertiesForSecondValue.get(2), is(maxConfigurableValueForSecondProperty));
    }

    private void expectClose(final Closeable closeable) throws IOException {
        mockery.checking(new Expectations() {
            {
                one(closeable).close();
            }
        });

    }

    private void setUpTheMockedReader(final String firstLine, final String secondLine) throws IOException {
        final String commentLine = "#some comment";
        mockery.checking(new Expectations() {
            {
                one(mockedBufferedReader).readLine();
                will(returnValue(commentLine));
                one(mockedBufferedReader).readLine();
                will(returnValue(firstLine));
                one(mockedBufferedReader).readLine();
                will(returnValue(secondLine));
                one(mockedBufferedReader).readLine();
                will(returnValue(null));
                one(mockedBufferedReader).close();
            }
        });

    }

    class StubbedCSVFileReader extends CSVFileReader {
        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.config.latency.CSVFileReader#createInputStreamReader(java.io.InputStream)
         */
        @Override
        InputStreamReader createInputStreamReader(final InputStream inputStream1) {
            return null;
        }

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.config.latency.CSVFileReader#createBufferedReader(java.io.InputStreamReader)
         */
        @Override
        BufferedReader createBufferedReader(final InputStreamReader inputStreamReader) {
            return mockedBufferedReader;
        }

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.config.latency.CSVFileReader#getResourceAsStream(java.lang.String)
         */
        @Override
        InputStream getResourceAsStream(final String csvFileName) {
            return inputStream;
        }
    }

}
