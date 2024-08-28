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
 *         &lt;element name="SupportedPVLocales" type="{http://session.dsws.businessobjects.com/2007/06/01}SupportedPVLs"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "supportedPVLocales"
})
@XmlRootElement(name = "getSupportedPVLsResponse")
public class GetSupportedPVLsResponse {

    @XmlElement(name = "SupportedPVLocales", required = true)
    protected SupportedPVLs supportedPVLocales;

    /**
     * Gets the value of the supportedPVLocales property.
     *
     * @return possible object is
     *         {@link SupportedPVLs }
     */
    public SupportedPVLs getSupportedPVLocales() {
        return supportedPVLocales;
    }

    /**
     * Sets the value of the supportedPVLocales property.
     *
     * @param value allowed object is
     *              {@link SupportedPVLs }
     */
    public void setSupportedPVLocales(SupportedPVLs value) {
        this.supportedPVLocales = value;
    }

}
