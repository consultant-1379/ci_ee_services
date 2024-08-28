/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2011
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/

package com.ericsson.eniq.events.server.serviceprovider.impl.dashboard.reports.client;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.ericsson.eniq.events.server.test.common.BaseJMockUnitTest;
import com.ericsson.eniq.events.server.utils.config.ApplicationConfigManager;


/**
 * Describe BICatalogObjectSoapClientTest here...
 *
 * @author ezhelao
 */
public class BICatalogObjectSoapClientTest extends BaseJMockUnitTest {

    private static final String INVALID_FORMAT_URL = "this is an invalid format url";

    private static final String INVALID_URL = "http://anyvalidurl.com:8080/";

    private static final String APPLICATION_CONFIGURE_CLASS_NAME = "applicationConfigManager";

    private static final String VALID_USER_NAME = "administrator";

    private static final String VALID_PASSWORD = "";

    ApplicationConfigManager mockedApplicationConfigManager;

    @Before
    public void setup() {
        mockedApplicationConfigManager = mockery.mock(ApplicationConfigManager.class);
        allowOtherCallsForBISConfigurationSettings();
    }

    private void allowOtherCallsForBISConfigurationSettings() {
        mockery.checking(new Expectations() {
            {
                atMost(1).of(mockedApplicationConfigManager).getBISServiceOpenDocCompleteURLWithFormatter();
                allowing(mockedApplicationConfigManager).getBISReportsRootObjectName();
            }
        });

    }

    /**
     * The log for this class.
     * @throws UnknownHostException 
     */

    @Test(expected = MalformedURLException.class)
    public void testInitConnectionURLMalformedException() throws Exception,
            UnknownHostException {
        expectCallForGetBISServiceURL(INVALID_FORMAT_URL);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.getDocumentFolderList();

    }

    private void expectCallForGetBISServiceURL(final String bisServiceURL) {
        mockery.checking(new Expectations() {
            {
                one(mockedApplicationConfigManager).getBISUsername();
                will(returnValue(VALID_USER_NAME));
                one(mockedApplicationConfigManager).getBISPassword();
                will(returnValue(VALID_PASSWORD));
                one(mockedApplicationConfigManager).getBISServiceURL();
                will(returnValue(bisServiceURL));
            }
        });

    }

    @Test(expected = Exception.class)
    public void testInitConnectionInaccessibleWSDLException() throws Exception
             {
        expectCallForGetBISServiceURL(INVALID_URL);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.getDocumentFolderList();

    }



    @Test(expected = MalformedURLException.class)
    public void getDocumentListMalformedURLException() throws Exception {
        expectCallForGetBISServiceURL(null);
        final BICatalogObjectSoapClient soapClient = new BICatalogObjectSoapClient();
        ReflectionTestUtils.setField(soapClient, APPLICATION_CONFIGURE_CLASS_NAME, mockedApplicationConfigManager);
        soapClient.postConstruct();
        soapClient.getDocumentFolderList();

    }

}
