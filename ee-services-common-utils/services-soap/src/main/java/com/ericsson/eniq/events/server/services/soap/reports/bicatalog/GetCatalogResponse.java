package com.ericsson.eniq.events.server.services.soap.reports.bicatalog;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="getCatalogResult" type="{http://bicatalog.dsws.businessobjects.com/2007/06/01}BICatalogObject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "getCatalogResult"
})
@XmlRootElement(name = "getCatalogResponse")
public class GetCatalogResponse {

    @XmlElement(nillable = true)
    protected List<BICatalogObject> getCatalogResult;

    /**
     * Gets the value of the getCatalogResult property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the getCatalogResult property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGetCatalogResult().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link BICatalogObject }
     */
    public List<BICatalogObject> getGetCatalogResult() {
        if (getCatalogResult == null) {
            getCatalogResult = new ArrayList<BICatalogObject>();
        }
        return this.getCatalogResult;
    }

}
