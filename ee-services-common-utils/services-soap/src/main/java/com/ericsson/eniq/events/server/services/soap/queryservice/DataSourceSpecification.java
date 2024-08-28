package com.ericsson.eniq.events.server.services.soap.queryservice;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for DataSourceSpecification complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="DataSourceSpecification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataSourceClass" type="{http://datasource.businessobjects.com/2005}DataSourceClass" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Hierarchy" type="{http://datasource.businessobjects.com/2005}Hierarchy" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://datasourceparameter.businessobjects.com/2007/06/01}DataSourceParameters" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://datasource.businessobjects.com/2007/06/01}Sampling"/>
 *       &lt;/sequence>
 *       &lt;attribute name="UID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MaxRowsRetrieved" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="MaxRetrievalTime" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="MaxInListNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="RankConditionSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SubQuerySupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="CombinedQuerySupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="BothOperatorSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ExceptOperatorSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="AdvancedConditionSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ConditionObjectValueSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="DuplicateRowSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="IsNullOperatorSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="NotIsNullOperatorSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="ViewSQLSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="MissingRowSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="EditQueryAllowed" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="PercentageRankConditionSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SortOnAnyObjectSupported" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="QuerySortSupported" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSourceSpecification", namespace = "http://datasource.businessobjects.com/2005", propOrder = {
        "dataSourceClass", "hierarchy", "description", "dataSourceParameters", "sampling" })
public class DataSourceSpecification {

    @XmlElement(name = "DataSourceClass")
    protected List<DataSourceClass> dataSourceClass;

    @XmlElement(name = "Hierarchy")
    protected List<Hierarchy> hierarchy;

    @XmlElement(name = "Description", required = true)
    protected String description;

    @XmlElement(name = "DataSourceParameters", namespace = "http://datasourceparameter.businessobjects.com/2007/06/01")
    protected List<DataSourceParameter> dataSourceParameters;

    @XmlElement(name = "Sampling", namespace = "http://datasource.businessobjects.com/2007/06/01", required = true)
    protected Sampling sampling;

    @XmlAttribute(name = "UID", required = true)
    protected String uid;

    @XmlAttribute(name = "Name", required = true)
    protected String name;

    @XmlAttribute(name = "MaxRowsRetrieved", required = true)
    protected int maxRowsRetrieved;

    @XmlAttribute(name = "MaxRetrievalTime", required = true)
    protected int maxRetrievalTime;

    @XmlAttribute(name = "MaxInListNumber", required = true)
    protected int maxInListNumber;

    @XmlAttribute(name = "RankConditionSupported", required = true)
    protected boolean rankConditionSupported;

    @XmlAttribute(name = "SubQuerySupported", required = true)
    protected boolean subQuerySupported;

    @XmlAttribute(name = "CombinedQuerySupported", required = true)
    protected boolean combinedQuerySupported;

    @XmlAttribute(name = "BothOperatorSupported", required = true)
    protected boolean bothOperatorSupported;

    @XmlAttribute(name = "ExceptOperatorSupported", required = true)
    protected boolean exceptOperatorSupported;

    @XmlAttribute(name = "AdvancedConditionSupported", required = true)
    protected boolean advancedConditionSupported;

    @XmlAttribute(name = "ConditionObjectValueSupported", required = true)
    protected boolean conditionObjectValueSupported;

    @XmlAttribute(name = "DuplicateRowSupported", required = true)
    protected boolean duplicateRowSupported;

    @XmlAttribute(name = "IsNullOperatorSupported", required = true)
    protected boolean isNullOperatorSupported;

    @XmlAttribute(name = "NotIsNullOperatorSupported", required = true)
    protected boolean notIsNullOperatorSupported;

    @XmlAttribute(name = "ViewSQLSupported", required = true)
    protected boolean viewSQLSupported;

    @XmlAttribute(name = "MissingRowSupported", required = true)
    protected boolean missingRowSupported;

    @XmlAttribute(name = "EditQueryAllowed", required = true)
    protected boolean editQueryAllowed;

    @XmlAttribute(name = "PercentageRankConditionSupported", required = true)
    protected boolean percentageRankConditionSupported;

