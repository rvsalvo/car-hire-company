/**
 * 
 */
package com.trustpay.carhire.web.configuration;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Configures authorization for client.
 * 
 * @author Rodrigo Salvo
 *
 */
@Configuration
@Order( SecurityProperties.ACCESS_OVERRIDE_ORDER )
public class WebSecurityApplicationConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http )
        throws Exception {

        http.httpBasic().and().authorizeRequests().antMatchers( "/index.html", "/home.html", "/login.html", "/" ).permitAll().anyRequest().authenticated().and()
            .csrf().disable();
    }

}
