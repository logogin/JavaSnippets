package logogin.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.management.MBeanServer;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@Service
public class JmxStarter {
    private Logger log = LoggerFactory.getLogger(JmxStarter.class);

    private @Value("${jmx.objforwardport}") int objforwardport;
    private @Value("${jmx.remoteport}") int remoteport;

    private Registry rmiRegistry;
    private JMXConnectorServer connectorServer;

    @PostConstruct
    public void init() throws IOException {
        String surl = String.format("service:jmx:rmi://localhost:%d/jndi/rmi://localhost:%d/jmxrmi", objforwardport, remoteport);
        log.debug("trying to create jmx service at url: {}" , surl);

        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        rmiRegistry = LocateRegistry.createRegistry(remoteport);
        JMXServiceURL url = new JMXServiceURL(surl);
        connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(url, null, mbeanServer);
        connectorServer.start();
    }

    @PreDestroy
    public void close() throws IOException {
        connectorServer.stop();
        UnicastRemoteObject.unexportObject(rmiRegistry, true);
    }
}