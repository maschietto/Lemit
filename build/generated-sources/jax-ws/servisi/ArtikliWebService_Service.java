
package servisi;

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
@WebServiceClient(name = "ArtikliWebService", targetNamespace = "http://servisi/", wsdlLocation = "http://localhost:8080/OrtopanWebService/ArtikliWebService?wsdl")
public class ArtikliWebService_Service
    extends Service
{

    private final static URL ARTIKLIWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException ARTIKLIWEBSERVICE_EXCEPTION;
    private final static QName ARTIKLIWEBSERVICE_QNAME = new QName("http://servisi/", "ArtikliWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/OrtopanWebService/ArtikliWebService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ARTIKLIWEBSERVICE_WSDL_LOCATION = url;
        ARTIKLIWEBSERVICE_EXCEPTION = e;
    }

    public ArtikliWebService_Service() {
        super(__getWsdlLocation(), ARTIKLIWEBSERVICE_QNAME);
    }

    public ArtikliWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns ArtikliWebService
     */
    @WebEndpoint(name = "ArtikliWebServicePort")
    public ArtikliWebService getArtikliWebServicePort() {
        return super.getPort(new QName("http://servisi/", "ArtikliWebServicePort"), ArtikliWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ArtikliWebService
     */
    @WebEndpoint(name = "ArtikliWebServicePort")
    public ArtikliWebService getArtikliWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://servisi/", "ArtikliWebServicePort"), ArtikliWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ARTIKLIWEBSERVICE_EXCEPTION!= null) {
            throw ARTIKLIWEBSERVICE_EXCEPTION;
        }
        return ARTIKLIWEBSERVICE_WSDL_LOCATION;
    }

}