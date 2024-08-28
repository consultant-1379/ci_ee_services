package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="getResourceRightsResult" type="{http://bicatalog.dsws.businessobjects.com/2007/06/01}ResourceRight" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getResourceRightsResult"
})
@XmlRootElement(name = "getResourceRightsResponse")
public class GetResourceRightsResponse {

    @XmlElement(nillable = true)
    protected List<ResourceRight> getResourceRightsResult;

    /**
     * Gets the value of the getResourceRightsResult property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getResourceRightsResult property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGetResourceRightsResult().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link ResourceRight }
     */
    public List<ResourceRight> getGetResourceRightsResult() {
        if (getResourceRightsResult == null) {
            getResourceRightsResult = new ArrayList<ResourceRight>();
        }
        return this.getResourceRightsResult;
    }

}
