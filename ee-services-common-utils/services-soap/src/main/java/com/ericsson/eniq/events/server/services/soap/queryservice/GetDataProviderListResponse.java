package com.ericsson.eniq.events.server.services.soap.queryservice;

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
 *         &lt;element name="dataProviderInformation" type="{http://queryservice.dsws.businessobjects.com/2005}DataProviderInformation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "dataProviderInformation"
})
@XmlRootElement(name = "getDataProviderListResponse", namespace = "http://queryservice.dsws.businessobjects.com/2005")
public class GetDataProviderListResponse {

    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005")
    protected List<DataProviderInformation> dataProviderInformation;

    /**
     * Gets the value of the dataProviderInformation property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataProviderInformation property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataProviderInformation().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataProviderInformation }
     */
    public List<DataProviderInformation> getDataProviderInformation() {
        if (dataProviderInformation == null) {
            dataProviderInformation = new ArrayList<DataProviderInformation>();
        }
        return this.dataProviderInformation;
    }

}
