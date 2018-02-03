/**
 * 
 */
package com.trustpay.carhire.repository.impl;


import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Customer;
import com.trustpay.carhire.model.Vehicle;
import com.trustpay.carhire.repository.VehicleRepository;
import com.trustpay.carhire.repository.exception.VehicleNotFoundException;


/**
 * @author Rodrigo Salvo
 *
 */
@Repository
public class ImMemoryVehicleRepositoryImpl implements VehicleRepository {

    private Map< String, Book > books = new HashMap<>();

    private Map< String, Vehicle > vechicles = new HashMap<>();


    @Override
    public void save( Vehicle vehicle ) {

        this.vechicles.put( vehicle.getPlate(), vehicle );

    }


    @Override
    public Collection< Vehicle > listAllAvailable() {

        return this.vechicles.values().stream().filter( v -> !v.isBooked() ).collect( Collectors.toSet() );
    }


    @Override
    public Collection< Book > listAllBooked() {

        return this.books.values();
    }


    @Override
    public Book book( Vehicle vehicle, Customer customer ) {

        Vehicle savedVehicle = this.vechicles.get( vehicle.getPlate() );

        if ( savedVehicle == null ) {
            throw new VehicleNotFoundException( String.format( "Vehicle %s not found!", vehicle ) );
        }

        savedVehicle.setBooked( true );
        Book book = new Book( savedVehicle, customer, new Date() );

        this.books.put( customer.getEmail(), book );
        return book;
    }

}
