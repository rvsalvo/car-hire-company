package com.crossover.simple.client.main;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * 
 * @author Rodrigo Salvo
 *
 */
@EnableAutoConfiguration
@ComponentScan( { "com.trustpay.carhire" } )
public class CarHireCompanyApplication {

    private static final Logger LOG = LogManager.getLogger( CarHireCompanyApplication.class );


    private CarHireCompanyApplication() {

    }


    public static void main( String[] args ) {

        LOG.debug( "Starting Car Hire Company core module..." );

        SpringApplication.run( CarHireCompanyApplication.class, args );

        LOG.debug( "Application core module started." );

    }

}
