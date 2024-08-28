package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Query complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="Query">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}QueryBase">
 *       &lt;sequence>
 *         &lt;element name="QueryResult" type="{http://query.businessobjects.com/2005}QueryObject" maxOccurs="unbounded"/>
 *         &lt;element name="QueryScope" type="{http://query.businessobjects.com/2005}QueryObject" maxOccurs="unbounded"/>
 *         &lt;element name="QueryScopeLevel" type="{http://query.businessobjects.com/2005}QueryScopeLevel" minOccurs="0"/>
 *         &lt;element name="QueryObjectSort" type="{http://query.businessobjects.com/2005}QueryObjectSort" maxOccurs="unbounded"/>
 *         &lt;element name="QueryCondition" type="{http://query.businessobjects.com/2005}QueryCondition" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Query", propOrder = {
        "queryResult",
        "queryScope",
        "queryScopeLevel",
        "queryObjectSort",
        "queryCondition"
})
public class Query
        extends QueryBase {

    @XmlElement(name = "QueryResult", required = true)
    protected List<QueryObject> queryResult;
    @XmlElement(name = "QueryScope", required = true)
    protected List<QueryObject> queryScope;
    @XmlElement(name = "QueryScopeLevel")
    protected QueryScopeLevel queryScopeLevel;
    @XmlElement(name = "QueryObjectSort", required = true)
    protected List<QueryObjectSort> queryObjectSort;
    @XmlElement(name = "QueryCondition")
    protected QueryCondition queryCondition;

    /**
     * Gets the value of the queryResult property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queryResult property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueryResult().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryObject }
     */
    public List<QueryObject> getQueryResult() {
        if (queryResult == null) {
            queryResult = new ArrayList<QueryObject>();
        }
        return this.queryResult;
    }

    /**
     * Gets the value of the queryScope property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queryScope property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueryScope().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryObject }
     */
    public List<QueryObject> getQueryScope() {
        if (queryScope == null) {
            queryScope = new ArrayList<QueryObject>();
        }
        return this.queryScope;
    }

    /**
     * Gets the value of the queryScopeLevel property.
     *
     * @return possible object is
     *         {@link QueryScopeLevel }
     */
    public QueryScopeLevel getQueryScopeLevel() {
        return queryScopeLevel;
    }

    /**
     * Sets the value of the queryScopeLevel property.
     *
     * @param value allowed object is
     *              {@link QueryScopeLevel }
     */
    public void setQueryScopeLevel(QueryScopeLevel value) {
        this.queryScopeLevel = value;
    }

    /**
     * Gets the value of the queryObjectSort property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queryObjectSort property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueryObjectSort().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryObjectSort }
     */
    public List<QueryObjectSort> getQueryObjectSort() {
        if (queryObjectSort == null) {
            queryObjectSort = new ArrayList<QueryObjectSort>();
        }
        return this.queryObjectSort;
    }

    /**
     * Gets the value of the queryCondition property.
     *
     * @return possible object is
     *         {@link QueryCondition }
     */
    public QueryCondition getQueryCondition() {
        return queryCondition;
    }

    /**
     * Sets the value of the queryCondition property.
     *
     * @param value allowed object is
     *              {@link QueryCondition }
     */
    public void setQueryCondition(QueryCondition value) {
        this.queryCondition = value;
    }

}
