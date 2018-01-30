/**
 * 
 */
package com.crossover.simple.client.model;


/**
 * @author Rodrigo Salvo
 *
 */
public class UploadResult {
    
    private final String message;
    private final boolean error;
    private final String requestId;
    
    public UploadResult( String message, boolean error ) {

        super();
        this.message = message;
        this.error = error;
        this.requestId = null;
    }
    
    public UploadResult( String message, boolean error, String requestId ) {

        super();
        this.message = message;
        this.error = error;
        this.requestId = requestId;
    }

    public UploadResult( String requestId ) {

        super();
        this.requestId = requestId;
        this.message = null;
        this.error = true;
    }

    /**
     * @return the message
     */
    public String getMessage() {
    
        return message;
    }




    
    /**
     * @return the success
     */
    public boolean isError() {
    
        return error;
    }

    
    /**
     * @return the requestId
     */
    public String getRequestId() {
    
        return requestId;
    }

    
}
