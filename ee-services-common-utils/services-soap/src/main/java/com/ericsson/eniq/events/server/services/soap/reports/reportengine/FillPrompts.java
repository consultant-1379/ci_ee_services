package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for FillPrompts complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="FillPrompts">
 *   &lt;complexContent>
 *     &lt;extension base="{http://reportengine.dsws.businessobjects.com/2005}Action">
 *       &lt;sequence>
 *         &lt;element name="FillPromptList" type="{http://reportengine.dsws.businessobjects.com/2005}FillPrompt" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FillPrompts", propOrder = {
        "fillPromptList"
})
public class FillPrompts
        extends Action {

    @XmlElement(name = "FillPromptList", nillable = true)
    protected List<FillPrompt> fillPromptList;

    /**
     * Gets the value of the fillPromptList property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fillPromptList property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFillPromptList().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link FillPrompt }
     */
    public List<FillPrompt> getFillPromptList() {
        if (fillPromptList == null) {
            fillPromptList = new ArrayList<FillPrompt>();
        }
        return this.fillPromptList;
    }

}
