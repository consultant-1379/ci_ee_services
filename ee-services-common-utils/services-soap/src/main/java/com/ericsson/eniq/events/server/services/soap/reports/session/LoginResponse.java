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
 *         &lt;element name="SessionInfo" type="{http://session.dsws.businessobjects.com/2007/06/01}SessionInfo"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sessionInfo"
})
@XmlRootElement(name = "loginResponse")
public class LoginResponse {

    @XmlElement(name = "SessionInfo", required = true)
    protected SessionInfo sessionInfo;

    /**
     * Gets the value of the sessionInfo property.
     *
     * @return possible object is
     *         {@link SessionInfo }
     */
    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    /**
     * Sets the value of the sessionInfo property.
     *
     * @param value allowed object is
     *              {@link SessionInfo }
     */
    public void setSessionInfo(SessionInfo value) {
        this.sessionInfo = value;
    }

}
