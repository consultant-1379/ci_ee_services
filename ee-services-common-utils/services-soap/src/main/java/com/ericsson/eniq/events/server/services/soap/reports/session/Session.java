package com.ericsson.eniq.events.server.services.soap.reports.session;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;


/**
 * Session Web Service
 * <p/>
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 */
@WebServiceClient(name = "Session", targetNamespace = "http://session.dsws.businessobjects.com/2007/06/01", wsdlLocation = "http://atrcxb1272vm1.athtem.eei.ericsson.se:8080/dswsbobje/services/Session?wsdl")
public class Session
        extends Service {

    private final static URL SESSION_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.ericsson.eniq.events.server.services.soap.reports.session.Session.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.ericsson.eniq.events.server.services.soap.reports.session.Session.class.getResource(".");
            url = new URL(baseUrl, "http://atrcxb1272vm1.athtem.eei.ericsson.se:8080/dswsbobje/services/Session?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://atrcxb1272vm1.athtem.eei.ericsson.se:8080/dswsbobje/services/Session?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        SESSION_WSDL_LOCATION = url;
    }

    public Session(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Session() {
        super(SESSION_WSDL_LOCATION, new QName("http://session.dsws.businessobjects.com/2007/06/01", "Session"));
    }

    /**
     * @return returns SessionPort
     */
    @WebEndpoint(name = "Session")
    public SessionPort getSession() {
        return super.getPort(new QName("http://session.dsws.businessobjects.com/2007/06/01", "Session"), SessionPort.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns SessionPort
     */
    @WebEndpoint(name = "Session")
    public SessionPort getSession(WebServiceFeature... features) {
        return super.getPort(new QName("http://session.dsws.businessobjects.com/2007/06/01", "Session"), SessionPort.class, features);
    }

}
