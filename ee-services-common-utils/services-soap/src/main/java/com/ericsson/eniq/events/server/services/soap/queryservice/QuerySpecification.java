package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for QuerySpecification complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="QuerySpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QueryBase" type="{http://query.businessobjects.com/2005}QueryBase"/>
 *         &lt;element name="QueryProperty" type="{http://query.businessobjects.com/2005}QueryProperty" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute ref="{http://query.businessobjects.com/2007/06/01}SamplingMode use="required""/>
 *       &lt;attribute ref="{http://query.businessobjects.com/2007/06/01}SamplingSize use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuerySpecification", propOrder = {
        "queryBase",
        "queryProperty"
})
public class QuerySpecification {

    @XmlElement(name = "QueryBase", required = true)
    protected QueryBase queryBase;
    @XmlElement(name = "QueryProperty")
    protected List<QueryProperty> queryProperty;
    @XmlAttribute(name = "Name", required = true)
    protected String name;
    @XmlAttribute(name = "SamplingMode", namespace = "http://query.businessobjects.com/2007/06/01", required = true)
    protected SamplingMode samplingMode;
    @XmlAttribute(name = "SamplingSize", namespace = "http://query.businessobjects.com/2007/06/01", required = true)
    protected int samplingSize;

    /**
     * Gets the value of the queryBase property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryBase }
     */
    public QueryBase getQueryBase() {
        return queryBase;
    }

    /**
     * Sets the value of the queryBase property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryBase }
     */
    public void setQueryBase(QueryBase value) {
        this.queryBase = value;
    }

    /**
     * Gets the value of the queryProperty property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queryProperty property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueryProperty().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryProperty }
     */
    public List<QueryProperty> getQueryProperty() {
        if (queryProperty == null) {
            queryProperty = new ArrayList<QueryProperty>();
        }
        return this.queryProperty;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the samplingMode property.
     *
     * @return possible object is
     *         {@link SamplingMode }
     */
    public SamplingMode getSamplingMode() {
        return samplingMode;
    }

    /**
     * Sets the value of the samplingMode property.
     *
     * @param value allowed object is
     *              {@link SamplingMode }
     */
    public void setSamplingMode(SamplingMode value) {
        this.samplingMode = value;
    }

    /**
     * Gets the value of the samplingSize property.
     */
    public int getSamplingSize() {
        return samplingSize;
    }

    /**
     * Sets the value of the samplingSize property.
     */
    public void setSamplingSize(int value) {
        this.samplingSize = value;
    }

}
