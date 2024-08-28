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
 *         &lt;element name="sessionID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PVLocale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sessionID",
        "pvLocale"
})
@XmlRootElement(name = "setPVLocale")
public class SetPVLocale {

    @XmlElement(required = true)
    protected String sessionID;
    @XmlElement(name = "PVLocale", required = true)
    protected String pvLocale;

    /**
     * Gets the value of the sessionID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSessionID(String value) {
        this.sessionID = value;
    }

    /**
     * Gets the value of the pvLocale property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getPVLocale() {
        return pvLocale;
    }

    /**
     * Sets the value of the pvLocale property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPVLocale(String value) {
        this.pvLocale = value;
    }

}
