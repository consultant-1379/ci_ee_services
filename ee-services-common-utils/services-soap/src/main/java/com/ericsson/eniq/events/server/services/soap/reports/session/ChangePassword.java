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
 *         &lt;element name="session" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="oldPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="newPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "session",
        "oldPassword",
        "newPassword"
})
@XmlRootElement(name = "changePassword")
public class ChangePassword {

    @XmlElement(required = true)
    protected String session;
    @XmlElement(required = true)
    protected String oldPassword;
    @XmlElement(required = true)
    protected String newPassword;

    /**
     * Gets the value of the session property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSession(String value) {
        this.session = value;
    }

    /**
     * Gets the value of the oldPassword property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * Sets the value of the oldPassword property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOldPassword(String value) {
        this.oldPassword = value;
    }

    /**
     * Gets the value of the newPassword property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the value of the newPassword property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNewPassword(String value) {
        this.newPassword = value;
    }

}
