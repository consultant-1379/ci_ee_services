/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.file;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;

/**
 * @author eemecoy
 *
 */
public class FileOperationsTest extends BaseJMockUnitTest {

    private FileOperations fileOperations;

    File mockedFileObject;

    BufferedReader mockedBufferedReader;

    String urlPathToFile;

    @Before
    public void setup() {
        fileOperations = new StubbedFileOperations();
        mockedFileObject = mockery.mock(File.class);
        mockedBufferedReader = mockery.mock(BufferedReader.class);

    }

    @Test
    public void testNullPointerNotThrownIfNoFilesInFolder() {
        expectListOnFile(new String[] {});
        final String[] result = fileOperations.getFilesInFolder("aFolder");
        assertNotNull(result);
        assertThat(result.length, is(0));
    }

    @Test
    public void testGetPathToParentFolder() {
        final String folder = "/C:/cc/eemecoy_at_eniq/eniq_events/eniq_events_services/services-templates/target/classe/";
        urlPathToFile = folder + "/templateMap.csv";
        final String fileName = "aFileName";
        final String result = fileOperations.getPathToParentFolder(fileName);
        assertEquals(folder, result);
    }

    @Test
    public void testBlankLineInMiddleOfFileNotIncludedInResult() throws Exception {
        final String firstLine = "the first line";
        final String anEmptyLine = "";
        final String thirdLine = "the third line";
        final List<String> result = runTestOnReadingFiles(firstLine, anEmptyLine, thirdLine);
        assertThat(result.size(), is(2));
        assertThat(result.contains(firstLine), is(true));
        assertThat(result.contains(thirdLine), is(true));
    }

    @Test
    public void testBlankLineAtEndOfFileNotIncludedInResult() throws Exception {
        final String firstLine = "the first line";
        final String secondLine = "the second line";
        final String anEmptyLine = "";
        final List<String> result = runTestOnReadingFiles(firstLine, secondLine, anEmptyLine);
        assertThat(result.size(), is(2));
        assertThat(result.contains(firstLine), is(true));
        assertThat(result.contains(secondLine), is(true));
    }

    List<String> runTestOnReadingFiles(final String firstLine, final String secondLine, final String anEmptyLine)
            throws IOException {
        final String folderName = "aFolder";
        final String fileExtension = ".csv";
        final String fileName = "aFile" + fileExtension;
        final String[] filesInFolder = new String[] { fileName };
        expectListOnFile(filesInFolder);
        setupLinesInFile(new String[] { firstLine, secondLine, anEmptyLine });
        final List<String> result = fileOperations.readFiles(folderName, fileExtension);
        return result;
    }

    private void setupLinesInFile(final String[] linesInFile) throws IOException {
        for (final String line : linesInFile) {

            mockery.checking(new Expectations() {
                {
                    one(mockedBufferedReader).readLine();
                    will(returnValue(line));
                }
            });
        }
        mockery.checking(new Expectations() {
            {
                one(mockedBufferedReader).readLine();
                will(returnValue(null)); // to indicate we've reached the end of the file
                one(mockedBufferedReader).close();
            }
        });

    }

    private void expectListOnFile(final String[] filesInFolder) {
        mockery.checking(new Expectations() {
            {
                one(mockedFileObject).list();
                will(returnValue(filesInFolder));
            }
        });

    }

    class StubbedFileOperations extends FileOperations {

        /* (non-Javadoc)
        * @see com.ericsson.eniq.events.server.utils.file.FileOperations#getUrlPath(java.lang.String)
        */
        @Override
        String getUrlPath(final String fileName) {
            return urlPathToFile;
        }

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.utils.file.FileOperations#createFileObject(java.lang.String)
         */
        @Override
        File createFileObject(final String folderName) {
            return mockedFileObject;
        }

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.utils.file.FileOperations#createBufferedReader(java.io.InputStream)
         */
        @Override
        BufferedReader createBufferedReader(final InputStream fileInputStream) {
            return mockedBufferedReader;
        }
    }

}
