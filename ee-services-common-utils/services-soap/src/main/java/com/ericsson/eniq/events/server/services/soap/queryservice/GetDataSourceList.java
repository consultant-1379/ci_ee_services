package com.ericsson.eniq.events.server.services.soap.queryservice;

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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "session"
})
@XmlRootElement(name = "getDataSourceList", namespace = "http://queryservice.dsws.businessobjects.com/2005")
public class GetDataSourceList {

    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected String session;

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

}
