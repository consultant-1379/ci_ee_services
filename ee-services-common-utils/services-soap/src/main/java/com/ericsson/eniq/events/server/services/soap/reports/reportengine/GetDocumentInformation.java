package com.ericsson.eniq.events.server.services.soap.reports.reportengine;

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
 *         &lt;element name="sessionID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentReference" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retrieveMustFillInfo" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveMustFillInfo" minOccurs="0"/>
 *         &lt;element name="actions" type="{http://reportengine.dsws.businessobjects.com/2005}Action" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="navigate" type="{http://reportengine.dsws.businessobjects.com/2005}Navigate" minOccurs="0"/>
 *         &lt;element name="retrieveData" type="{http://reportengine.dsws.businessobjects.com/2005}RetrieveData" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "sessionID",
        "documentReference",
        "retrieveMustFillInfo",
        "actions",
        "navigate",
        "retrieveData"
})
@XmlRootElement(name = "getDocumentInformation")
public class GetDocumentInformation {

    protected String sessionID;
    protected String documentReference;
    protected RetrieveMustFillInfo retrieveMustFillInfo;
    @XmlElement(nillable = true)
    protected List<Action> actions;
    protected Navigate navigate;
    protected RetrieveData retrieveData;

    /**
     * Gets the value of the sessionID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSessionID() {
        return sessionID;
    }

    /**
     * Sets the value of the sessionID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSessionID(String value) {
        this.sessionID = value;
    }

    /**
     * Gets the value of the documentReference property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDocumentReference() {
        return documentReference;
    }

    /**
     * Sets the value of the documentReference property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDocumentReference(String value) {
        this.documentReference = value;
    }

    /**
     * Gets the value of the retrieveMustFillInfo property.
     *
     * @return possible object is
     *         {@link RetrieveMustFillInfo }
     */
    public RetrieveMustFillInfo getRetrieveMustFillInfo() {
        return retrieveMustFillInfo;
    }

    /**
     * Sets the value of the retrieveMustFillInfo property.
     *
     * @param value allowed object is
     *              {@link RetrieveMustFillInfo }
     */
    public void setRetrieveMustFillInfo(RetrieveMustFillInfo value) {
        this.retrieveMustFillInfo = value;
    }

    /**
     * Gets the value of the actions property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actions property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActions().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Action }
     */
    public List<Action> getActions() {
        if (actions == null) {
            actions = new ArrayList<Action>();
        }
        return this.actions;
    }

    /**
     * Gets the value of the navigate property.
     *
     * @return possible object is
     *         {@link Navigate }
     */
    public Navigate getNavigate() {
        return navigate;
    }

    /**
     * Sets the value of the navigate property.
     *
     * @param value allowed object is
     *              {@link Navigate }
     */
    public void setNavigate(Navigate value) {
        this.navigate = value;
    }

    /**
     * Gets the value of the retrieveData property.
     *
     * @return possible object is
     *         {@link RetrieveData }
     */
    public RetrieveData getRetrieveData() {
        return retrieveData;
    }

    /**
     * Sets the value of the retrieveData property.
     *
     * @param value allowed object is
     *              {@link RetrieveData }
     */
    public void setRetrieveData(RetrieveData value) {
        this.retrieveData = value;
    }

}
