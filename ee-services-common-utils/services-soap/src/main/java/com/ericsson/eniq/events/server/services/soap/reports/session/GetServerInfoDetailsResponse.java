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
 *         &lt;element name="ServerInfoDetails" type="{http://session.dsws.businessobjects.com/2007/06/01}ServerInfoDetails"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "serverInfoDetails"
})
@XmlRootElement(name = "getServerInfoDetailsResponse")
public class GetServerInfoDetailsResponse {

    @XmlElement(name = "ServerInfoDetails", required = true)
    protected ServerInfoDetails serverInfoDetails;

    /**
     * Gets the value of the serverInfoDetails property.
     *
     * @return possible object is
     *         {@link ServerInfoDetails }
     */
    public ServerInfoDetails getServerInfoDetails() {
        return serverInfoDetails;
    }

    /**
     * Sets the value of the serverInfoDetails property.
     *
     * @param value allowed object is
     *              {@link ServerInfoDetails }
     */
    public void setServerInfoDetails(ServerInfoDetails value) {
        this.serverInfoDetails = value;
    }

}
