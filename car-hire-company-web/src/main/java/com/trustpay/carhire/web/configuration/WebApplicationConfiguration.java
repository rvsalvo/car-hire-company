/**
 * 
 */
package com.trustpay.carhire.web.configuration;


import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.trustpay.carhire.web.util.PropertyUtils;


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

        return new TomcatEmbeddedServletContainerFactory( PropertyUtils.port() );
    }

}
