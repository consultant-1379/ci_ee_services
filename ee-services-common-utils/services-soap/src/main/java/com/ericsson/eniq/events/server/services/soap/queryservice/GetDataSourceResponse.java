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
 *         &lt;element name="dataSourceSpecification" type="{http://datasource.businessobjects.com/2005}DataSourceSpecification"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "dataSourceSpecification"
})
@XmlRootElement(name = "getDataSourceResponse", namespace = "http://queryservice.dsws.businessobjects.com/2005")
public class GetDataSourceResponse {

    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected DataSourceSpecification dataSourceSpecification;

    /**
     * Gets the value of the dataSourceSpecification property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceSpecification }
     */
    public DataSourceSpecification getDataSourceSpecification() {
        return dataSourceSpecification;
    }

    /**
     * Sets the value of the dataSourceSpecification property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceSpecification }
     */
    public void setDataSourceSpecification(DataSourceSpecification value) {
        this.dataSourceSpecification = value;
    }

}
