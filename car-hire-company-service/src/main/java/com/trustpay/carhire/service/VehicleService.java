/**
 * 
 */
package com.trustpay.carhire.service;


import java.util.Collection;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Vehicle;


/**
 * @author Rodrigo Salvo
 *
 */
public interface VehicleService {

    /**
     * Saves the vehicle into the system.
     * 
     * @param vehicle
     */
    void save( Vehicle vehicle );


    /**
     * List all available vehicles, not booked yet.
     * 
     * @return available vehicles.
     */
    Collection< Vehicle > listAllAvailable();


    /**
     * List all booked vehicles, including the customer who booked it.
     * 
     * @return Booked vehicle.
     */
    Collection< Book > listAllBooked();


    /**
     * Process the booking of a vehicle, marking it as booked.
     * 
     * @param book
     * @return the booked operation
     */
    Book book( Book book );


    /**
     * Searches for available (not booked) vehicles based on a string text.
     * 
     * @param text
     * @return the String representation of the found vehicles
     */
    Collection< String > findVehicles( String text );
}
