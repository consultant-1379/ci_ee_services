package com.ericsson.eniq.events.server.services.soap.network.kpi.EniqEventsServicesNetworkRNCCellQuery;

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
 *         &lt;element name="login" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Enter_value_s__for__RNC_Id_" type="{EniqEventsServicesNetworkRNCCellQuery}LovValueIndex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Enter_value_s__for__UCell_Id_" type="{EniqEventsServicesNetworkRNCCellQuery}LovValueIndex" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Date_" type="{EniqEventsServicesNetworkRNCCellQuery}LovValueIndex"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "login",
        "password",
        "enterValueSForRNCId",
        "enterValueSForUCellId",
        "date"
})
@XmlRootElement(name = "runQueryAsAServiceEx")
public class RunQueryAsAServiceEx {

    @XmlElement(required = true)
    protected String login;
    @XmlElement(required = true)
    protected String password;
    @XmlElement(name = "Enter_value_s__for__RNC_Id_", nillable = true)
    protected List<LovValueIndex> enterValueSForRNCId;
    @XmlElement(name = "Enter_value_s__for__UCell_Id_", nillable = true)
    protected List<LovValueIndex> enterValueSForUCellId;
    @XmlElement(name = "Date_", required = true, nillable = true)
    protected LovValueIndex date;

    /**
     * Gets the value of the login property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the enterValueSForRNCId property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enterValueSForRNCId property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnterValueSForRNCId().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link LovValueIndex }
     */
    public List<LovValueIndex> getEnterValueSForRNCId() {
        if (enterValueSForRNCId == null) {
            enterValueSForRNCId = new ArrayList<LovValueIndex>();
        }
        return this.enterValueSForRNCId;
    }

    /**
     * Gets the value of the enterValueSForUCellId property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enterValueSForUCellId property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnterValueSForUCellId().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link LovValueIndex }
     */
    public List<LovValueIndex> getEnterValueSForUCellId() {
        if (enterValueSForUCellId == null) {
            enterValueSForUCellId = new ArrayList<LovValueIndex>();
        }
        return this.enterValueSForUCellId;
    }

    /**
     * Gets the value of the date property.
     *
     * @return possible object is
     *         {@link LovValueIndex }
     */
    public LovValueIndex getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     *
     * @param value allowed object is
     *              {@link LovValueIndex }
     */
    public void setDate(LovValueIndex value) {
        this.date = value;
    }

}
