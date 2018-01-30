/**
 * 
 */
package com.trustpay.carhire.web.test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.trustpay.carhire.model.Vehicle;
import com.trustpay.carhire.model.VehicleType;
import com.trustpay.carhire.web.view.OperationResult;


/**
 * @author Rodrigo Salvo
 *
 */
@ExtendWith( SpringExtension.class )
@SpringBootTest( webEnvironment = WebEnvironment.RANDOM_PORT )
@ComponentScan( "com.trustpay.carhire" )
public class CarHireCompanyTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testAbout() {

        final Vehicle vehicle = createVehicle();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType( MediaType.APPLICATION_JSON );
        final HttpEntity< Vehicle > entity = new HttpEntity<>( vehicle, headers );

        final ResponseEntity< OperationResult > response = this.restTemplate.postForEntity( "/save", entity, OperationResult.class );
        assertThat( response.getStatusCodeValue(), equalTo( 200 ) );
    }


    private Vehicle createVehicle() {

        return new Vehicle( UUID.randomUUID().toString(), VehicleType.CAR );
    }

}
