package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for FillDBLogons complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="FillDBLogons">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Action">
 *       &lt;sequence>
 *         &lt;element name="FillDBLogonList" type="{http://reportengine.dsws.businessobjects.com/2005}FillDBLogon" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FillDBLogons", propOrder = {
        "fillDBLogonList"
})
public class FillDBLogons
        extends Action {

    @XmlElement(name = "FillDBLogonList", nillable = true)
    protected List<FillDBLogon> fillDBLogonList;

    /**
     * Gets the value of the fillDBLogonList property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fillDBLogonList property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFillDBLogonList().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link FillDBLogon }
     */
    public List<FillDBLogon> getFillDBLogonList() {
        if (fillDBLogonList == null) {
            fillDBLogonList = new ArrayList<FillDBLogon>();
        }
        return this.fillDBLogonList;
    }

}
