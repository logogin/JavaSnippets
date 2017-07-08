package logogin.spring;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * SOAPService.java
 *
 * @created Jul 9, 2012
 * @author logogin
 */
@Service
public class SOAPService {

    private static final String TARGET_NAMESPACE = "http://ws.domain.com/";
    private static final String AUTHENTICATE_ACTION = "isAuthenticated";
    private static final String CREDENTIALS_ACTION = "getCredentials";

    private @Autowired WebServiceTemplate webService;

    @XmlRootElement(name = "isAuthenticated", namespace = TARGET_NAMESPACE)
    public static class AuthenticateRequest {
        public String username;
        public String password;

        public static AuthenticateRequest valueOf(String username, String password) {
            AuthenticateRequest request = new AuthenticateRequest();
            request.username = username;
            request.password = password;
            return request;
        }
    }

    @XmlRootElement(name = AUTHENTICATE_ACTION + "Response", namespace = TARGET_NAMESPACE)
    public static class AuthenticateResponse {
        public boolean _return;
    }

    @XmlRootElement(name = CREDENTIALS_ACTION, namespace = TARGET_NAMESPACE)
    public static class CredentialsRequest {
        public String username;

        public static CredentialsRequest valueOf(String username) {
            CredentialsRequest request = new CredentialsRequest();
            request.username = username;
            return request;
        }
    }

    @XmlRootElement(name = CREDENTIALS_ACTION + "Response", namespace = TARGET_NAMESPACE)
    public static class CredentialsResponse {
        @XmlElement(name = "return")
        public String _return;
    }

    public boolean isAuthenticated(String username, String password) {
        AuthenticateResponse response = (AuthenticateResponse)webService.marshalSendAndReceive(AuthenticateRequest.valueOf(username, password));
        return response._return;
    }

    public String getCredentials(String username) {
        CredentialsResponse response = (CredentialsResponse)webService.marshalSendAndReceive(CredentialsRequest.valueOf(username));
        return response._return;
    }
}
