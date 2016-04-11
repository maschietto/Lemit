
package webservisi;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10-b140803.1500
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "WebServiceFP", targetNamespace = "http://webServisi/", wsdlLocation = "http://localhost:8080/LEMIT_D.O.O/WebServiceFP?wsdl")
public class WebServiceFP_Service
    extends Service
{

    private final static URL WEBSERVICEFP_WSDL_LOCATION;
    private final static WebServiceException WEBSERVICEFP_EXCEPTION;
    private final static QName WEBSERVICEFP_QNAME = new QName("http://webServisi/", "WebServiceFP");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/LEMIT_D.O.O/WebServiceFP?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WEBSERVICEFP_WSDL_LOCATION = url;
        WEBSERVICEFP_EXCEPTION = e;
    }

    public WebServiceFP_Service() {
        super(__getWsdlLocation(), WEBSERVICEFP_QNAME);
    }

    public WebServiceFP_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns WebServiceFP
     */
    @WebEndpoint(name = "WebServiceFPPort")
    public WebServiceFP getWebServiceFPPort() {
        return super.getPort(new QName("http://webServisi/", "WebServiceFPPort"), WebServiceFP.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceFP
     */
    @WebEndpoint(name = "WebServiceFPPort")
    public WebServiceFP getWebServiceFPPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webServisi/", "WebServiceFPPort"), WebServiceFP.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WEBSERVICEFP_EXCEPTION!= null) {
            throw WEBSERVICEFP_EXCEPTION;
        }
        return WEBSERVICEFP_WSDL_LOCATION;
    }

}
