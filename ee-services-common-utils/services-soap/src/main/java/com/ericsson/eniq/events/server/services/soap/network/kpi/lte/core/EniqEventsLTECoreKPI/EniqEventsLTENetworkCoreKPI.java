package com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "EniqEventsLTENetworkCoreKPI", targetNamespace = "EniqEventsLTENetworkCoreKPI", wsdlLocation = "http://atrcxb1272vm2.athtem.eei.ericsson.se:8080/dswsbobje/qaawsservices/biws?WSDL=1&cuid=AVoNMCPoSC9Cr.Tck0K.xgs")
public class EniqEventsLTENetworkCoreKPI extends Service {

    private final static URL ENIQEVENTSLTENETWORKCOREKPI_WSDL_LOCATION;

    private final static Logger logger = Logger
            .getLogger(com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI.EniqEventsLTENetworkCoreKPI.class
                    .getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.ericsson.eniq.events.server.services.soap.network.kpi.lte.core.EniqEventsLTECoreKPI.EniqEventsLTENetworkCoreKPI.class
                    .getResource(".");
            url = new URL(baseUrl,
                    "http://atrcxb1272vm2.athtem.eei.ericsson.se:8080/dswsbobje/qaawsservices/biws?WSDL=1&cuid=AVoNMCPoSC9Cr.Tck0K.xgs");
        } catch (final MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://atrcxb1272vm2.athtem.eei.ericsson.se:8080/dswsbobje/qaawsservices/biws?WSDL=1&cuid=AVoNMCPoSC9Cr.Tck0K.xgs', retrying as a local file");
            logger.warning(e.getMessage());
        }
        ENIQEVENTSLTENETWORKCOREKPI_WSDL_LOCATION = url;
    }

    public EniqEventsLTENetworkCoreKPI(final URL wsdlLocation, final QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EniqEventsLTENetworkCoreKPI() {
        super(ENIQEVENTSLTENETWORKCOREKPI_WSDL_LOCATION, new QName("EniqEventsLTENetworkCoreKPI",
                "EniqEventsLTENetworkCoreKPI"));
    }

    /**
     * 
     * @return
     *     returns QueryAsAServiceSoap
     */
    @WebEndpoint(name = "QueryAsAServiceSoap")
    public QueryAsAServiceSoap getQueryAsAServiceSoap() {
        return super
                .getPort(new QName("EniqEventsLTENetworkCoreKPI", "QueryAsAServiceSoap"), QueryAsAServiceSoap.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns QueryAsAServiceSoap
     */
    @WebEndpoint(name = "QueryAsAServiceSoap")
    public QueryAsAServiceSoap getQueryAsAServiceSoap(final WebServiceFeature... features) {
        return super.getPort(new QName("EniqEventsLTENetworkCoreKPI", "QueryAsAServiceSoap"),
                QueryAsAServiceSoap.class, features);
    }

}
