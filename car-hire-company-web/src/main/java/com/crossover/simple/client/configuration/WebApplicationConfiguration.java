/**
 * 
 */
package com.crossover.simple.client.configuration;


import javax.ws.rs.core.Application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.crossover.simple.client.util.SimpleClientPropertyUtil;


/**
 * Configures tomcat application port.
 * 
 * @author Rodrigo Salvo
 *
 */
@Configuration
public class WebApplicationConfiguration extends SpringBootServletInitializer {

    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {

        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory( SimpleClientPropertyUtil.simpleClientPort() );
        return factory;
    }


    @Override
    protected SpringApplicationBuilder configure( SpringApplicationBuilder application ) {

        return application.sources( Application.class );
    }

}
