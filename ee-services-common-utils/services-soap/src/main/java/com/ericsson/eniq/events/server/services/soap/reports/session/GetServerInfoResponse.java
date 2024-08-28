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
 *         &lt;element name="ServerInfo" type="{http://session.dsws.businessobjects.com/2007/06/01}ServerInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "serverInfo"
})
@XmlRootElement(name = "getServerInfoResponse")
public class GetServerInfoResponse {

    @XmlElement(name = "ServerInfo", required = true)
    protected ServerInfo serverInfo;

    /**
     * Gets the value of the serverInfo property.
     *
     * @return possible object is
     *         {@link ServerInfo }
     */
    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    /**
     * Sets the value of the serverInfo property.
     *
     * @param value allowed object is
     *              {@link ServerInfo }
     */
    public void setServerInfo(ServerInfo value) {
        this.serverInfo = value;
    }

}
