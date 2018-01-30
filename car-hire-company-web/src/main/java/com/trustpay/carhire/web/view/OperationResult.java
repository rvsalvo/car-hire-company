/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2017
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.trustpay.carhire.web.view;


/**
 * @author Rodrigo Salvo
 *
 */
public class OperationResult {

    private String message;

    private boolean error;


    public OperationResult() {

        super();
    }


    public OperationResult( String message, boolean error ) {

        super();
        this.message = message;
        this.error = error;
    }


    public String getMessage() {

        return message;
    }


    public void setMessage( String message ) {

        this.message = message;
    }


    public boolean isError() {

        return error;
    }


    public void setError( boolean error ) {

        this.error = error;
    }


    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ( error ? 1231 : 1237 );
        result = prime * result + ( ( message == null ) ? 0 : message.hashCode() );
        return result;
    }


    @Override
    public boolean equals( Object obj ) {

        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final OperationResult other = (OperationResult) obj;
        if ( error != other.error ) {
            return false;
        }
        if ( message == null ) {
            if ( other.message != null ) {
                return false;
            }
        } else if ( !message.equals( other.message ) ) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {

        return "OperationResult [message=" + message + ", error=" + error + "]";
    }

}
