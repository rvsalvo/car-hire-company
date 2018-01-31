/**
 * 
 */
package com.trustpay.carhire.web.test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.trustpay.carhire.model.Vehicle;
import com.trustpay.carhire.model.VehicleType;
import com.trustpay.carhire.web.test.configuration.CarHireCompanyContextConfiguration;
import com.trustpay.carhire.web.util.PropertyUtils;
import com.trustpay.carhire.web.view.OperationResult;


/**
 * @author Rodrigo Salvo
 *
 */
@RunWith( SpringRunner.class )
@SpringBootTest( classes = { CarHireCompanyContextConfiguration.class }, webEnvironment = WebEnvironment.RANDOM_PORT )
public class CarHireCompanyTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testCreateVehicle() {

        final Vehicle vehicle = createVehicle();

        final HttpHeaders headers = createHeaders( PropertyUtils.username(), PropertyUtils.password() );
        headers.setContentType( MediaType.APPLICATION_JSON );
        final HttpEntity< Vehicle > entity = new HttpEntity<>( vehicle, headers );

        final ResponseEntity< OperationResult > response = this.restTemplate.postForEntity( "/save", entity, OperationResult.class );
        assertThat( response.getStatusCodeValue(), equalTo( 200 ) );
    }


    @SuppressWarnings( "rawtypes" )
    @Test
    public void testListVehicles() {

        final HttpHeaders headers = createHeaders( PropertyUtils.username(), PropertyUtils.password() );
        headers.setContentType( MediaType.APPLICATION_JSON );
        final HttpEntity< ? > entity = new HttpEntity<>( headers );

        final ResponseEntity< Collection > response = this.restTemplate.exchange( "/listAllAvailable", HttpMethod.GET, entity, Collection.class );

        assertThat( response.getStatusCodeValue(), equalTo( 200 ) );
        assertTrue( response.getBody().isEmpty() );
    }


    @SuppressWarnings( "rawtypes" )
    @Test
    public void testListBooked() {

        final HttpHeaders headers = createHeaders( PropertyUtils.username(), PropertyUtils.password() );
        headers.setContentType( MediaType.APPLICATION_JSON );
        final HttpEntity< ? > entity = new HttpEntity<>( headers );

        final ResponseEntity< Collection > response = this.restTemplate.exchange( "/listAllBooked", HttpMethod.GET, entity, Collection.class );

        assertThat( response.getStatusCodeValue(), equalTo( 200 ) );
        assertTrue( response.getBody().isEmpty() );
    }


    private HttpHeaders createHeaders( String username, String password ) {

        return new HttpHeaders() {

            private static final long serialVersionUID = 1L;

            {
                final String auth = username + ":" + password;
                final byte[] encodedAuth = Base64.encode( auth.getBytes() );
                final String authHeader = "Basic " + new String( encodedAuth );
                set( "Authorization", authHeader );
            }
        };
    }


    private Vehicle createVehicle() {

        return new Vehicle( UUID.randomUUID().toString(), VehicleType.CAR );
    }

}
