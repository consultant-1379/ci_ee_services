package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for FillQueryContexts complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="FillQueryContexts">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Action">
 *       &lt;sequence>
 *         &lt;element name="FillQueryContextList" type="{http://reportengine.dsws.businessobjects.com/2005}FillQueryContext" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FillQueryContexts", propOrder = {
        "fillQueryContextList"
})
public class FillQueryContexts
        extends Action {

    @XmlElement(name = "FillQueryContextList", nillable = true)
    protected List<FillQueryContext> fillQueryContextList;

    /**
     * Gets the value of the fillQueryContextList property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fillQueryContextList property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFillQueryContextList().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link FillQueryContext }
     */
    public List<FillQueryContext> getFillQueryContextList() {
        if (fillQueryContextList == null) {
            fillQueryContextList = new ArrayList<FillQueryContext>();
        }
        return this.fillQueryContextList;
    }

}
