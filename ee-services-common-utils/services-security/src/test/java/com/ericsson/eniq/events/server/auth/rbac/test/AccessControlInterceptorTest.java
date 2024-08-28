package com.ericsson.eniq.events.server.auth.rbac.test;

import static org.junit.Assert.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.interceptor.InvocationContext;
import javax.security.auth.Subject;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ericsson.eniq.events.server.auth.rbac.AccessControlInterceptor;
import com.ericsson.eniq.events.server.common.exception.ServiceException;
import com.sun.enterprise.security.auth.login.common.PasswordCredential;

/**
 *
 * @author eflatib
 * @author eravjee
 *
 */
@RunWith(JMock.class)
public class AccessControlInterceptorTest {

    private final PasswordCredential dummyPasswordCredential = new PasswordCredential("dummyUser",
            "dummyPassword".toCharArray(), "dummyRealm");

    private final Set<Object> dummyCredentialSet = new HashSet<Object>();

    private final Set<PasswordCredential> dummyPasswordCredentialSet = new HashSet<PasswordCredential>();

    private Subject dummySubject;

    private Mockery context;

    private InvocationContext mockedContext;

    private final StubbedAccessControlInterceptor interceptor = new StubbedAccessControlInterceptor();

    @Before
    public void setUp() {
        context = new JUnit4Mockery();
        context.setImposteriser(ClassImposteriser.INSTANCE);

        mockedContext = context.mock(InvocationContext.class);
    }

    /**
     * What happens when Subject is null ?
     *
     * @throws Exception
     */
    @Test
    public void testCheckPermissionsWhenSubjectIsNull() throws Exception {
        /*
                final String dummyMetaDataNoTabs = "{ \"dummy\": [ {\"aa\": \"11\", \"bb\": \"22\"} ] }";
                dummySubject = new Subject(false, new HashSet<Principal>(), dummyPasswordCredentialSet,
                        dummyPasswordCredentialSet);
        */
        final String expectedMessage = "subject is null";

        dummyPasswordCredentialSet.add(dummyPasswordCredential);
        dummySubject = null;

        context.checking(new Expectations() {
            {
                allowing(mockedContext).proceed();
                will(returnValue(null));
            }
        });
        try {
            interceptor.checkPermissions(mockedContext);
            fail("Expected ServiceException, but NO EXCEPTION THROWN.");
        } catch (final ServiceException se) {

            assertTrue(
                    "Expected ServiceException with Message: \"" + expectedMessage + "\", but got \"" + se.getMessage()
                            + "\" instead.", expectedMessage.equals(se.getMessage())); // checks if the exception message is correct

        } catch (final Exception e) {
            fail("Expected ServiceException, but got " + e + " instead.");
        }
    }

    /**
     * What happens when Credentials are not found in Subject ?
     *
     * @throws Exception
     */
    @Test
    public void testCheckPermissionsWhenCredentialsNotFoundInSubject() throws Exception {

        final String expectedMessage = "passwordCredential not found";

        dummyPasswordCredentialSet.add(dummyPasswordCredential);
        final Set<Principal> principalSet = new HashSet<Principal>();
        dummySubject = new Subject(false, principalSet, dummyCredentialSet, dummyCredentialSet);

        context.checking(new Expectations() {
            {
                allowing(mockedContext).proceed();
                will(returnValue(null));
            }
        });
        try {
            interceptor.checkPermissions(mockedContext);
            fail("Expected ServiceException, but NO EXCEPTION THROWN.");
        } catch (final ServiceException se) {

            assertTrue(
                    "Expected ServiceException with Message: \"" + expectedMessage + "\", but got \"" + se.getMessage()
                            + "\" instead.", expectedMessage.equals(se.getMessage())); // checks if the exception message is correct

        } catch (final Exception e) {
            fail("Expected ServiceException, but got " + e + " instead.");
        }
    }

