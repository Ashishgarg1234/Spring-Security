package com.cisco.security.authserverauthentication;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthServerAuthenticationApplication {

    @Value(value = "${server.ssl.key-store:}")
    private String sslKeyStore;


    public static void main(String[] args) {
        SpringApplication.run(AuthServerAuthenticationApplication.class, args);
    }
    @Bean
    public ServletWebServerFactory servletContainer() {
        boolean needRedirectToHttps = sslKeyStore != null && !sslKeyStore.isEmpty();

        TomcatServletWebServerFactory tomcat = null;

        if (!needRedirectToHttps) {
            tomcat = new TomcatServletWebServerFactory();
            return tomcat;
        }

        tomcat = new TomcatServletWebServerFactory() {

            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
    }

    private Connector redirectConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8083);
        return connector;
    }
}
