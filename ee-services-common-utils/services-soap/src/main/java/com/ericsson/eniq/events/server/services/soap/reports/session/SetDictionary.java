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
 *         &lt;element name="session" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dictionary" type="{http://session.dsws.businessobjects.com/2007/06/01}DictionaryType"/>
 *         &lt;element name="entries" type="{http://session.dsws.businessobjects.com/2007/06/01}Entry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "session",
        "dictionary",
        "entries"
})
@XmlRootElement(name = "setDictionary")
public class SetDictionary {

    @XmlElement(required = true)
    protected String session;
    @XmlElement(required = true)
    protected DictionaryType dictionary;
    @XmlElement(nillable = true)
    protected List<Entry> entries;

    /**
     * Gets the value of the session property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSession(String value) {
        this.session = value;
    }

    /**
     * Gets the value of the dictionary property.
     *
     * @return possible object is
     *         {@link DictionaryType }
     */
    public DictionaryType getDictionary() {
        return dictionary;
    }

    /**
     * Sets the value of the dictionary property.
     *
     * @param value allowed object is
     *              {@link DictionaryType }
     */
    public void setDictionary(DictionaryType value) {
        this.dictionary = value;
    }

    /**
     * Gets the value of the entries property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entries property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntries().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Entry }
     */
    public List<Entry> getEntries() {
        if (entries == null) {
            entries = new ArrayList<Entry>();
        }
        return this.entries;
    }

}
