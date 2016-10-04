package com.sunny.webServices;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * Created by sunshah on 10/1/16.
 */
public class EmbeddedJettyMain {
    private static final int PORT = 4090;
    private static final String BASE_PATH = "/api";

    /**
     * create an instance of server and register endpoints
     * @param args
     */
    public static void main(String[] args) {
        Server server = new Server(PORT);
        /* create service context handler for this server
        * The context handler routes requests to resources, so all resources will need to register with
        * it
        */
        ServletContextHandler servletContextHandler = new ServletContextHandler(server, BASE_PATH);
        /* specify where your Jersey resources are using a resource config object*/
        ResourceConfig resourceConfig = new ResourceConfig();
        /* you can register individual resources or a directory */
//        resourceConfig.register(ImageServlet.class);
        resourceConfig.packages("com.sunny.webServices.resources");
        /* we need to connect JAX RS classes into the servlet api.
         * This is usually done via a servlet or initialization with a library, in this case Jersey.
         * For Jersey, we will need to configure a ServletContainer and setup its servlet mapping.
         */
        ServletHolder servletHolder = new ServletHolder(new ServletContainer(resourceConfig));
        /* now we need to register the servlet container that Jersey provides using a holder
         * to the ServletContextHandler that Jetty uses to look up what to do for incoming requests
         * */
        servletContextHandler.addServlet(servletHolder, "/*");

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.destroy();
        }

    }
}
