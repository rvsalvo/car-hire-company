/**
 * 
 */
package com.trustpay.carhire.web.controller;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Customer;
import com.trustpay.carhire.model.Vehicle;
import com.trustpay.carhire.service.VehicleService;
import com.trustpay.carhire.web.view.OperationResult;


/**
 * Responsible for handling the file attachment and sending it to server for
 * compilation.
 * 
 * @author Rodrigo Salvo
 *
 */
@RestController
public class CarHireController {

    @Autowired
    private VehicleService vehicleService;


    @RequestMapping( method = RequestMethod.POST, value = "/save" )
    public OperationResult save( @RequestParam( "vehicle" ) Vehicle vehicle ) {

        if ( vehicle == null ) {
            return new OperationResult( "Vehicle is empty", true );
        }

        try {

            vehicleService.save( vehicle );

        } catch ( final Throwable e ) {
            return new OperationResult( e.getMessage(), true );
        }

        return new OperationResult();

    }


    @RequestMapping( method = RequestMethod.GET, value = "/listAllAvailable" )
    public Collection< Vehicle > listAllAvailable() {

        return vehicleService.listAllAvailable();
    }


    @RequestMapping( method = RequestMethod.GET, value = "/listAllBooked" )
    public Collection< Vehicle > listAllBooked() {

        return vehicleService.listAllBooked();
    }


    @RequestMapping( method = RequestMethod.POST, value = "/book" )
    public Book book( Vehicle vehicle, String email ) {

        return vehicleService.book( vehicle, new Customer( email, null ) );
    }

}
