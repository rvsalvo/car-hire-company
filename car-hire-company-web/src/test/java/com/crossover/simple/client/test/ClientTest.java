/**
 * 
 */
package com.crossover.simple.client.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crossover.simple.client.service.ClientCompilerService;
import com.crossover.simple.client.service.ClientServiceException;


/**
 * @author Rodrigo Salvo
 *
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = { "classpath:context.xml" } )
public class ClientTest {

    private static final Logger log = Logger.getLogger( ClientTest.class );

    @Autowired
    private ClientCompilerService compilerService;
    
    @Test( expected = IllegalArgumentException.class )    
    public void test() throws ClientServiceException{
        
        log.debug( "Executing client compiler test..." );
        
        compilerService.sendToCompile( null );
        
    }


}
