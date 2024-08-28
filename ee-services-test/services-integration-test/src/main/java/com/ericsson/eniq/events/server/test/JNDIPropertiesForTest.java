/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2010 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.test;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;
import static com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Ignore;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import com.ericsson.eniq.events.server.common.ApplicationConfigConstants;
import com.ericsson.eniq.events.server.datasource.DataSourceManager;

/**
 * Perform set up for DB Integration tests
 * This wires in the default eniq (dwh) data source into JNDI
 *
 * @author eemecoy
 *
 */
@Ignore
public class JNDIPropertiesForTest {

    @Resource
    private DataSource dwhDataSource;

    @Resource(name = ApplicationConfigConstants.ENIQ_EVENT_PROPERTIES)
    private Properties eniqEventsProperties;

    private final static Map<Object, Object> defaultMap = new HashMap<Object, Object>();

    static {
        defaultMap.put(DataSourceManager.DEFAULT_ENIQ_DATA_SOURCE_PROPERTY_NAME, "jdbc/eniqPool");
        defaultMap.put(DataSourceManager.EXPORT_CSV_ENIQ_DATA_SOURCE_PROPERTY_NAME, "jdbc/eniqPool");
        defaultMap.put(ENIQ_EVENTS_MAX_JSON_RESULT_SIZE, "50");
        defaultMap.put(ENIQ_EVENTS_DT_USE_TIME_DELAY, "false");
        defaultMap.put(ENIQ_EVENTS_DATA_TIERING, "false");
    }

    /**
     * called from DataServiceBaseTestCase-context.xml
     * @throws Exception
     */
    public void setUpJNDIPropertiesForTest() throws Exception {
        final SimpleNamingContextBuilder builder = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        final String defaultENIQDataSourceName = (String) eniqEventsProperties
                .get(DataSourceManager.DEFAULT_ENIQ_DATA_SOURCE_PROPERTY_NAME);
        builder.bind(defaultENIQDataSourceName, dwhDataSource);
        final Properties eniqEventProperties = new Properties();
        eniqEventProperties.putAll(defaultMap);
        builder.bind(ApplicationConfigConstants.ENIQ_EVENT_PROPERTIES, eniqEventProperties);
    }

    public void setDwhDataSource(final DataSource dwhDataSource) {
        this.dwhDataSource = dwhDataSource;
    }

    public void setEniqEventsProperties(final Properties eniqEventsProperties) {
        this.eniqEventsProperties = eniqEventsProperties;
    }

    public void setUpDataTieringJNDIProperty() throws Exception {
        final SimpleNamingContextBuilder context = SimpleNamingContextBuilder.emptyActivatedContextBuilder();
        final Properties eniqEventProperties = new Properties();
        eniqEventProperties.putAll(defaultMap);
        eniqEventProperties.put(ENIQ_EVENTS_DATA_TIERING, TRUE);
        eniqEventProperties.put(ENIQ_EVENTS_TIME_DELAY_TO_BE_USED, "0");
        context.bind(ApplicationConfigConstants.ENIQ_EVENT_PROPERTIES, eniqEventProperties);
    }
}
