package com.ericsson.eniq.events.server.services.soap.reports.session;

import org.w3c.dom.Element;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>Java class for SessionInfo complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="SessionInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserRights" type="{http://session.dsws.businessobjects.com/2007/06/01}UserRight" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="WSResourceList" type="{http://session.dsws.businessobjects.com/2007/06/01}WSResource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LoginInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Locale" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="PVLocale" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="MustChangePassword" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="SessionID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DefaultToken" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeOut" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="UserCUID" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SerializedSession" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimeZone" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;anyAttribute namespace='##other'/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SessionInfo", propOrder = {
        "userRights",
        "wsResourceList",
        "loginInfo",
        "any"
})
public class SessionInfo {

    @XmlElement(name = "UserRights", nillable = true)
    protected List<UserRight> userRights;
    @XmlElement(name = "WSResourceList", nillable = true)
    protected List<WSResource> wsResourceList;
    @XmlElementRef(name = "LoginInfo", namespace = "http://session.dsws.businessobjects.com/2007/06/01", type = JAXBElement.class)
    protected JAXBElement<String> loginInfo;
    @XmlAnyElement(lax = true)
    protected List<Object> any;
    @XmlAttribute(name = "Locale", required = true)
    protected String locale;
    @XmlAttribute(name = "PVLocale", required = true)
    protected String pvLocale;
    @XmlAttribute(name = "MustChangePassword", required = true)
    protected boolean mustChangePassword;
    @XmlAttribute(name = "SessionID", required = true)
    protected String sessionID;
    @XmlAttribute(name = "DefaultToken", required = true)
    protected String defaultToken;
    @XmlAttribute(name = "TimeOut", required = true)
    protected int timeOut;
    @XmlAttribute(name = "UserCUID", required = true)
    protected String userCUID;
    @XmlAttribute(name = "SerializedSession", required = true)
    protected String serializedSession;
    @XmlAttribute(name = "TimeZone")
    protected String timeZone;
    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets the value of the userRights property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userRights property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserRights().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link UserRight }
     */
    public List<UserRight> getUserRights() {
        if (userRights == null) {
            userRights = new ArrayList<UserRight>();
        }
        return this.userRights;
    }

    /**
     * Gets the value of the wsResourceList property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wsResourceList property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWSResourceList().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link WSResource }
     */
    public List<WSResource> getWSResourceList() {
        if (wsResourceList == null) {
            wsResourceList = new ArrayList<WSResource>();
        }
        return this.wsResourceList;
    }

    /**
     * Gets the value of the loginInfo property.
     *
     * @return possible object is
     *         {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getLoginInfo() {
        return loginInfo;
    }

    /**
     * Sets the value of the loginInfo property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setLoginInfo(JAXBElement<String> value) {
        this.loginInfo = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the any property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * <p/>
     * <p/>
     * <p/>
     * Objects of the following type(s) are allowed in the list
     * {@link Element }
     * {@link Object }
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    /**
     * Gets the value of the locale property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLocale(String value) {
        this.locale = value;
    }

    /**
     * Gets the value of the pvLocale property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getPVLocale() {
        return pvLocale;
    }

    /**
     * Sets the value of the pvLocale property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPVLocale(String value) {
        this.pvLocale = value;
    }

    /**
     * Gets the value of the mustChangePassword property.
     */
    public boolean isMustChangePassword() {
        return mustChangePassword;
    }

    /**
     * Sets the value of the mustChangePassword property.
     */
    public void setMustChangePassword(boolean value) {
        this.mustChangePassword = value;
    }

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
     * Gets the value of the defaultToken property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getDefaultToken() {
        return defaultToken;
    }

    /**
     * Sets the value of the defaultToken property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDefaultToken(String value) {
        this.defaultToken = value;
    }

    /**
     * Gets the value of the timeOut property.
     */
    public int getTimeOut() {
        return timeOut;
    }

    /**
     * Sets the value of the timeOut property.
     */
    public void setTimeOut(int value) {
        this.timeOut = value;
    }

    /**
     * Gets the value of the userCUID property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getUserCUID() {
        return userCUID;
    }

    /**
     * Sets the value of the userCUID property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUserCUID(String value) {
        this.userCUID = value;
    }

    /**
     * Gets the value of the serializedSession property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getSerializedSession() {
        return serializedSession;
    }

    /**
     * Sets the value of the serializedSession property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSerializedSession(String value) {
        this.serializedSession = value;
    }

    /**
     * Gets the value of the timeZone property.
     *
     * @return possible object is
     *         {@link String }
     */
    public String getTimeZone() {
        return timeZone;
    }

    /**
     * Sets the value of the timeZone property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTimeZone(String value) {
        this.timeZone = value;
    }

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * <p/>
     * <p/>
     * the map is keyed by the name of the attribute and
     * the value is the string value of the attribute.
     * <p/>
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     *
     * @return always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

}
