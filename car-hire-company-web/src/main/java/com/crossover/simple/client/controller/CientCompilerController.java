/**
 * 
 */
package com.crossover.simple.client.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crossover.simple.client.model.UploadResult;
import com.crossover.simple.client.service.ClientCompilerService;
import com.crossover.simple.client.service.ClientServiceException;


/**
 * Responsible for handling the file attachment and sending it to server for compilation.
 * 
 * @author Rodrigo Salvo
 *
 */
@RestController
public class CientCompilerController {
    
    @Autowired
    private ClientCompilerService clientCompilerService;

    @RequestMapping( method = RequestMethod.POST, value = "/fileUpload" )
    public UploadResult handleFileUpload(
        @RequestParam( "file" ) MultipartFile file,
        RedirectAttributes redirectAttributes ) {

        if ( file.isEmpty() ) {
            return new UploadResult( String.format( "Failed to upload %s because it was empty", file.getOriginalFilename() ), true );
        }
        
        try {
            
            return clientCompilerService.sendToCompile( file );
            
        } catch ( ClientServiceException e ) {
            return new UploadResult( e.getMessage(), true );            
        }
        
    }

}
