/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.templates.mappingengine;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.common.exception.DuplicateTemplateKeyException;
import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.utils.file.FileOperations;

/**
 * @author eemecoy
 *
 */
public class TemplateMappingEngineMockedTest extends BaseJMockUnitTest {

    private TemplateMappingEngine templateMappingEngine;

    FileOperations mockedFileOperations;

    @Before
    public void setup() {
        templateMappingEngine = new TemplateMappingEngine();
        mockedFileOperations = mockery.mock(FileOperations.class);
        templateMappingEngine.setFileOperations(mockedFileOperations);

    }

    @Test
    public void testExceptionThrownForDuplicateKeys() throws Exception {
        final String pathName = "thePathName";
        final List<String> fileContents = new ArrayList<String>();
        final String templateKey = pathName + ",null,false,null,false,null";
        final String theDuplicateKey = templateKey + ",theTemplate.vm";
        fileContents.add(theDuplicateKey);
        fileContents.add(theDuplicateKey);
        setUpExpectationsOnFileOperations(fileContents);
        try {
            templateMappingEngine.applicationStartup();
            fail("Exception should have been thrown");
        } catch (final DuplicateTemplateKeyException e) {
            final String exceptionMessage = e.getMessage();
            final String expectedErrorMessage = "Duplicate key present for MultiKey[" + pathName;
            assertThat("Exception message " + exceptionMessage + " should have contained " + expectedErrorMessage,
                    exceptionMessage.contains(expectedErrorMessage), is(true));
        }

    }

    private void setUpExpectationsOnFileOperations(final List<String> fileContents) throws IOException {
        mockery.checking(new Expectations() {
            {
                one(mockedFileOperations).getPathToParentFolder(TemplateMappingEngine.CSV_FILE);
                final String pathToParentFolder = "theParentFolder";
                will(returnValue(pathToParentFolder));
                one(mockedFileOperations).readFiles(pathToParentFolder, TemplateMappingEngine.CSV_FILE_EXTENSION);
                will(returnValue(fileContents));
            }
        });

    }
}
