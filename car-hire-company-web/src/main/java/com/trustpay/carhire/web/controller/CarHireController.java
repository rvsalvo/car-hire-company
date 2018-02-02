/**
 * 
 */
package com.trustpay.carhire.web.controller;


import static org.springframework.http.ResponseEntity.status;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Vehicle;
import com.trustpay.carhire.service.VehicleService;
import com.trustpay.carhire.web.view.OperationResult;


/**
 * Responsible for handling book and register of vehicles.
 * 
 * @author Rodrigo Salvo
 *
 */
@RestController
public class CarHireController {

    @Autowired
    private VehicleService vehicleService;


    @RequestMapping( method = RequestMethod.POST, value = "/save" )
    public ResponseEntity< OperationResult< Void > > save( @RequestBody Vehicle vehicle ) {

        if ( vehicle == null ) {
            status( HttpStatus.BAD_REQUEST ).body( new OperationResult<>( "Vehicle is empty" ) );
        }

        try {

            vehicleService.save( vehicle );

        } catch ( final Exception e ) {
            return generateErrorResult( e );
        }

        return status( HttpStatus.OK ).body( new OperationResult<>() );

    }


    @RequestMapping( method = RequestMethod.GET, value = "/listAllAvailable" )
    public ResponseEntity< OperationResult< Collection< Vehicle > > > listAllAvailable() {

        try {
            return status( HttpStatus.OK ).body( new OperationResult<>( vehicleService.listAllAvailable() ) );
        } catch ( final Exception e ) {
            return generateErrorResult( e );
        }

    }


    @RequestMapping( method = RequestMethod.GET, value = "/listAllBooked" )
    public ResponseEntity< OperationResult< Collection< Vehicle > > > listAllBooked() {

        try {
            return status( HttpStatus.OK ).body( new OperationResult<>( vehicleService.listAllBooked() ) );
        } catch ( final Exception e ) {
            return generateErrorResult( e );
        }

    }


    @RequestMapping( method = RequestMethod.POST, value = "/book" )
    public ResponseEntity< OperationResult< Book > > book( @RequestBody Book book ) {

        try {
            return status( HttpStatus.OK ).body( new OperationResult<>( vehicleService.book( book ) ) );
        } catch ( final Exception e ) {
            return generateErrorResult( e );
        }
    }


    private < T > ResponseEntity< OperationResult< T > > generateErrorResult( final Exception e ) {

        return status( HttpStatus.INTERNAL_SERVER_ERROR ).body( new OperationResult<>( e.getMessage() ) );
    }

}
