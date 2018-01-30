/**
 * 
 */
package com.crossover.simple.client.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;


/**
 * Facility for loading client configuration properties.
 * 
 * @author Rodrigo Salvo
 *
 */
public class SimpleClientPropertyUtil {
    
    private static final Logger log = Logger.getLogger( SimpleClientPropertyUtil.class );
    
    protected static final Properties properties = new Properties();
    
    static {
        
        loadProperties();
        
    }

    public static void loadProperties() {

        try {
            properties.load( new FileInputStream( System.getProperty( "client.config" )
                + "/conf/simple-client.properties" ) );
        }
        catch ( IOException e ) {
            log.error( e );
        }
    }
    
    public static int simpleClientPort(){
        return getInt( properties.getProperty( "client.port" ) );
    }    
    
    /**
     * Loads the server address
     * 
     * @return String
     */
    public static String serverAddress(){
        return properties.getProperty( "server.address" );
    }   
    
    /**
     * Loads the service context path for server web-services.
     * 
     * @return
     */
    public static String compilerService(){
        return properties.getProperty( "compiler.service" );
    }    
    
    public static long getLong( String value ){
        
        if ( value != null ){
            return Long.parseLong( value );
        }
        
        return -1;
    }
    
    public static int getInt( String value ){
        
        if ( value != null ){
            return Integer.parseInt( value );
        }
        
        return -1;
    }    

    public static boolean getBoolean( String value ){
        
        if ( value != null ){
            return Boolean.parseBoolean( value );
        }
        
        return false;
    }    

}
