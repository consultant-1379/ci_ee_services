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
 *         &lt;element name="dataProviderInformation" type="{http://queryservice.dsws.businessobjects.com/2005}DataProviderInformation"/>
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
@XmlRootElement(name = "createDataProviderResponse", namespace = "http://queryservice.dsws.businessobjects.com/2005")
public class CreateDataProviderResponse {

    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected DataProviderInformation dataProviderInformation;

    /**
     * Gets the value of the dataProviderInformation property.
     *
     * @return possible object is
     *         {@link DataProviderInformation }
     */
    public DataProviderInformation getDataProviderInformation() {
        return dataProviderInformation;
    }

    /**
     * Sets the value of the dataProviderInformation property.
     *
     * @param value allowed object is
     *              {@link DataProviderInformation }
     */
    public void setDataProviderInformation(DataProviderInformation value) {
        this.dataProviderInformation = value;
    }

}
