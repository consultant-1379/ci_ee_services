package com.ericsson.eniq.events.server.utils.config;

import com.ericsson.eniq.events.server.utils.config.latency.LatencySettings;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ApplicationConfigManagerTest {

    protected Mockery mockery = new JUnit4Mockery();

    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    PropertyStore propertyStore;

    LatencySettings latencySettings;

    private ApplicationConfigManagerImpl configManager;

    @Before
    public void setup() {
        propertyStore = mockery.mock(PropertyStore.class);
        latencySettings = mockery.mock(LatencySettings.class);
        configManager = new ApplicationConfigManagerImpl();
        configManager.setPropertyStore(propertyStore);
        configManager.setLatencySettings(latencySettings);
    }

    @Test
    public void testGetOneMinuteAggregation() {
        final boolean expectedValue = true;
        setupBooleanPropertyInMockedPropertyStore(ENIQ_EVENTS_ONE_MINUTE_AGGREGATION, DEFAULT_ONE_MINUTE_AGGREGATION,
                expectedValue);
        assertThat(configManager.getOneMinuteAggregation(), is(expectedValue));
    }

    private void setupBooleanPropertyInMockedPropertyStore(final String key, final boolean defaultValue,
                                                           final boolean expectedValue) {
        mockery.checking(new Expectations() {
            {
                one(propertyStore).getBooleanPropertyValue(key, defaultValue);
                will(returnValue(expectedValue));
            }
        });

    }

    @Test
    public void test_JNDI_property_value_contents() {
        final Properties properties = createEniqEvents_JNDI_Properties();

        assertEquals(9, properties.size());
        assertEquals("1", properties.getProperty(ENIQ_EVENTS_APN_EVENT_FAILURE_RANKING_COUNT));
        assertEquals("2", properties.getProperty(ENIQ_EVENTS_BSC_EVENT_FAILURE_RANKING_COUNT));
        assertEquals("3", properties.getProperty(ENIQ_EVENTS_CELL_EVENT_FAILURE_RANKING_COUNT));
        assertEquals("4", properties.getProperty(ENIQ_EVENTS_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT));
        assertEquals("5", properties.getProperty(ENIQ_EVENTS_IMSI_EVENT_FAILURE_RANKING_COUNT));
        assertEquals("6", properties.getProperty(ENIQ_EVENTS_TERMINAL_EVENT_FAILURE_RANKING_COUNT));
        assertEquals("50", properties.getProperty(ENIQ_EVENTS_MAX_JSON_RESULT_SIZE));
        assertEquals("30", properties.getProperty(ENIQ_EVENTS_APN_RETENTION_IN_DAYS));

    }

    @Test
    public void test_get_property_values_from_JNDI_context() {

        final Properties eniqEventsJndiProperties = createEniqEvents_JNDI_Properties();

        setUpExpectationsOnPropertyStoreToReturnSpecifiedValues(eniqEventsJndiProperties);

        assertEquals(1, configManager.getApnEventFailureRankingCount());
        assertEquals(2, configManager.getBscEventFailureRankingCount());
        assertEquals(3, configManager.getCellEventFailureRankingCount());
        assertEquals(4, configManager.getCauseCodeEventFailureRankingCount());
        assertEquals(5, configManager.getImsiEventFailureRankingCount());
        assertEquals(6, configManager.getTerminalEventFailureRankingCount());
        assertEquals(50, configManager.getMaxJsonResultSize());
        assertEquals(30, configManager.getAPNRetention());

    }

    private void setUpExpectationsOnPropertyStoreToReturnSpecifiedValues(final Properties eniqEventsJndiProperties) {
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_MAX_JSON_RESULT_SIZE, DEFAULT_MAXIMUM_JSON_RESULT_SIZE,
                MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE,
                Integer.parseInt(eniqEventsJndiProperties.getProperty(ENIQ_EVENTS_MAX_JSON_RESULT_SIZE)));
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_APN_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_APN_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                Integer.parseInt(eniqEventsJndiProperties.getProperty(ENIQ_EVENTS_APN_EVENT_FAILURE_RANKING_COUNT)));
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_BSC_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_BSC_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                Integer.parseInt(eniqEventsJndiProperties.getProperty(ENIQ_EVENTS_BSC_EVENT_FAILURE_RANKING_COUNT)));
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_CELL_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_CELL_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                Integer.parseInt(eniqEventsJndiProperties.getProperty(ENIQ_EVENTS_CELL_EVENT_FAILURE_RANKING_COUNT)));
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                Integer.parseInt(eniqEventsJndiProperties
                        .getProperty(ENIQ_EVENTS_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT)));
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_IMSI_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_IMSI_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                Integer.parseInt(eniqEventsJndiProperties.getProperty(ENIQ_EVENTS_IMSI_EVENT_FAILURE_RANKING_COUNT)));
        setUpIntegerPropertyInMockedPropertyStore(
                ENIQ_EVENTS_TERMINAL_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_TERMINAL_EVENT_FAILURE_RANKING_COUNT,
                MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                Integer.parseInt(eniqEventsJndiProperties.getProperty(ENIQ_EVENTS_TERMINAL_EVENT_FAILURE_RANKING_COUNT)));
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_APN_RETENTION_IN_DAYS, DEFAULT_APN_RETENTION_IN_DAYS,
                MAXIMUM_POSSIBLE_APN_RETENTION_IN_DAYS,
                Integer.parseInt(eniqEventsJndiProperties.getProperty(ENIQ_EVENTS_APN_RETENTION_IN_DAYS)));

    }

    private void setUpExpectationsOnPropertyStoreToReturnDefaultValues() {
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_MAX_JSON_RESULT_SIZE, DEFAULT_MAXIMUM_JSON_RESULT_SIZE,
                MAXIMUM_POSSIBLE_CONFIGURABLE_MAX_JSON_RESULT_SIZE, DEFAULT_MAXIMUM_JSON_RESULT_SIZE);
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_APN_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_APN_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                DEFAULT_APN_EVENT_FAILURE_RANKING_COUNT);
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_BSC_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_BSC_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                DEFAULT_BSC_EVENT_FAILURE_RANKING_COUNT);
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_CELL_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_CELL_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                DEFAULT_CELL_EVENT_FAILURE_RANKING_COUNT);
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                DEFAULT_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT);
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_IMSI_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_IMSI_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                DEFAULT_IMSI_EVENT_FAILURE_RANKING_COUNT);
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_TERMINAL_EVENT_FAILURE_RANKING_COUNT,
                DEFAULT_TERMINAL_EVENT_FAILURE_RANKING_COUNT, MAXIMUM_POSSIBLE_GRID_DATA_ROW_COUNTS,
                DEFAULT_TERMINAL_EVENT_FAILURE_RANKING_COUNT);
        setUpIntegerPropertyInMockedPropertyStore(ENIQ_EVENTS_APN_RETENTION_IN_DAYS, DEFAULT_APN_RETENTION_IN_DAYS,
                MAXIMUM_POSSIBLE_APN_RETENTION_IN_DAYS, DEFAULT_APN_RETENTION_IN_DAYS);

    }

    private void setUpIntegerPropertyInMockedPropertyStore(final String key, final int defaultValue,
                                                           final int maximumConfigurableSize, final int propertyValue) {
        final Property expectedProperty = new Property(key);
        expectedProperty.setDefaultValue(defaultValue);
        expectedProperty.setMaxConfigurableValue(maximumConfigurableSize);
        mockery.checking(new Expectations() {
            {
                allowing(propertyStore)
                        .getIntegerPropertyValue(with(PropertyMatcher.propertyMatches(expectedProperty)));
                will(returnValue(propertyValue));
            }
        });

    }

    @Test
    public void test_get_default_property_values_from_JNDI_context() {

        final Properties p = new Properties();

        assertTrue(p.isEmpty());

        setUpExpectationsOnPropertyStoreToReturnDefaultValues();

        assertEquals(DEFAULT_APN_EVENT_FAILURE_RANKING_COUNT, configManager.getApnEventFailureRankingCount());
        assertEquals(DEFAULT_BSC_EVENT_FAILURE_RANKING_COUNT, configManager.getBscEventFailureRankingCount());
        assertEquals(DEFAULT_CELL_EVENT_FAILURE_RANKING_COUNT, configManager.getCellEventFailureRankingCount());
        assertEquals(DEFAULT_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT,
                configManager.getCauseCodeEventFailureRankingCount());
        assertEquals(DEFAULT_IMSI_EVENT_FAILURE_RANKING_COUNT, configManager.getImsiEventFailureRankingCount());
        assertEquals(DEFAULT_TERMINAL_EVENT_FAILURE_RANKING_COUNT, configManager.getTerminalEventFailureRankingCount());
        assertEquals(DEFAULT_MAXIMUM_JSON_RESULT_SIZE, configManager.getMaxJsonResultSize());
        assertEquals(DEFAULT_APN_RETENTION_IN_DAYS, configManager.getAPNRetention());

    }

    @Test
    public void testGetDefaultTimeDelayProperties() {

        final int expectedOneMinuteDataDelay = 3;
        final int expectedFifteenMinuteDataDelay = 15;
        final int expectedDayDataDelay = 24;

        mockery.checking(new Expectations() {
            {
                one(latencySettings).getTimeDelayLeastOffset(null);
                will(returnValue(expectedOneMinuteDataDelay));
                one(latencySettings).getTimeDelayMediumOffset(null);
                will(returnValue(expectedFifteenMinuteDataDelay));
                one(latencySettings).getTimeDelayLargeOffset(null);
                will(returnValue(expectedDayDataDelay));
            }
        });

        assertEquals(expectedOneMinuteDataDelay, configManager.getTimeDelayOneMinuteData(null));
        assertEquals(expectedFifteenMinuteDataDelay, configManager.getTimeDelayFifteenMinuteData(null));
        assertEquals(expectedDayDataDelay, configManager.getTimeDelayDayData(null));
    }

    private Properties createEniqEvents_JNDI_Properties() {
        final Properties p = new Properties();
        p.setProperty(ENIQ_EVENTS_MAX_JSON_RESULT_SIZE, "50");
        p.setProperty(ENIQ_EVENTS_APN_EVENT_FAILURE_RANKING_COUNT, "1");
        p.setProperty(ENIQ_EVENTS_BSC_EVENT_FAILURE_RANKING_COUNT, "2");
        p.setProperty(ENIQ_EVENTS_CELL_EVENT_FAILURE_RANKING_COUNT, "3");
        p.setProperty(ENIQ_EVENTS_CAUSE_CODE_EVENT_FAILURE_RANKING_COUNT, "4");
        p.setProperty(ENIQ_EVENTS_IMSI_EVENT_FAILURE_RANKING_COUNT, "5");
        p.setProperty(ENIQ_EVENTS_TERMINAL_EVENT_FAILURE_RANKING_COUNT, "6");
        p.setProperty(ENIQ_EVENTS_APN_RETENTION_IN_DAYS, "30");
        p.setProperty(ENIQ_EVENTS_DT_USE_TIME_DELAY, "false");
        return p;
    }

}
