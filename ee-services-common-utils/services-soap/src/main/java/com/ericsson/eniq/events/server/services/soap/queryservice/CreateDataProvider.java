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
 *         &lt;element name="dataSourceUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="querySpecification" type="{http://query.businessobjects.com/2005}QuerySpecification" minOccurs="0"/>
 *         &lt;element name="returnProperties" type="{http://queryservice.dsws.businessobjects.com/2005}ReturnProperties"/>
 *         &lt;element ref="{http://queryservice.dsws.businessobjects.com/2007/06/01}inputProperties"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "session",
        "dataSourceUID",
        "querySpecification",
        "returnProperties",
        "inputProperties"
})
@XmlRootElement(name = "createDataProvider", namespace = "http://queryservice.dsws.businessobjects.com/2005")
public class CreateDataProvider {

    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected String session;
    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected String dataSourceUID;
    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005")
    protected QuerySpecification querySpecification;
    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2005", required = true)
    protected ReturnProperties returnProperties;
    @XmlElement(namespace = "http://queryservice.dsws.businessobjects.com/2007/06/01", required = true)
    protected InputProperties inputProperties;

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

    /**
     * Gets the value of the dataSourceUID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDataSourceUID() {
        return dataSourceUID;
    }

    /**
     * Sets the value of the dataSourceUID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDataSourceUID(String value) {
        this.dataSourceUID = value;
    }

    /**
     * Gets the value of the querySpecification property.
     *
     * @return possible object is
     *         {@link QuerySpecification }
     */
    public QuerySpecification getQuerySpecification() {
        return querySpecification;
    }

    /**
     * Sets the value of the querySpecification property.
     *
     * @param value allowed object is
     *              {@link QuerySpecification }
     */
    public void setQuerySpecification(QuerySpecification value) {
        this.querySpecification = value;
    }

    /**
     * Gets the value of the returnProperties property.
     *
     * @return possible object is
     *         {@link ReturnProperties }
     */
    public ReturnProperties getReturnProperties() {
        return returnProperties;
    }

    /**
     * Sets the value of the returnProperties property.
     *
     * @param value allowed object is
     *              {@link ReturnProperties }
     */
    public void setReturnProperties(ReturnProperties value) {
        this.returnProperties = value;
    }

    /**
     * Gets the value of the inputProperties property.
     *
     * @return possible object is
     *         {@link InputProperties }
     */
    public InputProperties getInputProperties() {
        return inputProperties;
    }

    /**
     * Sets the value of the inputProperties property.
     *
     * @param value allowed object is
     *              {@link InputProperties }
     */
    public void setInputProperties(InputProperties value) {
        this.inputProperties = value;
    }

}
