/**
 * 
 */
package com.trustpay.carhire.repository;


import java.util.Collection;

import com.trustpay.carhire.model.Book;
import com.trustpay.carhire.model.Customer;
import com.trustpay.carhire.model.Vehicle;


/**
 * @author Rodrigo Salvo
 *
 */
public interface VehicleRepository {

    void save( Vehicle vehicle );


    Collection< Vehicle > listAllAvailable();


    Collection< Vehicle > listAllBooked();


    Book book( Vehicle vehicle, Customer customer );

}
