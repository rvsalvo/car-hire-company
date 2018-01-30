package com.crossover.simple.client.main;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * 
 * @author Rodrigo Salvo
 *
 */
@EnableAutoConfiguration
@ComponentScan( { "com.crossover.simple.client" } )
public class SimpleClientApplication {

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger log = Logger.getLogger( SimpleClientApplication.class );


    public static void main( String[] args ) {

        log.debug( "starting client core module..." );

        SpringApplication.run( SimpleClientApplication.class, args );

        log.debug( "client core module started." );

    }

}
