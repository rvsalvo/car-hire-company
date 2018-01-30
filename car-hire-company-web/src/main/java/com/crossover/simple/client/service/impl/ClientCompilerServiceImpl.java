/**
 * 
 */
package com.crossover.simple.client.service.impl;


import java.io.IOException;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.mime.FormBodyPart;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.crossover.simple.client.model.UploadResult;
import com.crossover.simple.client.service.ClientCompilerService;
import com.crossover.simple.client.service.ClientServiceException;
import com.crossover.simple.client.util.SimpleClientPropertyUtil;
import com.google.gson.GsonBuilder;


/**
 * @author Rodrigo Salvo
 *
 */
@Service
public class ClientCompilerServiceImpl implements ClientCompilerService {

    private static final Logger log = Logger.getLogger( ClientCompilerServiceImpl.class );

    @Override
    public UploadResult sendToCompile( MultipartFile file ) throws ClientServiceException {

        Assert.notNull( file, "File can not be null." );

        log.debug( String.format( "Received file %s for compilation", file.getOriginalFilename() ) );

        //checks file format.
        Assert.isTrue( "application/x-zip-compressed".equals( file.getContentType() )
            || file.getOriginalFilename().endsWith( "zip" ), "File must be in ZIP format" );

        try {
            
            return makeRequest( file );
            
        } catch ( Throwable e ) {
            log.error( "Error while sending file to compile.", e );
            throw new ClientServiceException( String.format( "Error while sending file to compile: %s", e.getLocalizedMessage() ) );
        }

    }

    /**
     * Executes request to upload file and send it to server for compilation.
     * 
     * @param file
     * @return UploadStatus
     * @throws IOException
     */
    private UploadResult makeRequest( MultipartFile file ) throws IOException {
        
        MultipartEntity entity = new MultipartEntity();
        FormBodyPart bodyPart = new FormBodyPart( "file", new InputStreamBody( file.getInputStream(), file.getContentType(), file.getOriginalFilename() ) );
        entity.addPart( bodyPart );
        
        String message = Request.Post( SimpleClientPropertyUtil.serverAddress() + SimpleClientPropertyUtil.compilerService() )
        .body( entity )
        .execute()
        .handleResponse( new ResponseHandler< String >() {

            @Override
            public String handleResponse( HttpResponse response ) throws ClientProtocolException, IOException {

                StringWriter writer = new StringWriter();
                IOUtils.copy( response.getEntity().getContent(), writer );
                String json = writer.toString();

                //if any error status is returned, raises an exception.
                if ( response.getStatusLine().getStatusCode() < 300 ) {
                    return json;
                }
                throw new RuntimeException( String.format( "Unknow Error from server. Server returned error: %s : %s",
                    response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase() != null ? response.getStatusLine().getReasonPhrase() : "" ) );
            }
        } );
        
        //parses the returned object into a json message.
        return new GsonBuilder().create().fromJson( message, UploadResult.class );
        
    }

}
