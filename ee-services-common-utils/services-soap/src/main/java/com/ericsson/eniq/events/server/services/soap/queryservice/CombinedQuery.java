package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for CombinedQuery complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="CombinedQuery">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}QueryBase">
 *       &lt;sequence>
 *         &lt;element name="Queries" type="{http://query.businessobjects.com/2005}QueryBase" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="CombinedQueryOperator" type="{http://query.businessobjects.com/2005}CombinedQueryOperator" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CombinedQuery", propOrder = {
        "queries"
})
public class CombinedQuery
        extends QueryBase {

    @XmlElement(name = "Queries", nillable = true)
    protected List<QueryBase> queries;
    @XmlAttribute(name = "CombinedQueryOperator")
    protected CombinedQueryOperator combinedQueryOperator;

    /**
     * Gets the value of the queries property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the queries property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQueries().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link QueryBase }
     */
    public List<QueryBase> getQueries() {
        if (queries == null) {
            queries = new ArrayList<QueryBase>();
        }
        return this.queries;
    }

    /**
     * Gets the value of the combinedQueryOperator property.
     *
     * @return possible object is
     *         {@link CombinedQueryOperator }
     */
    public CombinedQueryOperator getCombinedQueryOperator() {
        return combinedQueryOperator;
    }

    /**
     * Sets the value of the combinedQueryOperator property.
     *
     * @param value allowed object is
     *              {@link CombinedQueryOperator }
     */
    public void setCombinedQueryOperator(CombinedQueryOperator value) {
        this.combinedQueryOperator = value;
    }

}
