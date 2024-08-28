package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for SubQuery complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="SubQuery">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}ConditionBase">
 *       &lt;sequence>
 *         &lt;element name="FilteredObject" type="{http://query.businessobjects.com/2005}QueryObject" maxOccurs="unbounded"/>
 *         &lt;element name="ResultObject" type="{http://query.businessobjects.com/2005}QueryObject" maxOccurs="unbounded"/>
 *         &lt;element name="QueryCondition" type="{http://query.businessobjects.com/2005}QueryCondition" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ConditionOperator" use="required" type="{http://query.businessobjects.com/2005}ConditionOperator" />
 *       &lt;attribute name="ComparisonOperator" type="{http://query.businessobjects.com/2005}ComparisonOperator" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubQuery", propOrder = {
        "filteredObject",
        "resultObject",
        "queryCondition"
})
public class SubQuery
        extends ConditionBase {

    @XmlElement(name = "FilteredObject", required = true)
    protected List<QueryObject> filteredObject;
    @XmlElement(name = "ResultObject", required = true)
    protected List<QueryObject> resultObject;
    @XmlElement(name = "QueryCondition")
    protected QueryCondition queryCondition;
    @XmlAttribute(name = "ConditionOperator", required = true)
    protected ConditionOperator conditionOperator;
    @XmlAttribute(name = "ComparisonOperator")
    protected ComparisonOperator comparisonOperator;

    /**
     * Gets the value of the filteredObject property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the filteredObject property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFilteredObject().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryObject }
     */
    public List<QueryObject> getFilteredObject() {
        if (filteredObject == null) {
            filteredObject = new ArrayList<QueryObject>();
        }
        return this.filteredObject;
    }

    /**
     * Gets the value of the resultObject property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resultObject property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResultObject().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryObject }
     */
    public List<QueryObject> getResultObject() {
        if (resultObject == null) {
            resultObject = new ArrayList<QueryObject>();
        }
        return this.resultObject;
    }

    /**
     * Gets the value of the queryCondition property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryCondition }
     */
    public QueryCondition getQueryCondition() {
        return queryCondition;
    }

    /**
     * Sets the value of the queryCondition property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.QueryCondition }
     */
    public void setQueryCondition(QueryCondition value) {
        this.queryCondition = value;
    }

    /**
     * Gets the value of the conditionOperator property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.ConditionOperator }
     */
    public ConditionOperator getConditionOperator() {
        return conditionOperator;
    }

    /**
     * Sets the value of the conditionOperator property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.ConditionOperator }
     */
    public void setConditionOperator(ConditionOperator value) {
        this.conditionOperator = value;
    }

    /**
     * Gets the value of the comparisonOperator property.
     *
     * @return possible object is
     *         {@link com.ericsson.eniq.events.server.services.soap.queryservice.ComparisonOperator }
     */
    public ComparisonOperator getComparisonOperator() {
        return comparisonOperator;
    }

    /**
     * Sets the value of the comparisonOperator property.
     *
     * @param value allowed object is
     *              {@link com.ericsson.eniq.events.server.services.soap.queryservice.ComparisonOperator }
     */
    public void setComparisonOperator(ComparisonOperator value) {
        this.comparisonOperator = value;
    }

}
