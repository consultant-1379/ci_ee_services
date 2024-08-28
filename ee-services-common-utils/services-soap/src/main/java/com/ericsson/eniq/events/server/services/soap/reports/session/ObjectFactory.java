package com.ericsson.eniq.events.server.services.soap.reports.session;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.ericsson.eniq.events.server.services.soap.reports.session package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DSWSException_QNAME = new QName("http://dsws.businessobjects.com/2007/06/01", "DSWSException");
    private final static QName _NameValuePair_QNAME = new QName("http://dsws.businessobjects.com/2007/06/01", "NameValuePair");
    private final static QName _SessionInfoLoginInfo_QNAME = new QName("http://session.dsws.businessobjects.com/2007/06/01", "LoginInfo");
    private final static QName _WSResourceDescription_QNAME = new QName("http://session.dsws.businessobjects.com/2007/06/01", "Description");
    private final static QName _DSWSExceptionCauseDetail_QNAME = new QName("", "CauseDetail");
    private final static QName _DSWSExceptionCauseMessage_QNAME = new QName("", "CauseMessage");
    private final static QName _DSWSExceptionCauseException_QNAME = new QName("", "CauseException");
    private final static QName _DSWSExceptionCallStackTrace_QNAME = new QName("", "CallStackTrace");
    private final static QName _DSWSExceptionCauseID_QNAME = new QName("", "CauseID");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ericsson.eniq.events.server.services.soap.reports.session
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSessionInfo }
     */
    public GetSessionInfo createGetSessionInfo() {
        return new GetSessionInfo();
    }

    /**
     * Create an instance of {@link GetVersionResponse }
     */
    public GetVersionResponse createGetVersionResponse() {
        return new GetVersionResponse();
    }

    /**
     * Create an instance of {@link GetSessionInfoResponse }
     */
    public GetSessionInfoResponse createGetSessionInfoResponse() {
        return new GetSessionInfoResponse();
    }

    /**
     * Create an instance of {@link ServerInfo }
     */
    public ServerInfo createServerInfo() {
        return new ServerInfo();
    }

    /**
     * Create an instance of {@link ResetSessionTimeOut }
     */
    public ResetSessionTimeOut createResetSessionTimeOut() {
        return new ResetSessionTimeOut();
    }

    /**
     * Create an instance of {@link ServerInfoDetails }
     */
    public ServerInfoDetails createServerInfoDetails() {
        return new ServerInfoDetails();
    }

    /**
     * Create an instance of {@link GetDictionaryResponse }
     */
    public GetDictionaryResponse createGetDictionaryResponse() {
        return new GetDictionaryResponse();
    }

    /**
     * Create an instance of {@link WSResource }
     */
    public WSResource createWSResource() {
        return new WSResource();
    }

    /**
     * Create an instance of {@link SetPVLocaleResponse }
     */
    public SetPVLocaleResponse createSetPVLocaleResponse() {
        return new SetPVLocaleResponse();
    }

    /**
     * Create an instance of {@link LoginWithToken }
     */
    public LoginWithToken createLoginWithToken() {
        return new LoginWithToken();
    }

    /**
     * Create an instance of {@link NameValuePair }
     */
    public NameValuePair createNameValuePair() {
        return new NameValuePair();
    }

    /**
     * Create an instance of {@link SetDictionary }
     */
    public SetDictionary createSetDictionary() {
        return new SetDictionary();
    }

    /**
     * Create an instance of {@link LoginWithTokenResponse }
     */
    public LoginWithTokenResponse createLoginWithTokenResponse() {
        return new LoginWithTokenResponse();
    }

    /**
     * Create an instance of {@link SupportedPVLs }
     */
    public SupportedPVLs createSupportedPVLs() {
        return new SupportedPVLs();
    }

    /**
     * Create an instance of {@link GetServerInfoDetails }
     */
    public GetServerInfoDetails createGetServerInfoDetails() {
        return new GetServerInfoDetails();
    }

    /**
     * Create an instance of {@link ResetSessionTimeOutResponse }
     */
    public ResetSessionTimeOutResponse createResetSessionTimeOutResponse() {
        return new ResetSessionTimeOutResponse();
    }

    /**
     * Create an instance of {@link EnterpriseCredential }
     */
    public EnterpriseCredential createEnterpriseCredential() {
        return new EnterpriseCredential();
    }

    /**
     * Create an instance of {@link GetSupportedPVLsResponse }
     */
    public GetSupportedPVLsResponse createGetSupportedPVLsResponse() {
        return new GetSupportedPVLsResponse();
    }

    /**
     * Create an instance of {@link Entry }
     */
    public Entry createEntry() {
        return new Entry();
    }

    /**
     * Create an instance of {@link GetVersion }
     */
    public GetVersion createGetVersion() {
        return new GetVersion();
    }

    /**
     * Create an instance of {@link Logout }
     */
    public Logout createLogout() {
        return new Logout();
    }

    /**
     * Create an instance of {@link DSWSException }
     */
    public DSWSException createDSWSException() {
        return new DSWSException();
    }

    /**
     * Create an instance of {@link LoginWithSerializedSession }
     */
    public LoginWithSerializedSession createLoginWithSerializedSession() {
        return new LoginWithSerializedSession();
    }

    /**
     * Create an instance of {@link GetDictionary }
     */
    public GetDictionary createGetDictionary() {
        return new GetDictionary();
    }

    /**
     * Create an instance of {@link GetSupportedPVLs }
     */
    public GetSupportedPVLs createGetSupportedPVLs() {
        return new GetSupportedPVLs();
    }

    /**
     * Create an instance of {@link ChangePasswordResponse }
     */
    public ChangePasswordResponse createChangePasswordResponse() {
        return new ChangePasswordResponse();
    }

    /**
     * Create an instance of {@link LogoutResponse }
     */
    public LogoutResponse createLogoutResponse() {
        return new LogoutResponse();
    }

    /**
     * Create an instance of {@link GetServerInfo }
     */
    public GetServerInfo createGetServerInfo() {
        return new GetServerInfo();
    }

    /**
     * Create an instance of {@link UserRight }
     */
    public UserRight createUserRight() {
        return new UserRight();
    }

    /**
     * Create an instance of {@link GetServerInfoDetailsResponse }
     */
    public GetServerInfoDetailsResponse createGetServerInfoDetailsResponse() {
        return new GetServerInfoDetailsResponse();
    }

    /**
     * Create an instance of {@link SetDictionaryResponse }
     */
    public SetDictionaryResponse createSetDictionaryResponse() {
        return new SetDictionaryResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link GetServerInfoResponse }
     */
    public GetServerInfoResponse createGetServerInfoResponse() {
        return new GetServerInfoResponse();
    }

    /**
     * Create an instance of {@link SetPVLocale }
     */
    public SetPVLocale createSetPVLocale() {
        return new SetPVLocale();
    }

    /**
     * Create an instance of {@link Login }
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link LoginWithSerializedSessionResponse }
     */
    public LoginWithSerializedSessionResponse createLoginWithSerializedSessionResponse() {
        return new LoginWithSerializedSessionResponse();
    }

    /**
     * Create an instance of {@link SessionInfo }
     */
    public SessionInfo createSessionInfo() {
        return new SessionInfo();
    }

    /**
     * Create an instance of {@link ChangePassword }
     */
    public ChangePassword createChangePassword() {
        return new ChangePassword();
    }

    /**
     * Create an instance of {@link Credential }
     */
    public Credential createCredential() {
        return new Credential();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DSWSException }{@code >}}
     */
    @XmlElementDecl(namespace = "http://dsws.businessobjects.com/2007/06/01", name = "DSWSException")
    public JAXBElement<DSWSException> createDSWSException(DSWSException value) {
        return new JAXBElement<DSWSException>(_DSWSException_QNAME, DSWSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NameValuePair }{@code >}}
     */
    @XmlElementDecl(namespace = "http://dsws.businessobjects.com/2007/06/01", name = "NameValuePair")
    public JAXBElement<NameValuePair> createNameValuePair(NameValuePair value) {
        return new JAXBElement<NameValuePair>(_NameValuePair_QNAME, NameValuePair.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://session.dsws.businessobjects.com/2007/06/01", name = "LoginInfo", scope = SessionInfo.class)
    public JAXBElement<String> createSessionInfoLoginInfo(String value) {
        return new JAXBElement<String>(_SessionInfoLoginInfo_QNAME, String.class, SessionInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://session.dsws.businessobjects.com/2007/06/01", name = "Description", scope = WSResource.class)
    public JAXBElement<String> createWSResourceDescription(String value) {
        return new JAXBElement<String>(_WSResourceDescription_QNAME, String.class, WSResource.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseDetail", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseDetail(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseDetail_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseMessage", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseMessage(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseMessage_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseException", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseException(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseException_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CallStackTrace", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCallStackTrace(String value) {
        return new JAXBElement<String>(_DSWSExceptionCallStackTrace_QNAME, String.class, DSWSException.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "CauseID", scope = DSWSException.class)
    public JAXBElement<String> createDSWSExceptionCauseID(String value) {
        return new JAXBElement<String>(_DSWSExceptionCauseID_QNAME, String.class, DSWSException.class, value);
    }

}
