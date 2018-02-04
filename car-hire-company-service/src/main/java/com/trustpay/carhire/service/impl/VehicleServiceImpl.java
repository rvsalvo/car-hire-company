/**
 * 
 */
package com.trustpay.carhire.service.impl;


import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Customer;
import com.trustpay.carhire.model.Vehicle;
import com.trustpay.carhire.repository.VehicleRepository;
import com.trustpay.carhire.service.VehicleService;


/**
 * @author Rodrigo Salvo
 *
 */
@Service
public class VehicleServiceImpl implements VehicleService {

    private static final Logger LOG = LogManager.getLogger( VehicleServiceImpl.class );

    @Autowired
    private VehicleRepository vehicleRepository;


    @Override
    public void save( Vehicle vehicle ) {

        Assert.notNull( vehicle, "Vehicle can not be null" );

        LOG.debug( "Saving new vehicle {}", vehicle );

        vehicleRepository.save( vehicle );

    }


    @Override
    public Collection< Vehicle > listAllAvailable() {

        return vehicleRepository.listAllAvailable();
    }


    @Override
    public Collection< Book > listAllBooked() {

        return vehicleRepository.listAllBooked();
    }


    @Override
    public Book book( Book book ) {

        Assert.notNull( book, "Book can not be null" );

        final Vehicle vehicle = book.getVehicle();
        final Customer customer = book.getCustomer();

        Assert.notNull( vehicle, "Vehicle can not be null" );
        Assert.notNull( vehicle.getPlate(), "Vehicle plate can not be null" );
        Assert.notNull( vehicle.getType(), "Vehicle type can not be null" );

        Assert.notNull( customer, "Customer can not be null" );
        Assert.notNull( customer.getEmail(), "Customer email can not be null" );

        LOG.debug( "Booking vehicle {} for customer {}", vehicle, customer );

        return vehicleRepository.book( vehicle, customer );
    }


    @Override
    public Collection< String > findVehicles( String text ) {

        return vehicleRepository.findVehicles( text );
    }

}
