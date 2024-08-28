package com.ericsson.eniq.events.server.services.soap.queryservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ObjectComparison complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="ObjectComparison">
 *   &lt;complexContent>
 *     &lt;extension base="{http://query.businessobjects.com/2005}Operand">
 *       &lt;sequence>
 *         &lt;element name="QueryObject" type="{http://query.businessobjects.com/2005}QueryObject"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObjectComparison", propOrder = {
        "queryObject"
})
public class ObjectComparison
        extends Operand {

    @XmlElement(name = "QueryObject", required = true)
    protected QueryObject queryObject;

    /**
     * Gets the value of the queryObject property.
     *
     * @return possible object is
     *         {@link QueryObject }
     */
    public QueryObject getQueryObject() {
        return queryObject;
    }

    /**
     * Sets the value of the queryObject property.
     *
     * @param value allowed object is
     *              {@link QueryObject }
     */
    public void setQueryObject(QueryObject value) {
        this.queryObject = value;
    }

}
