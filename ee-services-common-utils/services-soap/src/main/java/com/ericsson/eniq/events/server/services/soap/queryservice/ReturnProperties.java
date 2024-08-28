package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReturnProperties complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ReturnProperties">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute ref="{http://queryservice.dsws.businessobjects.com/2007/06/01}IncludeDataSourceParameterValues use="required""/>
 *       &lt;attribute name="IncludeQuerySpecification" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="NoData" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnProperties", namespace = "http://queryservice.dsws.businessobjects.com/2005")
public class ReturnProperties {

    @XmlAttribute(name = "IncludeDataSourceParameterValues", namespace = "http://queryservice.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean includeDataSourceParameterValues;
    @XmlAttribute(name = "IncludeQuerySpecification", required = true)
    protected boolean includeQuerySpecification;
    @XmlAttribute(name = "NoData", required = true)
    protected boolean noData;

    /**
     * Gets the value of the includeDataSourceParameterValues property.
     */
    public boolean isIncludeDataSourceParameterValues() {
        return includeDataSourceParameterValues;
    }

    /**
     * Sets the value of the includeDataSourceParameterValues property.
     */
    public void setIncludeDataSourceParameterValues(boolean value) {
        this.includeDataSourceParameterValues = value;
    }

    /**
     * Gets the value of the includeQuerySpecification property.
     */
    public boolean isIncludeQuerySpecification() {
        return includeQuerySpecification;
    }

    /**
     * Sets the value of the includeQuerySpecification property.
     */
    public void setIncludeQuerySpecification(boolean value) {
        this.includeQuerySpecification = value;
    }

    /**
     * Gets the value of the noData property.
     */
    public boolean isNoData() {
        return noData;
    }

    /**
     * Sets the value of the noData property.
     */
    public void setNoData(boolean value) {
        this.noData = value;
    }

}
