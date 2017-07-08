package logogin.spring;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

/**
 * @created 2008-03-30
 * @author Pavel Danchneko
 */
public class JMXServerTest {

    private static final String URL = "service:jmx:rmi://localhost/jndi/rmi://localhost:1099/myconnector";
    
    @Test
    public void connectServer() throws Exception {
        JMXServiceURL serviceUrl = new JMXServiceURL(URL);
        JMXConnector connector = JMXConnectorFactory.connect(serviceUrl, null);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        assertEquals("bean", connection.getDefaultDomain());
    }
}
