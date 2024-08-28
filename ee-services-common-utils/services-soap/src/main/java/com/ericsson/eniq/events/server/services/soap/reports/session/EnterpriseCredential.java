package com.ericsson.eniq.events.server.services.soap.reports.session;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for EnterpriseCredential complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="EnterpriseCredential">
 *   &lt;complexContent>
 *     &lt;extension base="{http://session.dsws.businessobjects.com/2007/06/01}Credential">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Login" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Password" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Locale" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Domain" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AuthType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ReportedIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ReportedHostName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnterpriseCredential")
public class EnterpriseCredential
        extends Credential {

    @XmlAttribute(name = "Login", required = true)
    protected String login;
    @XmlAttribute(name = "Password")
    protected String password;
    @XmlAttribute(name = "Locale")
    protected String locale;
    @XmlAttribute(name = "TimeZone")
    protected String timeZone;
    @XmlAttribute(name = "Domain")
    protected String domain;
    @XmlAttribute(name = "AuthType")
    protected String authType;
    @XmlAttribute(name = "ReportedIPAddress")
    protected String reportedIPAddress;
    @XmlAttribute(name = "ReportedHostName")
    protected String reportedHostName;

    /**
     * Gets the value of the login property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the locale property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLocale(String value) {
        this.locale = value;
    }

    /**
     * Gets the value of the timeZone property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTimeZone(String value) {
        this.timeZone = value;
    }

    /**
     * Gets the value of the domain property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets the value of the domain property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDomain(String value) {
        this.domain = value;
    }

    /**
     * Gets the value of the authType property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * Sets the value of the authType property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAuthType(String value) {
        this.authType = value;
    }

    /**
     * Gets the value of the reportedIPAddress property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getReportedIPAddress() {
        return reportedIPAddress;
    }

    /**
     * Sets the value of the reportedIPAddress property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReportedIPAddress(String value) {
        this.reportedIPAddress = value;
    }

    /**
     * Gets the value of the reportedHostName property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getReportedHostName() {
        return reportedHostName;
    }

    /**
     * Sets the value of the reportedHostName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setReportedHostName(String value) {
        this.reportedHostName = value;
    }

}
