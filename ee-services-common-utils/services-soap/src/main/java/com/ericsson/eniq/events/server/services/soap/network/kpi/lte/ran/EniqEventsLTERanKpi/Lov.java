
package com.ericsson.eniq.events.server.services.soap.network.kpi.lte.ran.EniqEventsLTERanKpi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Lov complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Lov">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="valueindex" type="{EniqEventsLTENetworkRanKpi}LovValueIndex" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Lov", propOrder = {
    "valueindex"
})
public class Lov {

    protected List<LovValueIndex> valueindex;

    /**
     * Gets the value of the valueindex property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the valueindex property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValueindex().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LovValueIndex }
     * 
     * 
     */
    public List<LovValueIndex> getValueindex() {
        if (valueindex == null) {
            valueindex = new ArrayList<LovValueIndex>();
        }
        return this.valueindex;
    }

}