    @XmlAttribute(name = "SortOnAnyObjectSupported", required = true)
    protected boolean sortOnAnyObjectSupported;

    @XmlAttribute(name = "QuerySortSupported")
    protected Boolean querySortSupported;

    /**
     * Gets the value of the dataSourceClass property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceClass property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceClass().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceClass }
     */
    public List<DataSourceClass> getDataSourceClass() {
        if (dataSourceClass == null) {
            dataSourceClass = new ArrayList<DataSourceClass>();
        }
        return this.dataSourceClass;
    }

    /**
     * Gets the value of the hierarchy property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hierarchy property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHierarchy().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Hierarchy }
     */
    public List<Hierarchy> getHierarchy() {
        if (hierarchy == null) {
            hierarchy = new ArrayList<Hierarchy>();
        }
        return this.hierarchy;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDescription(final String value) {
        this.description = value;
    }

    /**
     * Gets the value of the dataSourceParameters property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSourceParameters property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSourceParameters().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.DataSourceParameter }
     */
    public List<DataSourceParameter> getDataSourceParameters() {
        if (dataSourceParameters == null) {
            dataSourceParameters = new ArrayList<DataSourceParameter>();
        }
        return this.dataSourceParameters;
    }

    /**
     * Gets the value of the sampling property.
     *
     * @return possible object is
     *         {@link Sampling }
     */
    public Sampling getSampling() {
        return sampling;
    }

    /**
     * Sets the value of the sampling property.
     *
     * @param value allowed object is
     *              {@link Sampling }
     */
    public void setSampling(final Sampling value) {
        this.sampling = value;
    }

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
    public void setUID(final String value) {
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
    public void setName(final String value) {
        this.name = value;
    }

    /**
     * Gets the value of the maxRowsRetrieved property.
     */
    public int getMaxRowsRetrieved() {
        return maxRowsRetrieved;
    }

    /**
     * Sets the value of the maxRowsRetrieved property.
     */
    public void setMaxRowsRetrieved(final int value) {
        this.maxRowsRetrieved = value;
    }

    /**
     * Gets the value of the maxRetrievalTime property.
     */
    public int getMaxRetrievalTime() {
        return maxRetrievalTime;
    }

    /**
     * Sets the value of the maxRetrievalTime property.
     */
    public void setMaxRetrievalTime(final int value) {
        this.maxRetrievalTime = value;
    }

    /**
     * Gets the value of the maxInListNumber property.
     */
    public int getMaxInListNumber() {
        return maxInListNumber;
    }

    /**
     * Sets the value of the maxInListNumber property.
     */
    public void setMaxInListNumber(final int value) {
        this.maxInListNumber = value;
    }

    /**
     * Gets the value of the rankConditionSupported property.
     */
    public boolean isRankConditionSupported() {
        return rankConditionSupported;
    }

    /**
     * Sets the value of the rankConditionSupported property.
     */
    public void setRankConditionSupported(final boolean value) {
        this.rankConditionSupported = value;
    }

    /**
     * Gets the value of the subQuerySupported property.
     */
    public boolean isSubQuerySupported() {
        return subQuerySupported;
    }

    /**
     * Sets the value of the subQuerySupported property.
     */
    public void setSubQuerySupported(final boolean value) {
        this.subQuerySupported = value;
    }

    /**
     * Gets the value of the combinedQuerySupported property.
     */
    public boolean isCombinedQuerySupported() {
        return combinedQuerySupported;
    }

    /**
     * Sets the value of the combinedQuerySupported property.
     */
    public void setCombinedQuerySupported(final boolean value) {
        this.combinedQuerySupported = value;
    }

    /**
     * Gets the value of the bothOperatorSupported property.
     */
    public boolean isBothOperatorSupported() {
        return bothOperatorSupported;
    }

    /**
     * Sets the value of the bothOperatorSupported property.
     */
    public void setBothOperatorSupported(final boolean value) {
        this.bothOperatorSupported = value;
    }

    /**
     * Gets the value of the exceptOperatorSupported property.
     */
    public boolean isExceptOperatorSupported() {
        return exceptOperatorSupported;
    }

    /**
     * Sets the value of the exceptOperatorSupported property.
     */
    public void setExceptOperatorSupported(final boolean value) {
        this.exceptOperatorSupported = value;
    }

    /**
     * Gets the value of the advancedConditionSupported property.
     */
    public boolean isAdvancedConditionSupported() {
        return advancedConditionSupported;
    }

    /**
     * Sets the value of the advancedConditionSupported property.
     */
    public void setAdvancedConditionSupported(final boolean value) {
        this.advancedConditionSupported = value;
    }

    /**
     * Gets the value of the conditionObjectValueSupported property.
     */
    public boolean isConditionObjectValueSupported() {
        return conditionObjectValueSupported;
    }

    /**
     * Sets the value of the conditionObjectValueSupported property.
     */
    public void setConditionObjectValueSupported(final boolean value) {
        this.conditionObjectValueSupported = value;
    }

    /**
     * Gets the value of the duplicateRowSupported property.
     */
    public boolean isDuplicateRowSupported() {
        return duplicateRowSupported;
    }

    /**
     * Sets the value of the duplicateRowSupported property.
     */
    public void setDuplicateRowSupported(final boolean value) {
        this.duplicateRowSupported = value;
    }

    /**
     * Gets the value of the isNullOperatorSupported property.
     */
    public boolean isIsNullOperatorSupported() {
        return isNullOperatorSupported;
    }

    /**
     * Sets the value of the isNullOperatorSupported property.
     */
    public void setIsNullOperatorSupported(final boolean value) {
        this.isNullOperatorSupported = value;
    }

    /**
     * Gets the value of the notIsNullOperatorSupported property.
     */
    public boolean isNotIsNullOperatorSupported() {
        return notIsNullOperatorSupported;
    }

    /**
     * Sets the value of the notIsNullOperatorSupported property.
     */
    public void setNotIsNullOperatorSupported(final boolean value) {
        this.notIsNullOperatorSupported = value;
    }

    /**
     * Gets the value of the viewSQLSupported property.
     */
    public boolean isViewSQLSupported() {
        return viewSQLSupported;
    }

    /**
     * Sets the value of the viewSQLSupported property.
     */
    public void setViewSQLSupported(final boolean value) {
        this.viewSQLSupported = value;
    }

    /**
     * Gets the value of the missingRowSupported property.
     */
    public boolean isMissingRowSupported() {
        return missingRowSupported;
    }

    /**
     * Sets the value of the missingRowSupported property.
     */
    public void setMissingRowSupported(final boolean value) {
        this.missingRowSupported = value;
    }

    /**
     * Gets the value of the editQueryAllowed property.
     */
    public boolean isEditQueryAllowed() {
        return editQueryAllowed;
    }

    /**
     * Sets the value of the editQueryAllowed property.
     */
    public void setEditQueryAllowed(final boolean value) {
        this.editQueryAllowed = value;
    }

    /**
     * Gets the value of the percentageRankConditionSupported property.
     */
    public boolean isPercentageRankConditionSupported() {
        return percentageRankConditionSupported;
    }

    /**
     * Sets the value of the percentageRankConditionSupported property.
     */
    public void setPercentageRankConditionSupported(final boolean value) {
        this.percentageRankConditionSupported = value;
    }

    /**
     * Gets the value of the sortOnAnyObjectSupported property.
     */
    public boolean isSortOnAnyObjectSupported() {
        return sortOnAnyObjectSupported;
    }

    /**
     * Sets the value of the sortOnAnyObjectSupported property.
     */
    public void setSortOnAnyObjectSupported(final boolean value) {
        this.sortOnAnyObjectSupported = value;
    }

    /**
     * Gets the value of the querySortSupported property.
     *
     * @return possible object is
     *         {@link Boolean }
     */
    public boolean isQuerySortSupported() {
        if (querySortSupported == null) {
            return true;
        }
        return querySortSupported;
    }

    /**
     * Sets the value of the querySortSupported property.
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setQuerySortSupported(final Boolean value) {
        this.querySortSupported = value;
    }

}
