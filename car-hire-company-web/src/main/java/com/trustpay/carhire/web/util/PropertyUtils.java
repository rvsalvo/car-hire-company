/**
 * 
 */
package com.trustpay.carhire.web.util;


import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Facility for loading client configuration properties.
 * 
 * @author Rodrigo Salvo
 *
 */
public class PropertyUtils {

    private static final Logger LOG = LogManager.getLogger( PropertyUtils.class );

    protected static final Properties PROPERTIES = new Properties();


    private PropertyUtils() {

    }

    static {

        loadProperties();

    }


    public static void loadProperties() {

        try {
            PROPERTIES.load( PropertyUtils.class.getResourceAsStream( "/application.properties" ) );
        } catch ( final IOException e ) {
            LOG.error( e );
        }
    }


    public static int port() {

        return getInt( PROPERTIES.getProperty( "server.port" ) );
    }


    public static String username() {

        return PROPERTIES.getProperty( "security.user.name" );
    }


    public static String password() {

        return PROPERTIES.getProperty( "security.user.password" );
    }


    public static long getLong( String value ) {

        if ( value != null ) {
            return Long.parseLong( value );
        }

        return -1;
    }


    public static int getInt( String value ) {

        if ( value != null ) {
            return Integer.parseInt( value );
        }

        return -1;
    }


    public static boolean getBoolean( String value ) {

        if ( value != null ) {
            return Boolean.parseBoolean( value );
        }

        return false;
    }

}
