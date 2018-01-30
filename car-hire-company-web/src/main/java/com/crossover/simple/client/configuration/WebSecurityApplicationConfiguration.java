/**
 * 
 */
package com.crossover.simple.client.configuration;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


/**
 * Configures authorization for client. 
 * 
 * @author Rodrigo Salvo
 *
 */
@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityApplicationConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure( HttpSecurity http )
        throws Exception {

        CookieCsrfTokenRepository repo = new CookieCsrfTokenRepository();
        repo.setCookieHttpOnly( false );
        
        http.httpBasic().and().authorizeRequests().antMatchers( 
             "/index.html", 
             "/home.html", 
             "/login.html", 
             "/" )
            .permitAll().anyRequest().authenticated().and()
            .csrf()
            .csrfTokenRepository( repo );
    }

}
