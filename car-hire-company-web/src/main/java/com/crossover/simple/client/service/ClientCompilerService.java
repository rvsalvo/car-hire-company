/**
 * 
 */
package com.crossover.simple.client.service;

import org.springframework.web.multipart.MultipartFile;

import com.crossover.simple.client.model.UploadResult;



/**
 * @author Rodrigo Salvo
 *
 */
public interface ClientCompilerService {

    /**
     * Send file to server for uploading and compilation.
     * 
     * @param file
     * @return UploadResult - Status of the extraction and compilation process.
     * @throws ClientServiceException
     */
    UploadResult sendToCompile( MultipartFile file ) throws ClientServiceException;
    
}
