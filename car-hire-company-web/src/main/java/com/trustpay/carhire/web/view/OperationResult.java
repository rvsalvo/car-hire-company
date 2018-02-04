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
 * Represents the result object plus an error message in cases of the operation returns an error.
 * 
 * @author Rodrigo Salvo
 *
 */
public class OperationResult< T > {

    private String errorMessage;

    private T result;


    public OperationResult() {

        super();
    }


    public OperationResult( String errorMessage ) {

        super();
        this.errorMessage = errorMessage;
    }


    public OperationResult( String errorMessage, T result ) {

        super();
        this.errorMessage = errorMessage;
        this.result = result;
    }


    public OperationResult( T result ) {

        super();
        this.result = result;
    }


    @Override
    public String toString() {

        return "OperationResult [errorMessage=" + errorMessage + ", result=" + result + "]";
    }


    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( errorMessage == null ) ? 0 : errorMessage.hashCode() );
        result = prime * result + ( ( this.result == null ) ? 0 : this.result.hashCode() );
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
        final OperationResult< ? > other = (OperationResult< ? >) obj;
        if ( errorMessage == null ) {
            if ( other.errorMessage != null ) {
                return false;
            }
        } else if ( !errorMessage.equals( other.errorMessage ) ) {
            return false;
        }
        if ( result == null ) {
            if ( other.result != null ) {
                return false;
            }
        } else if ( !result.equals( other.result ) ) {
            return false;
        }
        return true;
    }


    public String getErrorMessage() {

        return errorMessage;
    }


    public void setErrorMessage( String errorMessage ) {

        this.errorMessage = errorMessage;
    }


    public T getResult() {

        return result;
    }


    public void setResult( T result ) {

        this.result = result;
    }

}
