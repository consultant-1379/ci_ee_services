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
 *         &lt;element name="credential" type="{http://session.dsws.businessobjects.com/2007/06/01}Credential"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "credential",
        "version"
})
@XmlRootElement(name = "login")
public class Login {

    @XmlElement(required = true)
    protected Credential credential;
    protected String version;

    /**
     * Gets the value of the credential property.
     *
     * @return possible object is
     *         {@link Credential }
     */
    public Credential getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     *
     * @param value allowed object is
     *              {@link Credential }
     */
    public void setCredential(Credential value) {
        this.credential = value;
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
