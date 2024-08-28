/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.licensing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;

/**
 * @author eemecoy
 *
 */
public class LicensingPropertiesTest extends BaseJMockUnitTest {

    Properties propertiesInENIQFile;

    private Properties eniqEventsJNDIProperties;

    private final String expectedLicenseServerName = "licenceServer";

    @Before
    public void setUp() {
        eniqEventsJNDIProperties = new Properties();
        setUpENIQEventsProperty("ETLCSERVER_PROPERTIES_FILE_LOCATION", "someFolder/someFile");
        setUpENIQEventsProperty("ENIQ_EVENTS_LICENCESERVER", expectedLicenseServerName);
        propertiesInENIQFile = new Properties();
    }

    @Test
    public void testSettingRMIURLSettingTheLicensingServiceRMIName() throws IOException {
        final String licensingRMIServiceName = "theRMIName";
        setUpENIQEventsProperty("LICENSING_SERVICE_RMI_NAME", licensingRMIServiceName);
        final String licensingHostPort = "654";
        propertiesInENIQFile.setProperty("ENGINE_PORT", licensingHostPort);

        final LicensingProperties objToTest = new StubbedLicensingProperties(eniqEventsJNDIProperties);
        final String result = objToTest.determineURLOfLicensingService();

        final String expectedURL = "rmi://" + expectedLicenseServerName + ":" + licensingHostPort + "/"
                + licensingRMIServiceName;
        assertThat(result, is(expectedURL));
    }

    @Test
    public void testThatPortIsDefaultPortIfNotSetInEniqPropertiesFile() throws IOException {
        final LicensingProperties objToTest = new StubbedLicensingProperties(eniqEventsJNDIProperties);
        final String result = objToTest.determineURLOfLicensingService();
        final String defaultLicensingHostPort = "1200";
        final String expectedURL = "rmi://" + expectedLicenseServerName + ":" + defaultLicensingHostPort
                + "/LicensingCache";
        assertThat(result, is(expectedURL));
    }

    @Test
    public void testSettingRMIURLFromENIQPropertiesFile() throws IOException {

        final String licensingHostPort = "654";
        propertiesInENIQFile.setProperty("ENGINE_PORT", licensingHostPort);

        final LicensingProperties objToTest = new StubbedLicensingProperties(eniqEventsJNDIProperties);

        final String result = objToTest.determineURLOfLicensingService();
        final String expectedURL = "rmi://" + expectedLicenseServerName + ":" + licensingHostPort + "/LicensingCache";
        assertThat(result, is(expectedURL));
    }

    private void setUpENIQEventsProperty(final String propertyName, final String propertyValue) {
        eniqEventsJNDIProperties.setProperty(propertyName, propertyValue);

    }

    class StubbedLicensingProperties extends LicensingProperties {

        /**
         * @param eniqEventsPropertiesResource
         */
        public StubbedLicensingProperties(final Properties eniqEventsPropertiesResource) {
            super(eniqEventsPropertiesResource);
        }

        /* (non-Javadoc)
         * @see com.ericsson.eniq.events.server.licensing.LicensingProperties#loadPropertiesFromFile(java.lang.String)
         */
        @Override
        Properties loadPropertiesFromFile(final String locationOfENIQPropertiesFile) throws FileNotFoundException,
                IOException {
            return propertiesInENIQFile;
        }

    }
}
