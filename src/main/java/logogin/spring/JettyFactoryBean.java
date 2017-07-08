package logogin.spring;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * JettyFactoryBean.java
 *
 * @created Apr 23, 2013
 * @author logogin
 */
public class JettyFactoryBean extends Server implements ApplicationContextAware {

    private int serverPort;
    private String contextConfigLocation;
    private String dispatcherPathSpec;

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void doStart() throws Exception {
        init();
        super.doStart();
    }

    public void init() {
        SelectChannelConnector connector0 = new SelectChannelConnector();
        connector0.setPort(serverPort);
        setConnectors(new Connector[] {connector0});

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        setHandler(context);

        GenericWebApplicationContext wac = new GenericWebApplicationContext();
        wac.setParent(applicationContext);
        wac.refresh();
        context.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, wac);

        DispatcherServlet dispatcher = new DispatcherServlet();
        dispatcher.setContextConfigLocation(contextConfigLocation);
        ServletHolder servletHolder = new ServletHolder(dispatcher);
        servletHolder.setName("dispatcher");
        context.addServlet(servletHolder, dispatcherPathSpec);

        FilterHolder filterHolder = new FilterHolder(DelegatingFilterProxy.class);
        filterHolder.setName("springSecurityFilterChain");
        context.addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public void setContextConfigLocation(String contextConfigLocation) {
        this.contextConfigLocation = contextConfigLocation;
    }

    public void setDispatcherPathSpec(String dispatcherPathSpec) {
        this.dispatcherPathSpec = dispatcherPathSpec;
    }
}
