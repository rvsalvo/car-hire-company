/**
 * 
 */
package com.crossover.simple.client.service;


/**
 * @author Rodrigo Salvo
 *
 */
public class ClientServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public ClientServiceException() {

        super();
    }

    public ClientServiceException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace ) {

        super( message, cause, enableSuppression, writableStackTrace );
        // TODO Auto-generated constructor stub
    }

    public ClientServiceException( String message, Throwable cause ) {

        super( message, cause );
        // TODO Auto-generated constructor stub
    }

    public ClientServiceException( String message ) {

        super( message );
        // TODO Auto-generated constructor stub
    }

    public ClientServiceException( Throwable cause ) {

        super( cause );
        // TODO Auto-generated constructor stub
    }

}
