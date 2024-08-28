package com.ericsson.eniq.events.server.services.soap.reports.session;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ServerInfo complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ServerInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AvailableAuthentications" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Clustered" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerInfo", propOrder = {
        "availableAuthentications"
})
@XmlSeeAlso({
        ServerInfoDetails.class
})
public class ServerInfo {

    @XmlElement(name = "AvailableAuthentications", nillable = true)
    protected List<String> availableAuthentications;
    @XmlAttribute(name = "Version", required = true)
    protected String version;
    @XmlAttribute(name = "Clustered", required = true)
    protected boolean clustered;

    /**
     * Gets the value of the availableAuthentications property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the availableAuthentications property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAvailableAuthentications().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     */
    public List<String> getAvailableAuthentications() {
        if (availableAuthentications == null) {
            availableAuthentications = new ArrayList<String>();
        }
        return this.availableAuthentications;
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

    /**
     * Gets the value of the clustered property.
     */
    public boolean isClustered() {
        return clustered;
    }

    /**
     * Sets the value of the clustered property.
     */
    public void setClustered(boolean value) {
        this.clustered = value;
    }

}