    /**
     * What happens when the metadata contains no "tabs" field to process
     * In this case the AccessControlInterceptor should return an empty String.
     * This scenario occurs when parsing UIMetaData_MSS.json, which does not contain this field.
     *
     * @throws Exception
     */
    @Test
    public void testCheckPermissionsWhenNoTabsFieldInMetaData() throws Exception {

        final String dummyMetaDataNoTabs = "{ \"dummy\": [ {\"aa\": \"11\", \"bb\": \"22\"} ] }";

        dummyPasswordCredentialSet.add(dummyPasswordCredential);
        final Set<Principal> principalSet = new HashSet<Principal>();
        dummySubject = new Subject(false, principalSet, dummyPasswordCredentialSet, dummyPasswordCredentialSet);

        String shouldBeEmpty;

        context.checking(new Expectations() {
            {

                allowing(mockedContext).proceed();
                will(returnValue(dummyMetaDataNoTabs));
            }
        });

        shouldBeEmpty = interceptor.checkPermissions(mockedContext).toString();

        assertTrue("The string should have been empty, instead we got \"" + shouldBeEmpty + "\"",
                shouldBeEmpty.equals(""));
    }

    /**
     * What happens when the metadata contains tabs and none of them are permitted ?
     * Then we should return the metadata without adding any "isRoleEnabled" fields.
     *
     * @throws Exception
     */
    @Test
    public void testCheckPermissionsWhenNoPermittedTabsInMetaData() throws Exception {

        final String dummyMetaData = "{\"tabs\": [ {   \"dummy_tab_1\": \"11\",  \"dummy_tab_2\": \"22\", } ]}";

        dummyPasswordCredentialSet.add(dummyPasswordCredential);
        final Set<Principal> principalSet = new HashSet<Principal>();
        dummySubject = new Subject(false, principalSet, dummyPasswordCredentialSet, dummyPasswordCredentialSet);

        String result;

        context.checking(new Expectations() {
            {
                allowing(mockedContext).proceed();
                will(returnValue(dummyMetaData));
            }
        });
        //        interceptor.setLoginContext(mockedLoginContext);

        result = interceptor.checkPermissions(mockedContext).toString();

        assertFalse("Returned string should NOT have been empty!", result.length() == 0);
        assertTrue("Returned string SHOULD have contained tabs field!", result.contains("tabs"));
        assertFalse("Returned string should NOT have contained isRoleEnabled field", result.contains("isRoleEnabled"));
    }

    /**
     * What happens when the metadata contains tabs and some of them are permitted ?
     * Then we should add "isRoleEnabled" field to the permitted tab in metadata and set its value to "TRUE".
     *
     * @throws Exception
     */
    @Test
    public void testCheckPermissionsWhenPermittedTabsFieldInMetaData() throws Exception {

        final String dummyMetaData = "{\"tabs\": [ { \"id\": \"dummy_tab_1\", \"aa\": \"11\"}, {\"id\": \"dummy_tab_2\",\"bb\": \"22\" }, { \"id\": \"dummy_tab_3\", \"cc\":  \"22\"} ]}";

        dummyPasswordCredentialSet.add(dummyPasswordCredential);
        final Set<Principal> principalSet = new HashSet<Principal>();
        dummySubject = new Subject(false, principalSet, dummyPasswordCredentialSet, dummyPasswordCredentialSet);

        String result;

        context.checking(new Expectations() {
            {
                allowing(mockedContext).proceed();
                will(returnValue(dummyMetaData));
            }
        });
        //        interceptor.setLoginContext(mockedLoginContext);

        result = interceptor.checkPermissions(mockedContext).toString();

        assertFalse("Returned string should NOT have been empty!", result.length() == 0);
        assertTrue("Returned string SHOULD have contained tabs field!", result.contains("tabs"));
        assertTrue("Returned string SHOULD have contained isRoleEnabled field", result.contains("isRoleEnabled"));
    }

    /*
     *
     * Internal stuff
     *
     */
    private class StubbedAccessControlInterceptor extends AccessControlInterceptor {

        @Override
        protected Set<String> getUserPermissions(final String a, final String b) {
            final Set<String> dummyUserPermissions = new HashSet<String>();
            dummyUserPermissions.add("dummy_tab_3");
            return dummyUserPermissions;
        }

        @Override
        protected Subject getSubjectFromContainer() {
            return dummySubject;
        }
    }
}
