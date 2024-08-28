package com.ericsson.eniq.events.server.services.soap.reports.session;

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
 *         &lt;element name="Dictionary" type="{http://session.dsws.businessobjects.com/2007/06/01}Entry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "dictionary"
})
@XmlRootElement(name = "getDictionaryResponse")
public class GetDictionaryResponse {

    @XmlElement(name = "Dictionary", nillable = true)
    protected List<Entry> dictionary;

    /**
     * Gets the value of the dictionary property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dictionary property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDictionary().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Entry }
     */
    public List<Entry> getDictionary() {
        if (dictionary == null) {
            dictionary = new ArrayList<Entry>();
        }
        return this.dictionary;
    }

}
