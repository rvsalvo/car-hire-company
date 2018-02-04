/**
 * 
 */
package com.trustpay.carhire.repository.impl;


import java.text.SimpleDateFormat;
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
 * Responsible for storing in-memory information.
 * 
 * @author Rodrigo Salvo
 *
 */
@Repository
public class InMemoryVehicleRepositoryImpl implements VehicleRepository {

    private Set< Book > books = new HashSet<>();

    private Map< String, Vehicle > vechicles = new HashMap<>();

    private static final SimpleDateFormat FMT = new SimpleDateFormat( "dd/MM/yyyy" );


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

        return this.books;
    }


    @Override
    public Book book( Vehicle vehicle, Customer customer ) {

        Vehicle savedVehicle = this.vechicles.get( vehicle.getPlate() );

        if ( savedVehicle == null ) {
            throw new VehicleNotFoundException( String.format( "Vehicle %s not found!", vehicle ) );
        }

        savedVehicle.setBooked( true );
        Book book = new Book( savedVehicle, customer, FMT.format( new Date() ) );

        this.books.add( book );
        return book;
    }


    @Override
    public Collection< String > findVehicles( String text ) {

        if ( StringUtils.isEmpty( text ) ) {
            return this.vechicles.values().stream().filter( vehicle -> !vehicle.isBooked() ).map( vehicle -> vehicle.toString() ).collect( Collectors.toSet() );
        }

        final String searchText = text.toLowerCase();

        return this.vechicles.values().stream().filter( vehicle -> !vehicle.isBooked() ).map( vehicle -> vehicle.toString() )
            .filter( vechile -> vechile.toLowerCase().indexOf( searchText ) >= 0 ).collect( Collectors.toSet() );

    }

}
