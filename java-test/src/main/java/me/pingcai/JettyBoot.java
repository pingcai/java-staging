package me.pingcai;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author huangpingcai
 * @since 2018/9/1 18:25
 */
@Slf4j
public class JettyBoot {

    private static final String PORT = "port";
    private static final String CONTEXT_PATH = "contextPath";

    public static void main(String[] args) throws Exception {

        JettyConfig config = initArguments(args);

        startJetty(config);

    }

    private static JettyConfig initArguments(String[] args) {
        JettyConfig config = new JettyConfig();
        for (String arg : args) {
            String[] kv = arg.split("=");
            if (kv.length != 2) {
                continue;
            }
            switch (kv[0]) {
                case PORT:
                    config.setPort(Integer.parseInt(kv[1]));
                    break;
                case CONTEXT_PATH:
                    config.setContextPath(kv[1]);
                    break;
                default:
                    break;
            }
        }
        return config;
    }

    private static void startJetty(JettyConfig config) throws Exception {
        //1.Create a Server instance.
        Server server = new Server();
        server.setStopAtShutdown(true);

        //2.Add/Configure Connectors.
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(config.getPort());
        server.addConnector(connector);

        //3.Add/Configure Handlers and/or Contexts and/or Servlets.
        WebAppContext context = new WebAppContext();
        context.setContextPath(config.getContextPath());
        context.setWar(config.getWebDir());
        server.setHandler(context);

        // 支持JSP
        Configuration.ClassList classList = Configuration.ClassList
                .setServerDefault(server);
        classList.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration");
        context.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$");

        //4.Start the Server.
        server.start();
        log.info("jetty is running");

        //5.Wait on the server or do something else with your thread.
        server.join();
    }

    private static final class JettyConfig {

        private String webDir = JettyConfig.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        private int port = 8080;
        private String contextPath = "/";

        public JettyConfig() {
        }

        public String getContextPath() {
            return contextPath;
        }

        public void setContextPath(String contextPath) {
            this.contextPath = contextPath;
        }

        public String getWebDir() {
            return webDir;
        }

        public void setWebDir(String webDir) {
            this.webDir = webDir;
        }

        public JettyConfig(int port) {
            this.port = port;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
