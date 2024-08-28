/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.licensing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Responsible for reading up properties for accessing the licensing service
 * This reads up the eniq events properties from the JDNI resource (this is declared in the calling class, 
 * LicensingServiceBean).  These properties include the name of the ENIQ properties file, which contains the 
 * port to contact the licensing service on.
 * The eniq events properties also contains the host of the Licensing RMI Service
 * The eniq events properties also contains the name of the Licensing RMI Service
 * 
 * @author eemecoy
 *
 */
public class LicensingProperties {

    private final Properties eniqEventsProperties;

    private static final String DEFAULT_LICENSING_RMI_SERVICE_NAME = "LicensingCache";

    private static final String ENGINE_PORT_PROPERTY_NAME = "ENGINE_PORT";

    private static final String ETLCSERVER_PROPERTIES_FILE_LOCATION_PROPERTY_NAME = "ETLCSERVER_PROPERTIES_FILE_LOCATION";

    private static final String DEFAULT_LICENSING_PORT = "1200";

    private static final String DEFAULT_ETLCSERVER_PROPERTIES_FILE_LOCATION = "/eniq/sw/conf/ETLCServer.properties";

    /**
     * 
     * @param eniqEventsPropertiesResource      the eniq events properties, as stored in the JDNI resource in Glassfish
     */
    public LicensingProperties(final Properties eniqEventsPropertiesResource) {
        eniqEventsProperties = eniqEventsPropertiesResource;
    }

    /**
     * Based on the various properties and property files, put together the URL
     * for the ENIQ RMI Licensing Service
     * 
     * Result will be something like:
     * rmi://licenceserver:1200/LicensingCache
     * 
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    String determineURLOfLicensingService() throws FileNotFoundException, IOException {
        final String locationOfENIQPropertiesFile = eniqEventsProperties.getProperty(
                ETLCSERVER_PROPERTIES_FILE_LOCATION_PROPERTY_NAME, DEFAULT_ETLCSERVER_PROPERTIES_FILE_LOCATION);
        final Properties propertiesInENIQPropertiesFile = loadPropertiesFromFile(locationOfENIQPropertiesFile);

        final String licensingHost = eniqEventsProperties.getProperty("ENIQ_EVENTS_LICENCESERVER");
        final String licensingPort = determineLicensingPort(propertiesInENIQPropertiesFile);

        final String licensingServiceRMIName = eniqEventsProperties.getProperty("LICENSING_SERVICE_RMI_NAME",
                DEFAULT_LICENSING_RMI_SERVICE_NAME);
        return "rmi://" + licensingHost + ":" + licensingPort + "/" + licensingServiceRMIName;
    }

    /**
     * Determine the value for the licensing port - check the ENIQ properties file for the property
     * ENGINE_PORT.  If this doesn't exist, use the default
     * 
     * @param propertiesInENIQPropertiesFile
     * @return
     */
    private String determineLicensingPort(final Properties propertiesInENIQPropertiesFile) {
        String licensingPort = propertiesInENIQPropertiesFile.getProperty(ENGINE_PORT_PROPERTY_NAME);
        if (licensingPort == null) {
            licensingPort = DEFAULT_LICENSING_PORT;
        }
        return licensingPort;
    }

    /**
     *  extracted out to get under unit test
     * @param locationOfENIQPropertiesFile
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    Properties loadPropertiesFromFile(final String locationOfENIQPropertiesFile) throws FileNotFoundException,
            IOException {
        final FileInputStream fileInputStream = new FileInputStream(locationOfENIQPropertiesFile);
        final Properties propertiesInENIQPropertiesFile = new Properties();
        propertiesInENIQPropertiesFile.load(fileInputStream);
        return propertiesInENIQPropertiesFile;
    }

}
