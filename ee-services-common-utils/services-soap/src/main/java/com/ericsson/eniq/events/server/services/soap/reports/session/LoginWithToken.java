package com.ericsson.eniq.events.server.services.soap.reports.session;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="loginToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="timeZone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReportedIPAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReportedHostName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "loginToken",
        "locale",
        "timeZone",
        "reportedIPAddress",
        "reportedHostName",
        "version"
})
@XmlRootElement(name = "loginWithToken")
public class LoginWithToken {

    @XmlElement(required = true)
    protected String loginToken;
    @XmlElement(required = true)
    protected String locale;
    @XmlElement(required = true)
    protected String timeZone;
    @XmlElement(name = "ReportedIPAddress", required = true)
    protected String reportedIPAddress;
    @XmlElement(name = "ReportedHostName", required = true)
    protected String reportedHostName;
    protected String version;

    /**
     * Gets the value of the loginToken property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * Sets the value of the loginToken property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLoginToken(String value) {
        this.loginToken = value;
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

    /**
     * Gets the value of the version property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
