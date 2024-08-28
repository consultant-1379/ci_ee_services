package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for DataProviderInformation complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DataProviderInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="QuerySpecification" type="{http://query.businessobjects.com/2005}QuerySpecification" minOccurs="0"/>
 *         &lt;element ref="{http://datasourceparameter.businessobjects.com/2007/06/01}DataSourceParameterValues" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Duration" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="RowCount" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="RefreshDate" use="required" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
 *       &lt;attribute name="Partial" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="FlowCount" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="DataSourceUID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DocumentReference" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute ref="{http://queryservice.dsws.businessobjects.com/2007/06/01}SampledData use="required""/>
 *       &lt;attribute ref="{http://queryservice.dsws.businessobjects.com/2007/06/01}Refreshable use="required""/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataProviderInformation", namespace = "http://queryservice.dsws.businessobjects.com/2005", propOrder = {
        "uid",
        "name",
        "querySpecification",
        "dataSourceParameterValues"
})
public class DataProviderInformation {

    @XmlElement(name = "UID", required = true)
    protected String uid;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "QuerySpecification")
    protected QuerySpecification querySpecification;
    @XmlElement(name = "DataSourceParameterValues", namespace = "http://datasourceparameter.businessobjects.com/2007/06/01")
    protected List<DataSourceParameterValue> dataSourceParameterValues;
    @XmlAttribute(name = "Duration", required = true)
    protected int duration;
    @XmlAttribute(name = "RowCount", required = true)
    protected int rowCount;
    @XmlAttribute(name = "RefreshDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar refreshDate;
    @XmlAttribute(name = "Partial", required = true)
    protected boolean partial;
    @XmlAttribute(name = "FlowCount", required = true)
    protected int flowCount;
    @XmlAttribute(name = "DataSourceUID", required = true)
    protected String dataSourceUID;
    @XmlAttribute(name = "DocumentReference", required = true)
    protected String documentReference;
    @XmlAttribute(name = "SampledData", namespace = "http://queryservice.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean sampledData;
    @XmlAttribute(name = "Refreshable", namespace = "http://queryservice.dsws.businessobjects.com/2007/06/01", required = true)
    protected boolean refreshable;

    /**
     * Gets the value of the uid property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUID() {
        return uid;
    }

    /**
     * Sets the value of the uid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUID(String value) {
        this.uid = value;
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
     * Gets the value of the dataSourceParameterValues property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceParameterValues property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceParameterValues().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSourceParameterValue }
     */
    public List<DataSourceParameterValue> getDataSourceParameterValues() {
        if (dataSourceParameterValues == null) {
            dataSourceParameterValues = new ArrayList<DataSourceParameterValue>();
        }
        return this.dataSourceParameterValues;
    }

    /**
     * Gets the value of the duration property.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Gets the value of the rowCount property.
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * Sets the value of the rowCount property.
     */
    public void setRowCount(int value) {
        this.rowCount = value;
    }

    /**
     * Gets the value of the refreshDate property.
     *
     * @return possible object is
     *         {@link javax.xml.datatype.XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getRefreshDate() {
        return refreshDate;
    }

    /**
     * Sets the value of the refreshDate property.
     *
     * @param value allowed object is
     *              {@link javax.xml.datatype.XMLGregorianCalendar }
     */
    public void setRefreshDate(XMLGregorianCalendar value) {
        this.refreshDate = value;
    }

    /**
     * Gets the value of the partial property.
     */
    public boolean isPartial() {
        return partial;
    }

    /**
     * Sets the value of the partial property.
     */
    public void setPartial(boolean value) {
        this.partial = value;
    }

    /**
     * Gets the value of the flowCount property.
     */
    public int getFlowCount() {
        return flowCount;
    }

    /**
     * Sets the value of the flowCount property.
     */
    public void setFlowCount(int value) {
        this.flowCount = value;
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
     * Gets the value of the documentReference property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDocumentReference() {
        return documentReference;
    }

    /**
     * Sets the value of the documentReference property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentReference(String value) {
        this.documentReference = value;
    }

    /**
     * Gets the value of the sampledData property.
     */
    public boolean isSampledData() {
        return sampledData;
    }

    /**
     * Sets the value of the sampledData property.
     */
    public void setSampledData(boolean value) {
        this.sampledData = value;
    }

    /**
     * Gets the value of the refreshable property.
     */
    public boolean isRefreshable() {
        return refreshable;
    }

    /**
     * Sets the value of the refreshable property.
     */
    public void setRefreshable(boolean value) {
        this.refreshable = value;
    }

}
