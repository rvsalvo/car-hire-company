/**
 * 
 */
package com.trustpay.carhire.repository.impl;


import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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


    @Override
    public Collection< String > findVehicles( String text ) {

        Set< String > vehicles = new HashSet<>();

        if ( StringUtils.isEmpty( text ) ) {
            this.vechicles.values().stream().filter( vehicle -> !vehicle.isBooked() ).forEach( vehicle -> vehicles.add( vehicle.toString() ) );
            return vehicles;
        }

        String searchText = text.toLowerCase();

        for ( Vehicle vehicle : this.vechicles.values() ) {

            if ( vehicle.isBooked() ) {
                continue;
            }

            String vehicleText = vehicle.toString();

            if ( vehicleText.toLowerCase().indexOf( searchText ) >= 0 ) {
                vehicles.add( vehicleText );
            }

        }

        return vehicles;
    }

}
